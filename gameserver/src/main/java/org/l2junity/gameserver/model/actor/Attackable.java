/*
 * Copyright (C) 2004-2015 L2J Unity
 * 
 * This file is part of L2J Unity.
 * 
 * L2J Unity is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J Unity is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.l2junity.gameserver.model.actor;

import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.commons.util.Rnd;
import org.l2junity.core.configs.*;
import org.l2junity.gameserver.ai.*;
import org.l2junity.gameserver.data.xml.impl.ExtendDropData;
import org.l2junity.gameserver.datatables.EventDroplist;
import org.l2junity.gameserver.datatables.EventDroplist.DateDrop;
import org.l2junity.gameserver.datatables.ItemTable;
import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.enums.InstanceType;
import org.l2junity.gameserver.instancemanager.CursedWeaponsManager;
import org.l2junity.gameserver.instancemanager.SuperpointManager;
import org.l2junity.gameserver.model.*;
import org.l2junity.gameserver.model.actor.instance.L2GrandBossInstance;
import org.l2junity.gameserver.model.actor.instance.L2MonsterInstance;
import org.l2junity.gameserver.model.actor.instance.L2ServitorInstance;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.actor.status.AttackableStatus;
import org.l2junity.gameserver.model.actor.tasks.attackable.CommandChannelTimer;
import org.l2junity.gameserver.model.actor.templates.L2NpcTemplate;
import org.l2junity.gameserver.model.drops.DropListScope;
import org.l2junity.gameserver.model.entity.Hero;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.character.npc.OnAttackableAggroRangeEnter;
import org.l2junity.gameserver.model.events.impl.character.npc.OnAttackableAttack;
import org.l2junity.gameserver.model.events.impl.character.npc.OnAttackableKill;
import org.l2junity.gameserver.model.holders.ItemHolder;
import org.l2junity.gameserver.model.items.L2Item;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.skills.SkillCaster;
import org.l2junity.gameserver.model.stats.DoubleStat;
import org.l2junity.gameserver.network.client.send.CreatureSay;
import org.l2junity.gameserver.network.client.send.SystemMessage;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;
import org.l2junity.gameserver.taskmanager.DecayTaskManager;
import org.l2junity.gameserver.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.WeakReference;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Attackable extends Npc {
	private static final Logger LOGGER = LoggerFactory.getLogger(Attackable.class);
	// Raid
	private boolean _isRaid = false;
	private boolean _isRaidMinion = false;
	//
	private boolean _champion = false;
	private volatile Map<Creature, AggroInfo> _aggroList = null;
	private boolean _isReturningToSpawnPoint = false;
	private boolean _canReturnToSpawnPoint = true;
	private boolean _seeThroughSilentMove = false;
	private boolean _canStopAttackByTime = true;
	// Manor
	private boolean _seeded = false;
	private L2Seed _seed = null;
	private int _seederObjId = 0;
	private final AtomicReference<ItemHolder> _harvestItem = new AtomicReference<>();
	// Spoil
	private int _spoilerObjectId;
	private final AtomicReference<Collection<ItemHolder>> _sweepItems = new AtomicReference<>();
	// Over-hit
	private boolean _overhit;
	private double _overhitDamage;
	private Creature _overhitAttacker;
	// Command channel
	private volatile CommandChannel _firstCommandChannelAttacked = null;
	private CommandChannelTimer _commandChannelTimer = null;
	private long _commandChannelLastAttack = 0;
	// Misc
	private boolean _mustGiveExpSp;

	/**
	 * Constructor of L2Attackable (use L2Character and L2NpcInstance constructor).<br>
	 * Actions:<br>
	 * Call the L2Character constructor to set the _template of the L2Attackable (copy skills from template to object and link _calculators to NPC_STD_CALCULATOR)<br>
	 * Set the name of the L2Attackable<br>
	 * Create a RandomAnimation Task that will be launched after the calculated delay if the server allow it.
	 *
	 * @param template the template to apply to the NPC.
	 */
	public Attackable(L2NpcTemplate template) {
		super(template);
		setInstanceType(InstanceType.Attackable);
		setIsInvul(false);
		_mustGiveExpSp = true;
	}

	@Override
	public AttackableStatus getStatus() {
		return (AttackableStatus) super.getStatus();
	}

	@Override
	public void initCharStatus() {
		setStatus(new AttackableStatus(this));
	}

	@Override
	protected CharacterAI initAI() {
		return new AttackableAI(this);
	}

	public final Map<Creature, AggroInfo> getAggroList() {
		return _aggroList != null ? _aggroList : Collections.emptyMap();
	}

	public final boolean isReturningToSpawnPoint() {
		return _isReturningToSpawnPoint;
	}

	public final void setisReturningToSpawnPoint(boolean value) {
		_isReturningToSpawnPoint = value;
	}

	public final boolean canReturnToSpawnPoint() {
		return _canReturnToSpawnPoint;
	}

	public final void setCanReturnToSpawnPoint(boolean value) {
		_canReturnToSpawnPoint = value;
	}

	public boolean canSeeThroughSilentMove() {
		return _seeThroughSilentMove;
	}

	public void setSeeThroughSilentMove(boolean val) {
		_seeThroughSilentMove = val;
	}

	public boolean canStopAttackByTime() {
		return _canStopAttackByTime;
	}

	public void setCanStopAttackByTime(boolean val) {
		_canStopAttackByTime = val;
	}

	/**
	 * Use the skill if minimum checks are pass.
	 *
	 * @param skill the skill
	 */
	public void useMagic(Skill skill) {
		if (!SkillCaster.checkUseConditions(this, skill)) {
			return;
		}

		final WorldObject target = skill.getTarget(this, false, false, false);
		if (target != null) {
			getAI().setIntention(CtrlIntention.AI_INTENTION_CAST, skill, target);
		}
	}

	/**
	 * Reduce the current HP of the L2Attackable, update its _aggroList and launch the doDie Task if necessary.
	 *
	 * @param attacker The L2Character who attacks
	 * @param isDOT
	 * @param skill
	 */
	@Override
	public void reduceCurrentHp(double value, Creature attacker, Skill skill, boolean isDOT, boolean directlyToHp, boolean critical, boolean reflect) {
		if (isRaid() && !isMinion() && (attacker != null) && (attacker.getParty() != null) && attacker.getParty().isInCommandChannel() && attacker.getParty().getCommandChannel().meetRaidWarCondition(this)) {
			if (_firstCommandChannelAttacked == null) // looting right isn't set
			{
				synchronized (this) {
					if (_firstCommandChannelAttacked == null) {
						_firstCommandChannelAttacked = attacker.getParty().getCommandChannel();
						if (_firstCommandChannelAttacked != null) {
							_commandChannelTimer = new CommandChannelTimer(this);
							_commandChannelLastAttack = System.currentTimeMillis();
							ThreadPool.getInstance().scheduleAi(_commandChannelTimer, 10000, TimeUnit.MILLISECONDS); // check for last attack
							_firstCommandChannelAttacked.broadcastPacket(new CreatureSay(0, ChatType.PARTYROOM_ALL, "", "You have looting rights!")); // TODO: retail msg
						}
					}
				}
			} else if (attacker.getParty().getCommandChannel().equals(_firstCommandChannelAttacked)) // is in same channel
			{
				_commandChannelLastAttack = System.currentTimeMillis(); // update last attack time
			}
		}

		// Add damage and hate to the attacker AggroInfo of the L2Attackable _aggroList
		if (attacker != null) {
			addDamage(attacker, (int) value, skill);
		}

		// If this L2Attackable is a L2MonsterInstance and it has spawned minions, call its minions to battle
		if (isMonster()) {
			L2MonsterInstance master = (L2MonsterInstance) this;

			if (master.hasMinions()) {
				master.getMinionList().onAssist(this, attacker);
			}

			master = master.getLeader();
			if ((master != null) && master.hasMinions()) {
				master.getMinionList().onAssist(this, attacker);
			}
		}
		// Reduce the current HP of the L2Attackable and launch the doDie Task if necessary
		super.reduceCurrentHp(value, attacker, skill, isDOT, directlyToHp, critical, reflect);
	}

	public synchronized void setMustRewardExpSp(boolean value) {
		_mustGiveExpSp = value;
	}

	public synchronized boolean getMustRewardExpSP() {
		return _mustGiveExpSp;
	}

	/**
	 * Kill the L2Attackable (the corpse disappeared after 7 seconds), distribute rewards (EXP, SP, Drops...) and notify Quest Engine.<br>
	 * Actions:<br>
	 * Distribute Exp and SP rewards to L2PcInstance (including Summon owner) that hit the L2Attackable and to their Party members<br>
	 * Notify the Quest Engine of the L2Attackable death if necessary.<br>
	 * Kill the L2NpcInstance (the corpse disappeared after 7 seconds)<br>
	 * Caution: This method DOESN'T GIVE rewards to L2PetInstance.
	 *
	 * @param killer The L2Character that has killed the L2Attackable
	 */
	@Override
	public boolean doDie(Creature killer) {
		// Kill the L2NpcInstance (the corpse disappeared after 7 seconds)
		if (!super.doDie(killer)) {
			return false;
		}

		if ((killer != null) && killer.isPlayable()) {
			// Delayed notification
			EventDispatcher.getInstance().notifyEventAsync(new OnAttackableKill(killer.getActingPlayer(), this, killer.isSummon()), this);
		}

		// Notify to minions if there are.
		if (isMonster()) {
			final L2MonsterInstance mob = (L2MonsterInstance) this;
			if ((mob.getLeader() != null) && mob.getLeader().hasMinions()) {
				final int respawnTime = NpcConfig.MINIONS_RESPAWN_TIME.containsKey(getId()) ? NpcConfig.MINIONS_RESPAWN_TIME.get(getId()) * 1000 : -1;
				mob.getLeader().getMinionList().onMinionDie(mob, respawnTime);
			}

			if (mob.hasMinions()) {
				mob.getMinionList().onMasterDie(false);
			}
		}

		return true;
	}

	/**
	 * Distribute Exp and SP rewards to L2PcInstance (including Summon owner) that hit the L2Attackable and to their Party members.<br>
	 * Actions:<br>
	 * Get the L2PcInstance owner of the L2ServitorInstance (if necessary) and L2Party in progress.<br>
	 * Calculate the Experience and SP rewards in function of the level difference.<br>
	 * Add Exp and SP rewards to L2PcInstance (including Summon penalty) and to Party members in the known area of the last attacker.<br>
	 * Caution : This method DOESN'T GIVE rewards to L2PetInstance.
	 *
	 * @param lastAttacker The L2Character that has killed the L2Attackable
	 */
	@Override
	protected void calculateRewards(Creature lastAttacker) {
		try {
			if (getAggroList().isEmpty()) {
				return;
			}

			// NOTE: Concurrent-safe map is used because while iterating to verify all conditions sometimes an entry must be removed.
			final Map<PlayerInstance, DamageDoneInfo> rewards = new ConcurrentHashMap<>();

			PlayerInstance maxDealer = null;
			int maxDamage = 0;
			long totalDamage = 0;
			// While Iterating over This Map Removing Object is Not Allowed
			// Go through the _aggroList of the L2Attackable
			for (AggroInfo info : getAggroList().values()) {
				// Get the L2Character corresponding to this attacker
				final PlayerInstance attacker = info.getAttacker().getActingPlayer();
				if (attacker != null) {
					// Get damages done by this attacker
					final int damage = info.getDamage();

					// Prevent unwanted behavior
					if (damage > 1) {
						// Check if damage dealer isn't too far from this (killed monster)
						if (!Util.checkIfInRange(PlayerConfig.ALT_PARTY_RANGE, this, attacker, true)) {
							continue;
						}

						totalDamage += damage;

						// Calculate real damages (Summoners should get own damage plus summon's damage)
						final DamageDoneInfo reward = rewards.computeIfAbsent(attacker, DamageDoneInfo::new);
						reward.addDamage(damage);

						if (reward.getDamage() > maxDamage) {
							maxDealer = attacker;
							maxDamage = reward.getDamage();
						}
					}
				}
			}

			// Calculate raidboss points
			if (isRaid() && !isRaidMinion()) {
				final PlayerInstance player = (maxDealer != null) && maxDealer.isOnline() ? maxDealer : lastAttacker.getActingPlayer();
				broadcastPacket(SystemMessage.getSystemMessage(SystemMessageId.CONGRATULATIONS_YOUR_RAID_WAS_SUCCESSFUL));
				final int raidbossPoints = (int) (getTemplate().getRaidPoints() * RatesConfig.RATE_RAIDBOSS_POINTS);
				final Party party = player.getParty();

				if (party != null) {
					final CommandChannel command = party.getCommandChannel();
					//@formatter:off
					final List<PlayerInstance> members = command != null ?
							command.getMembers().stream().filter(p -> p.isInRadius3d(this, PlayerConfig.ALT_PARTY_RANGE)).collect(Collectors.toList()) :
							player.getParty().getMembers().stream().filter(p -> p.isInRadius3d(this, PlayerConfig.ALT_PARTY_RANGE)).collect(Collectors.toList());
					//@formatter:on

					members.forEach(p ->
					{
						final int points = Math.max(raidbossPoints / members.size(), 1);
						p.increaseRaidbossPoints(points);
						p.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_EARNED_S1_RAID_POINT_S).addInt(points));

						if (p.isNoble()) {
							Hero.getInstance().setRBkilled(p.getObjectId(), getId());
						}
					});
				} else {
					final int points = Math.max(raidbossPoints, 1);
					player.increaseRaidbossPoints(points);
					player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_EARNED_S1_RAID_POINT_S).addInt(points));
					if (player.isNoble()) {
						Hero.getInstance().setRBkilled(player.getObjectId(), getId());
					}
				}
			}

			// Manage Base, Quests and Sweep drops of the L2Attackable
			doItemDrop((maxDealer != null) && maxDealer.isOnline() ? maxDealer : lastAttacker);

			// Manage drop of Special Events created by GM for a defined period
			doEventDrop(lastAttacker);

			// Manage luck based drop
			doLuckyDrop(getTemplate(), (maxDealer != null) && maxDealer.isOnline() ? maxDealer : lastAttacker);

			if (!getMustRewardExpSP()) {
				return;
			}

			if (!rewards.isEmpty()) {
				for (DamageDoneInfo reward : rewards.values()) {
					if (reward == null) {
						continue;
					}

					// Attacker to be rewarded
					final PlayerInstance attacker = reward.getAttacker();

					// Total amount of damage done
					final int damage = reward.getDamage();

					// Get party
					final Party attackerParty = attacker.getParty();

					// Penalty applied to the attacker's XP
					// If this attacker have servitor, get Exp Penalty applied for the servitor.
					float penalty = 1;

					final L2ServitorInstance summon = attacker.getServitors().values().stream().map(L2ServitorInstance.class::cast).filter(s -> s.getExpMultiplier() < 1).findFirst().orElse(null);
					if (summon != null) {
						penalty = summon.getExpMultiplier();
					}

					// If there's NO party in progress
					if (attackerParty == null) {
						// Calculate Exp and SP rewards
						if (isInSurroundingRegion(attacker)) {
							final double[] expSp = calculateExpAndSp(attacker.getLevel(), damage, totalDamage);
							double exp = expSp[0];
							double sp = expSp[1];

							if (L2JModsConfig.L2JMOD_CHAMPION_ENABLE && isChampion()) {
								exp *= L2JModsConfig.L2JMOD_CHAMPION_REWARDS;
								sp *= L2JModsConfig.L2JMOD_CHAMPION_REWARDS;
							}

							if (attacker.isPremium()) {
								exp *= PremiumConfig.RATE_PREMIUM_XP;
								sp *= PremiumConfig.RATE_PREMIUM_SP;
							}

							exp *= penalty;

							// Check for an over-hit enabled strike
							final Creature overhitAttacker = getOverhitAttacker();
							if (isOverhit() && (overhitAttacker != null) && (overhitAttacker.getActingPlayer() != null) && (attacker == overhitAttacker.getActingPlayer())) {
								attacker.sendPacket(SystemMessageId.OVER_HIT);
								exp *= calculateOverhitExpMod();
							}

							// Distribute the Exp and SP between the L2PcInstance and its L2Summon
							if (!attacker.isDead()) {
								exp = attacker.getStat().getValue(DoubleStat.EXPSP_RATE, exp);
								sp = attacker.getStat().getValue(DoubleStat.EXPSP_RATE, sp);

								attacker.addExpAndSp(exp, sp, useVitalityRate());
								if (exp > 0) {
									final L2Clan clan = attacker.getClan();
									if (clan != null) {
										double finalExp = exp;
										if (useVitalityRate()) {
											finalExp *= attacker.getStat().getExpBonusMultiplier();
										}
										clan.addHuntingPoints(attacker, this, finalExp);
									}

									attacker.updateVitalityPoints(getVitalityPoints(attacker.getLevel(), exp, isRaid()), true, true);
								}
							}
						}
					} else {
						// share with party members
						int partyDmg = 0;
						double partyMul = 1;
						int partyLvl = 0;

						// Get all L2Character that can be rewarded in the party
						final List<PlayerInstance> rewardedMembers = new ArrayList<>();
						// Go through all L2PcInstance in the party
						final List<PlayerInstance> groupMembers = attackerParty.isInCommandChannel() ? attackerParty.getCommandChannel().getMembers() : attackerParty.getMembers();
						for (PlayerInstance partyPlayer : groupMembers) {
							if ((partyPlayer == null) || partyPlayer.isDead()) {
								continue;
							}

							// Get the RewardInfo of this L2PcInstance from L2Attackable rewards
							final DamageDoneInfo reward2 = rewards.get(partyPlayer);

							// If the L2PcInstance is in the L2Attackable rewards add its damages to party damages
							if (reward2 != null) {
								if (Util.checkIfInRange(PlayerConfig.ALT_PARTY_RANGE, this, partyPlayer, true)) {
									partyDmg += reward2.getDamage(); // Add L2PcInstance damages to party damages
									rewardedMembers.add(partyPlayer);

									if (partyPlayer.getLevel() > partyLvl) {
										if (attackerParty.isInCommandChannel()) {
											partyLvl = attackerParty.getCommandChannel().getLevel();
										} else {
											partyLvl = partyPlayer.getLevel();
										}
									}
								}
								rewards.remove(partyPlayer); // Remove the L2PcInstance from the L2Attackable rewards
							} else {
								// Add L2PcInstance of the party (that have attacked or not) to members that can be rewarded
								// and in range of the monster.
								if (Util.checkIfInRange(PlayerConfig.ALT_PARTY_RANGE, this, partyPlayer, true)) {
									rewardedMembers.add(partyPlayer);
									if (partyPlayer.getLevel() > partyLvl) {
										if (attackerParty.isInCommandChannel()) {
											partyLvl = attackerParty.getCommandChannel().getLevel();
										} else {
											partyLvl = partyPlayer.getLevel();
										}
									}
								}
							}
						}

						// If the party didn't killed this L2Attackable alone
						if (partyDmg < totalDamage) {
							partyMul = ((double) partyDmg / totalDamage);
						}

						// Calculate Exp and SP rewards
						final double[] expSp = calculateExpAndSp(partyLvl, partyDmg, totalDamage);
						double exp = expSp[0];
						double sp = expSp[1];

						if (L2JModsConfig.L2JMOD_CHAMPION_ENABLE && isChampion()) {
							exp *= L2JModsConfig.L2JMOD_CHAMPION_REWARDS;
							sp *= L2JModsConfig.L2JMOD_CHAMPION_REWARDS;
						}

						exp *= partyMul;
						sp *= partyMul;

						// Check for an over-hit enabled strike
						// (When in party, the over-hit exp bonus is given to the whole party and splitted proportionally through the party members)
						final Creature overhitAttacker = getOverhitAttacker();
						if (isOverhit() && (overhitAttacker != null) && (overhitAttacker.getActingPlayer() != null) && (attacker == overhitAttacker.getActingPlayer())) {
							attacker.sendPacket(SystemMessageId.OVER_HIT);
							exp *= calculateOverhitExpMod();
						}

						// Distribute Experience and SP rewards to L2PcInstance Party members in the known area of the last attacker
						if (partyDmg > 0) {
							attackerParty.distributeXpAndSp(exp, sp, rewardedMembers, partyLvl, partyDmg, this);
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("", e);
		}
	}

	@Override
	public void addAttackerToAttackByList(Creature player) {
		if ((player == null) || (player == this) || getAttackByList().contains(player)) {
			return;
		}
		getAttackByList().add(new WeakReference<>(player));
	}

	/**
	 * Add damage and hate to the attacker AggroInfo of the L2Attackable _aggroList.
	 *
	 * @param attacker The L2Character that gave damages to this L2Attackable
	 * @param damage   The number of damages given by the attacker L2Character
	 * @param skill
	 */
	public void addDamage(Creature attacker, int damage, Skill skill) {
		if (attacker == null) {
			return;
		}

		// Notify the L2Attackable AI with EVT_ATTACKED
		if (!isDead()) {
			try {
				// If monster is on walk - stop it
				if (isWalker() && !isCoreAIDisabled() && SuperpointManager.getInstance().isOnWalk(this)) {
					SuperpointManager.getInstance().stopMoving(this, false, true);
				}

				getAI().notifyEvent(CtrlEvent.EVT_ATTACKED, attacker);

				// Calculate the amount of hate this attackable receives from this attack.
				double hateValue = (damage * 100) / (getLevel() + 7);

				if (skill == null) {
					hateValue *= attacker.getStat().getValue(DoubleStat.HATE_ATTACK, 1);
				}

				addDamageHate(attacker, damage, (int) hateValue);

				final PlayerInstance player = attacker.getActingPlayer();
				if (player != null) {
					EventDispatcher.getInstance().notifyEventAsync(new OnAttackableAttack(player, this, damage, skill, attacker.isSummon()), this);
				}
			} catch (Exception e) {
				LOGGER.error("", e);
			}
		}
	}

	/**
	 * Add damage and hate to the attacker AggroInfo of the L2Attackable _aggroList.
	 *
	 * @param attacker The L2Character that gave damages to this L2Attackable
	 * @param damage   The number of damages given by the attacker L2Character
	 * @param aggro    The hate (=damage) given by the attacker L2Character
	 */
	public void addDamageHate(Creature attacker, int damage, int aggro) {
		if ((attacker == null) || (attacker == this)) {
			return;
		}

		PlayerInstance targetPlayer = attacker.getActingPlayer();
		final Creature summoner = attacker.getSummoner();
		if (attacker.isNpc() && (summoner != null) && summoner.isPlayer() && !attacker.isTargetable()) {
			targetPlayer = summoner.getActingPlayer();
			attacker = summoner;
		}

		// Get the AggroInfo of the attacker L2Character from the _aggroList of the L2Attackable
		if (_aggroList == null) {
			synchronized (this) {
				if (_aggroList == null) {
					_aggroList = new ConcurrentHashMap<>();
				}
			}
		}
		final AggroInfo ai = _aggroList.computeIfAbsent(attacker, AggroInfo::new);
		ai.addDamage(damage);

		// traps does not cause aggro
		// making this hack because not possible to determine if damage made by trap
		// so just check for triggered trap here
		if ((targetPlayer == null) || (targetPlayer.getTrap() == null) || !targetPlayer.getTrap().isTriggered()) {
			ai.addHate(aggro);
		}

		if ((targetPlayer != null) && (aggro == 0)) {
			addDamageHate(attacker, 0, 1);

			// Set the intention to the L2Attackable to AI_INTENTION_ACTIVE
			if (getAI().getIntention() == CtrlIntention.AI_INTENTION_IDLE) {
				getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
			}

			// Notify to scripts
			EventDispatcher.getInstance().notifyEventAsync(new OnAttackableAggroRangeEnter(this, targetPlayer, attacker.isSummon()), this);
		} else if ((targetPlayer == null) && (aggro == 0)) {
			aggro = 1;
			ai.addHate(1);
		}

		// Set the intention to the L2Attackable to AI_INTENTION_ACTIVE
		if ((aggro != 0) && (getAI().getIntention() == CtrlIntention.AI_INTENTION_IDLE)) {
			getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
		}
	}

	public void reduceHate(Creature target, int amount) {
		if ((getAI() instanceof SiegeGuardAI) || (getAI() instanceof FortSiegeGuardAI)) {
			// TODO: this just prevents error until siege guards are handled properly
			stopHating(target);
			setTarget(null);
			getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
			return;
		}

		if (target == null) // whole aggrolist
		{
			Creature mostHated = getMostHated();
			if (mostHated == null) // makes target passive for a moment more
			{
				((AttackableAI) getAI()).setGlobalAggro(-25);
				return;
			}

			for (AggroInfo ai : getAggroList().values()) {
				ai.addHate(amount);
			}

			amount = getHating(mostHated);
			if (amount >= 0) {
				((AttackableAI) getAI()).setGlobalAggro(-25);
				clearAggroList();
				getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
				setWalking();
			}
			return;
		}

		AggroInfo ai = getAggroList().get(target);
		if (ai == null) {
			LOGGER.info("target " + target + " not present in aggro list of " + this);
			return;
		}

		ai.addHate(amount);
		if ((ai.getHate() >= 0) && (getMostHated() == null)) {
			((AttackableAI) getAI()).setGlobalAggro(-25);
			clearAggroList();
			getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
			setWalking();
		}
	}

	/**
	 * Clears _aggroList hate of the L2Character without removing from the list.
	 *
	 * @param target
	 */
	public void stopHating(Creature target) {
		if (target == null) {
			return;
		}

		final AggroInfo ai = getAggroList().get(target);
		if (ai != null) {
			ai.stopHate();
		}
	}

	/**
	 * @return the most hated L2Character of the L2Attackable _aggroList.
	 */
	public Creature getMostHated() {
		if (getAggroList().isEmpty() || isAlikeDead()) {
			return null;
		}

		return getAggroList().values().stream().filter(Objects::nonNull).sorted(Comparator.comparingInt(AggroInfo::getHate).reversed()).map(AggroInfo::getAttacker).findFirst().orElse(null);
	}

	/**
	 * @return the 2 most hated L2Character of the L2Attackable _aggroList.
	 */
	public List<Creature> get2MostHated() {
		if (getAggroList().isEmpty() || isAlikeDead()) {
			return null;
		}

		Creature mostHated = null;
		Creature secondMostHated = null;
		int maxHate = 0;
		List<Creature> result = new ArrayList<>();

		// While iterating over this map removing objects is not allowed
		// Go through the aggroList of the L2Attackable
		for (AggroInfo ai : getAggroList().values()) {
			if (ai.checkHate(this) > maxHate) {
				secondMostHated = mostHated;
				mostHated = ai.getAttacker();
				maxHate = ai.getHate();
			}
		}

		result.add(mostHated);

		if (getAttackByList().contains(secondMostHated)) {
			result.add(secondMostHated);
		} else {
			result.add(null);
		}
		return result;
	}

	public List<Creature> getHateList() {
		if (getAggroList().isEmpty() || isAlikeDead()) {
			return null;
		}

		final List<Creature> result = new ArrayList<>();
		for (AggroInfo ai : getAggroList().values()) {
			ai.checkHate(this);

			result.add(ai.getAttacker());
		}
		return result;
	}

	/**
	 * @param target The L2Character whose hate level must be returned
	 * @return the hate level of the L2Attackable against this L2Character contained in _aggroList.
	 */
	public int getHating(final Creature target) {
		if (getAggroList().isEmpty() || (target == null)) {
			return 0;
		}

		final AggroInfo ai = getAggroList().get(target);
		if (ai == null) {
			return 0;
		}

		if (ai.getAttacker() instanceof PlayerInstance) {
			PlayerInstance act = (PlayerInstance) ai.getAttacker();
			if (act.isInvisible() || ai.getAttacker().isInvul() || act.isSpawnProtected()) {
				// Remove Object Should Use This Method and Can be Blocked While Interacting
				getAggroList().remove(target);
				return 0;
			}
		}

		if (!ai.getAttacker().isSpawned() || ai.getAttacker().isInvisible()) {
			getAggroList().remove(target);
			return 0;
		}

		if (ai.getAttacker().isAlikeDead()) {
			ai.stopHate();
			return 0;
		}
		return ai.getHate();
	}

	public void doItemDrop(Creature mainDamageDealer) {
		doItemDrop(getTemplate(), mainDamageDealer);
	}

	/**
	 * Manage Base, Quests and Special Events drops of L2Attackable (called by calculateRewards).<br>
	 * Concept:<br>
	 * During a Special Event all L2Attackable can drop extra Items.<br>
	 * Those extra Items are defined in the table allNpcDateDrops of the EventDroplist.<br>
	 * Each Special Event has a start and end date to stop to drop extra Items automatically.<br>
	 * Actions:<br>
	 * Manage drop of Special Events created by GM for a defined period.<br>
	 * Get all possible drops of this L2Attackable from L2NpcTemplate and add it Quest drops.<br>
	 * For each possible drops (base + quests), calculate which one must be dropped (random).<br>
	 * Get each Item quantity dropped (random).<br>
	 * Create this or these L2ItemInstance corresponding to each Item Identifier dropped.<br>
	 * If the autoLoot mode is actif and if the L2Character that has killed the L2Attackable is a L2PcInstance, Give the item(s) to the L2PcInstance that has killed the L2Attackable.<br>
	 * If the autoLoot mode isn't actif or if the L2Character that has killed the L2Attackable is not a L2PcInstance, add this or these item(s) in the world as a visible object at the position where mob was last.
	 *
	 * @param npcTemplate
	 * @param mainDamageDealer
	 */
	public void doItemDrop(L2NpcTemplate npcTemplate, Creature mainDamageDealer) {
		if (mainDamageDealer == null) {
			return;
		}

		final PlayerInstance player = mainDamageDealer.getActingPlayer();

		// Don't drop anything if the last attacker or owner isn't L2PcInstance
		if (player == null) {
			return;
		}

		CursedWeaponsManager.getInstance().checkDrop(this, player);

		npcTemplate.getExtendDrop().stream().map(ExtendDropData.getInstance()::getExtendDropById).filter(Objects::nonNull).forEach(e -> e.reward(player, this));

		if (isSpoiled()) {
			_sweepItems.set(npcTemplate.calculateDrops(DropListScope.CORPSE, this, player));
		}

		Collection<ItemHolder> deathItems = npcTemplate.calculateDrops(DropListScope.DEATH, this, player);
		if (deathItems != null) {
			for (ItemHolder drop : deathItems) {
				L2Item item = ItemTable.getInstance().getTemplate(drop.getId());
				// Check if the autoLoot mode is active
				if (isFlying() || (!item.hasExImmediateEffect() && ((!isRaid() && PlayerConfig.AUTO_LOOT) || (isRaid() && PlayerConfig.AUTO_LOOT_RAIDS))) || (item.hasExImmediateEffect() && PlayerConfig.AUTO_LOOT_HERBS)) {
					player.doAutoLoot(this, drop); // Give the item(s) to the L2PcInstance that has killed the L2Attackable
				} else {
					dropItem(player, drop); // drop the item on the ground
				}

				// Broadcast message if RaidBoss was defeated
				if (isRaid() && !isRaidMinion()) {
					final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_DIED_AND_DROPPED_S3_S2_S);
					sm.addCharName(this);
					sm.addItemName(item);
					sm.addLong(drop.getCount());
					broadcastPacket(sm);
				}
			}
		}

		// Apply Special Item drop with random(rnd) quantity(qty) for champions.
		if (L2JModsConfig.L2JMOD_CHAMPION_ENABLE && isChampion() && ((L2JModsConfig.L2JMOD_CHAMPION_REWARD_LOWER_LVL_ITEM_CHANCE > 0) || (L2JModsConfig.L2JMOD_CHAMPION_REWARD_HIGHER_LVL_ITEM_CHANCE > 0))) {
			int champqty = Rnd.get(L2JModsConfig.L2JMOD_CHAMPION_REWARD_QTY);
			ItemHolder item = new ItemHolder(L2JModsConfig.L2JMOD_CHAMPION_REWARD_ID, ++champqty);

			if ((player.getLevel() <= getLevel()) && (Rnd.get(100) < L2JModsConfig.L2JMOD_CHAMPION_REWARD_LOWER_LVL_ITEM_CHANCE)) {
				if (PlayerConfig.AUTO_LOOT || isFlying()) {
					player.addItem("ChampionLoot", item.getId(), item.getCount(), this, true); // Give the item(s) to the L2PcInstance that has killed the L2Attackable
				} else {
					dropItem(player, item);
				}
			} else if ((player.getLevel() > getLevel()) && (Rnd.get(100) < L2JModsConfig.L2JMOD_CHAMPION_REWARD_HIGHER_LVL_ITEM_CHANCE)) {
				if (PlayerConfig.AUTO_LOOT || isFlying()) {
					player.addItem("ChampionLoot", item.getId(), item.getCount(), this, true); // Give the item(s) to the L2PcInstance that has killed the L2Attackable
				} else {
					dropItem(player, item);
				}
			}
		}
	}

	/**
	 * Manage Special Events drops created by GM for a defined period.<br>
	 * Concept:<br>
	 * During a Special Event all L2Attackable can drop extra Items.<br>
	 * Those extra Items are defined in the table allNpcDateDrops of the EventDroplist.<br>
	 * Each Special Event has a start and end date to stop to drop extra Items automatically.<br>
	 * Actions: <I>If an extra drop must be generated</I><br>
	 * Get an Item Identifier (random) from the DateDrop Item table of this Event.<br>
	 * Get the Item quantity dropped (random).<br>
	 * Create this or these L2ItemInstance corresponding to this Item Identifier.<br>
	 * If the autoLoot mode is actif and if the L2Character that has killed the L2Attackable is a L2PcInstance, Give the item(s) to the L2PcInstance that has killed the L2Attackable<br>
	 * If the autoLoot mode isn't actif or if the L2Character that has killed the L2Attackable is not a L2PcInstance, add this or these item(s) in the world as a visible object at the position where mob was last
	 *
	 * @param lastAttacker The L2Character that has killed the L2Attackable
	 */
	public void doEventDrop(Creature lastAttacker) {
		if (lastAttacker == null) {
			return;
		}

		PlayerInstance player = lastAttacker.getActingPlayer();

		// Don't drop anything if the last attacker or owner isn't L2PcInstance
		if (player == null) {
			return;
		}

		if ((player.getLevel() - getLevel()) > 9) {
			return;
		}

		// Go through DateDrop of EventDroplist allNpcDateDrops within the date range
		for (DateDrop drop : EventDroplist.getInstance().getAllDrops()) {
			if (Rnd.get(1000000) < drop.getEventDrop().getDropChance()) {
				final int itemId = drop.getEventDrop().getItemIdList()[Rnd.get(drop.getEventDrop().getItemIdList().length)];
				final long itemCount = Rnd.get(drop.getEventDrop().getMinCount(), drop.getEventDrop().getMaxCount());
				if (PlayerConfig.AUTO_LOOT || isFlying()) {
					player.doAutoLoot(this, itemId, itemCount); // Give the item(s) to the L2PcInstance that has killed the L2Attackable
				} else {
					dropItem(player, itemId, itemCount); // drop the item on the ground
				}
			}
		}
	}

	/**
	 * Manage lucky drops<br>
	 *
	 * @param npcTemplate      the killed npc template
	 * @param mainDamageDealer The L2Character that has killed the L2Attackable
	 */
	public void doLuckyDrop(L2NpcTemplate npcTemplate, Creature mainDamageDealer) {
		if (mainDamageDealer == null) {
			return;
		}

		final PlayerInstance player = mainDamageDealer.getActingPlayer();

		// Don't drop anything if the last attacker or owner isn't L2PcInstance
		if (player == null) {
			return;
		}

		final Collection<ItemHolder> luckItems = npcTemplate.calculateDrops(DropListScope.LUCK, this, player);
		if (luckItems != null) {
			for (ItemHolder drop : luckItems) {
				player.doAutoLoot(this, drop);
			}
		}
	}

	/**
	 * @return the active weapon of this L2Attackable (= null).
	 */
	public ItemInstance getActiveWeapon() {
		return null;
	}

	/**
	 * @param player The L2Character searched in the _aggroList of the L2Attackable
	 * @return True if the _aggroList of this L2Attackable contains the L2Character.
	 */
	public boolean containsTarget(Creature player) {
		return getAggroList().containsKey(player);
	}

	/**
	 * Clear the _aggroList of the L2Attackable.
	 */
	public void clearAggroList() {
		_aggroList = null;

		// clear overhit values
		_overhit = false;
		_overhitDamage = 0;
		_overhitAttacker = null;
	}

	/**
	 * @return {@code true} if there is a loot to sweep, {@code false} otherwise.
	 */
	@Override
	public boolean isSweepActive() {
		return _sweepItems.get() != null;
	}

	/**
	 * @return a copy of dummy items for the spoil loot.
	 */
	public List<L2Item> getSpoilLootItems() {
		final Collection<ItemHolder> sweepItems = _sweepItems.get();
		final List<L2Item> lootItems = new LinkedList<>();
		if (sweepItems != null) {
			for (ItemHolder item : sweepItems) {
				lootItems.add(ItemTable.getInstance().getTemplate(item.getId()));
			}
		}
		return lootItems;
	}

	/**
	 * @return table containing all L2ItemInstance that can be spoiled.
	 */
	public Collection<ItemHolder> takeSweep() {
		return _sweepItems.getAndSet(null);
	}

	/**
	 * @return table containing all L2ItemInstance that can be harvested.
	 */
	public ItemHolder takeHarvest() {
		return _harvestItem.getAndSet(null);
	}

	/**
	 * Checks if the corpse is too old.
	 *
	 * @param attacker      the player to validate
	 * @param remainingTime the time to check
	 * @param sendMessage   if {@code true} will send a message of corpse too old
	 * @return {@code true} if the corpse is too old
	 */
	public boolean isOldCorpse(PlayerInstance attacker, int remainingTime, boolean sendMessage) {
		if (isDead() && (DecayTaskManager.getInstance().getRemainingTime(this) < remainingTime)) {
			if (sendMessage && (attacker != null)) {
				attacker.sendPacket(SystemMessageId.THE_CORPSE_IS_TOO_OLD_THE_SKILL_CANNOT_BE_USED);
			}
			return true;
		}
		return false;
	}

	/**
	 * @param sweeper     the player to validate.
	 * @param sendMessage sendMessage if {@code true} will send a message of sweep not allowed.
	 * @return {@code true} if is the spoiler or is in the spoiler party.
	 */
	public boolean checkSpoilOwner(PlayerInstance sweeper, boolean sendMessage) {
		if ((sweeper.getObjectId() != getSpoilerObjectId()) && !sweeper.isInLooterParty(getSpoilerObjectId())) {
			if (sendMessage) {
				sweeper.sendPacket(SystemMessageId.THERE_ARE_NO_PRIORITY_RIGHTS_ON_A_SWEEPER);
			}
			return false;
		}
		return true;
	}

	/**
	 * Set the over-hit flag on the L2Attackable.
	 *
	 * @param status The status of the over-hit flag
	 */
	public void overhitEnabled(boolean status) {
		_overhit = status;
	}

	/**
	 * Set the over-hit values like the attacker who did the strike and the amount of damage done by the skill.
	 *
	 * @param attacker The L2Character who hit on the L2Attackable using the over-hit enabled skill
	 * @param damage   The amount of damage done by the over-hit enabled skill on the L2Attackable
	 */
	public void setOverhitValues(Creature attacker, double damage) {
		// Calculate the over-hit damage
		// Ex: mob had 10 HP left, over-hit skill did 50 damage total, over-hit damage is 40
		double overhitDmg = -(getCurrentHp() - damage);
		if (overhitDmg < 0) {
			// we didn't killed the mob with the over-hit strike. (it wasn't really an over-hit strike)
			// let's just clear all the over-hit related values
			overhitEnabled(false);
			_overhitDamage = 0;
			_overhitAttacker = null;
			return;
		}
		overhitEnabled(true);
		_overhitDamage = overhitDmg;
		_overhitAttacker = attacker;
	}

	/**
	 * Return the L2Character who hit on the L2Attackable using an over-hit enabled skill.
	 *
	 * @return L2Character attacker
	 */
	public Creature getOverhitAttacker() {
		return _overhitAttacker;
	}

	/**
	 * Return the amount of damage done on the L2Attackable using an over-hit enabled skill.
	 *
	 * @return double damage
	 */
	public double getOverhitDamage() {
		return _overhitDamage;
	}

	/**
	 * @return True if the L2Attackable was hit by an over-hit enabled skill.
	 */
	public boolean isOverhit() {
		return _overhit;
	}

	/**
	 * Calculate the Experience and SP to distribute to attacker (L2PcInstance, L2ServitorInstance or L2Party) of the L2Attackable.
	 *
	 * @param charLevel   The killer level
	 * @param damage      The damages given by the attacker (L2PcInstance, L2ServitorInstance or L2Party)
	 * @param totalDamage The total damage done
	 * @return
	 */
	private double[] calculateExpAndSp(int charLevel, int damage, long totalDamage) {
		final int levelDiff = getLevel() - charLevel;
		double xp = 0;
		double sp = 0;

		if ((levelDiff < 11) && (levelDiff > -11)) {
			xp = Math.max(0, (getExpReward() * damage) / totalDamage);
			sp = Math.max(0, (getSpReward() * damage) / totalDamage);

			if ((charLevel > 84) && (levelDiff <= -3)) {
				double mul;
				switch (levelDiff) {
					case -3: {
						mul = 0.97;
						break;
					}
					case -4: {
						mul = 0.67;
						break;
					}
					case -5: {
						mul = 0.42;
						break;
					}
					case -6: {
						mul = 0.25;
						break;
					}
					case -7: {
						mul = 0.15;
						break;
					}
					case -8: {
						mul = 0.09;
						break;
					}
					case -9: {
						mul = 0.05;
						break;
					}
					case -10: {
						mul = 0.03;
						break;
					}
					default: {
						mul = 1.;
						break;
					}
				}
				xp *= mul;
				sp *= mul;
			}
		}

		return new double[]
				{
						xp,
						sp
				};
	}

	public double calculateOverhitExpMod() {
		// Get the percentage based on the total of extra (over-hit) damage done relative to the total (maximum) ammount of HP on the L2Attackable
		// (1/1 basis - 13% of over-hit damage, 13% of extra exp is given, and so on...)
		// Over-hit damage percentages are limited to 25% max
		return 1 + Math.min(getOverhitDamage() / getMaxHp(), 0.25);
	}

	/**
	 * Return True.
	 */
	@Override
	public boolean canBeAttacked() {
		return true;
	}

	@Override
	public void onSpawn() {
		super.onSpawn();

		// Clear mob spoil, seed
		setSpoilerObjectId(0);

		// Clear all aggro list and overhit
		clearAggroList();

		// Clear Harvester reward
		_harvestItem.set(null);
		_sweepItems.set(null);

		// Clear mod Seeded stat
		_seeded = false;
		_seed = null;
		_seederObjId = 0;

		setWalking();

		// check the region where this mob is, do not activate the AI if region is inactive.
		if (!isInActiveRegion()) {
			if (hasAI()) {
				getAI().stopAITask();
			}
		}
	}

	@Override
	public void onRespawn() {
		// Reset champion state
		_champion = false;

		if (L2JModsConfig.L2JMOD_CHAMPION_ENABLE) {
			// Set champion on next spawn
			if (isMonster() && !getTemplate().isUndying() && !isRaid() && !isRaidMinion() && (L2JModsConfig.L2JMOD_CHAMPION_FREQUENCY > 0) && (getLevel() >= L2JModsConfig.L2JMOD_CHAMP_MIN_LVL) && (getLevel() <= L2JModsConfig.L2JMOD_CHAMP_MAX_LVL) && (L2JModsConfig.L2JMOD_CHAMPION_ENABLE_IN_INSTANCES || (getInstanceId() == 0))) {
				if (Rnd.get(100) < L2JModsConfig.L2JMOD_CHAMPION_FREQUENCY) {
					_champion = true;
				}
			}
		}

		// Reset the rest of NPC related states
		super.onRespawn();
	}

	/**
	 * Checks if its spoiled.
	 *
	 * @return {@code true} if its spoiled, {@code false} otherwise
	 */
	public boolean isSpoiled() {
		return _spoilerObjectId != 0;
	}

	/**
	 * Gets the spoiler object ID.
	 *
	 * @return the spoiler object ID if its spoiled, 0 otherwise
	 */
	public final int getSpoilerObjectId() {
		return _spoilerObjectId;
	}

	/**
	 * Sets the spoiler object ID.
	 *
	 * @param spoilerObjectId spoilerObjectId the spoiler object ID
	 */
	public final void setSpoilerObjectId(int spoilerObjectId) {
		_spoilerObjectId = spoilerObjectId;
	}

	/**
	 * Sets state of the mob to seeded. Parameters needed to be set before.
	 *
	 * @param seeder
	 */
	public final void setSeeded(PlayerInstance seeder) {
		if ((_seed != null) && (_seederObjId == seeder.getObjectId())) {
			_seeded = true;

			int count = 1;
			for (int skillId : getTemplate().getSkills().keySet()) {
				switch (skillId) {
					case 4303: // Strong type x2
						count *= 2;
						break;
					case 4304: // Strong type x3
						count *= 3;
						break;
					case 4305: // Strong type x4
						count *= 4;
						break;
					case 4306: // Strong type x5
						count *= 5;
						break;
					case 4307: // Strong type x6
						count *= 6;
						break;
					case 4308: // Strong type x7
						count *= 7;
						break;
					case 4309: // Strong type x8
						count *= 8;
						break;
					case 4310: // Strong type x9
						count *= 9;
						break;
				}
			}

			// hi-lvl mobs bonus
			final int diff = getLevel() - _seed.getLevel() - 5;
			if (diff > 0) {
				count += diff;
			}
			_harvestItem.set(new ItemHolder(_seed.getCropId(), count * RatesConfig.RATE_DROP_MANOR));
		}
	}

	/**
	 * Sets the seed parameters, but not the seed state
	 *
	 * @param seed   - instance {@link L2Seed} of used seed
	 * @param seeder - player who sows the seed
	 */
	public final void setSeeded(L2Seed seed, PlayerInstance seeder) {
		if (!_seeded) {
			_seed = seed;
			_seederObjId = seeder.getObjectId();
		}
	}

	public final int getSeederId() {
		return _seederObjId;
	}

	public final L2Seed getSeed() {
		return _seed;
	}

	public final boolean isSeeded() {
		return _seeded;
	}

	/**
	 * Check if the server allows Random Animation.
	 */
	// This is located here because L2Monster and L2FriendlyMob both extend this class. The other non-pc instances extend either L2NpcInstance or L2MonsterInstance.
	@Override
	public boolean hasRandomAnimation() {
		return ((GeneralConfig.MAX_MONSTER_ANIMATION > 0) && isRandomAnimationEnabled() && !(this instanceof L2GrandBossInstance));
	}

	public void setCommandChannelTimer(CommandChannelTimer commandChannelTimer) {
		_commandChannelTimer = commandChannelTimer;
	}

	public CommandChannelTimer getCommandChannelTimer() {
		return _commandChannelTimer;
	}

	public CommandChannel getFirstCommandChannelAttacked() {
		return _firstCommandChannelAttacked;
	}

	public void setFirstCommandChannelAttacked(CommandChannel firstCommandChannelAttacked) {
		_firstCommandChannelAttacked = firstCommandChannelAttacked;
	}

	/**
	 * @return the _commandChannelLastAttack
	 */
	public long getCommandChannelLastAttack() {
		return _commandChannelLastAttack;
	}

	/**
	 * @param channelLastAttack the _commandChannelLastAttack to set
	 */
	public void setCommandChannelLastAttack(long channelLastAttack) {
		_commandChannelLastAttack = channelLastAttack;
	}

	public void returnHome() {
		clearAggroList();

		if (hasAI() && (getSpawn() != null)) {
			getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, getSpawn());
		}
	}

	/*
	 * Return vitality points decrease (if positive) or increase (if negative) based on damage. Maximum for damage = maxHp.
	 */
	public int getVitalityPoints(int level, double exp, boolean isBoss) {
		if ((getLevel() <= 0) || (getExpReward() <= 0)) {
			return 0;
		}

		int points;
		if (level < 85) {
			points = Math.max((int) ((exp / 1000) * Math.max(level - getLevel(), 1)), 1);
		} else {
			points = Math.max((int) ((exp / (isBoss ? NpcConfig.VITALITY_CONSUME_BY_BOSS : NpcConfig.VITALITY_CONSUME_BY_MOB)) * Math.max(level - getLevel(), 1)), 1);
		}

		return -points;
	}

	/*
	 * True if vitality rate for exp and sp should be applied
	 */
	public boolean useVitalityRate() {
		return !isChampion() || L2JModsConfig.L2JMOD_CHAMPION_ENABLE_VITALITY;
	}

	/**
	 * Return True if the L2Character is RaidBoss or his minion.
	 */
	@Override
	public boolean isRaid() {
		return _isRaid;
	}

	/**
	 * Set this Npc as a Raid instance.
	 *
	 * @param isRaid
	 */
	public void setIsRaid(boolean isRaid) {
		_isRaid = isRaid;
	}

	/**
	 * Set this Npc as a Minion instance.
	 *
	 * @param val
	 */
	public void setIsRaidMinion(boolean val) {
		_isRaid = val;
		_isRaidMinion = val;
	}

	@Override
	public boolean isRaidMinion() {
		return _isRaidMinion;
	}

	@Override
	public boolean isMinion() {
		return getLeader() != null;
	}

	/**
	 * @return leader of this minion or null.
	 */
	public Attackable getLeader() {
		return null;
	}

	public void setChampion(boolean champ) {
		_champion = champ;
	}

	@Override
	public boolean isChampion() {
		return _champion;
	}

	@Override
	public boolean isAttackable() {
		return true;
	}

	@Override
	public Attackable asAttackable() {
		return this;
	}

	@Override
	public void setTarget(WorldObject object) {
		if (isDead()) {
			return;
		}

		if (object == null) {
			final WorldObject target = getTarget();
			final Map<Creature, AggroInfo> aggroList = _aggroList;
			if (target != null) {
				if (aggroList != null) {
					aggroList.remove(target);
				}
			}
			if ((aggroList != null) && aggroList.isEmpty()) {
				if (getAI() instanceof AttackableAI) {
					((AttackableAI) getAI()).setGlobalAggro(-25);
				}
				setWalking();
				clearAggroList();
			}
		}
		super.setTarget(object);
	}
}
