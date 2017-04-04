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
package org.l2junity.gameserver.ai;

import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.commons.util.Rnd;
import org.l2junity.core.configs.L2JModsConfig;
import org.l2junity.core.configs.NpcConfig;
import org.l2junity.gameserver.enums.AISkillScope;
import org.l2junity.gameserver.geodata.GeoData;
import org.l2junity.gameserver.instancemanager.GameTimeManager;
import org.l2junity.gameserver.model.AggroInfo;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.World;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Attackable;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.Playable;
import org.l2junity.gameserver.model.actor.instance.*;
import org.l2junity.gameserver.model.effects.L2EffectType;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.character.npc.OnAttackableFactionCall;
import org.l2junity.gameserver.model.events.impl.character.npc.OnAttackableHate;
import org.l2junity.gameserver.model.events.returns.TerminateReturn;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.skills.SkillCaster;
import org.l2junity.gameserver.model.zone.ZoneId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.l2junity.gameserver.ai.CtrlIntention.*;

/**
 * This class manages AI of L2Attackable.
 */
public class AttackableAI extends CharacterAI implements Runnable {
	private static final Logger LOGGER = LoggerFactory.getLogger(AttackableAI.class);

	private static final int RANDOM_WALK_RATE = 30; // confirmed
	// private static final int MAX_DRIFT_RANGE = 300;
	private static final int MAX_ATTACK_TIMEOUT = 1200; // int ticks, i.e. 2min
	/**
	 * The L2Attackable AI task executed every 1s (call onEvtThink method).
	 */
	private Future<?> _aiTask;
	/**
	 * The delay after which the attacked is stopped.
	 */
	private int _attackTimeout;
	/**
	 * The L2Attackable aggro counter.
	 */
	private int _globalAggro;
	/**
	 * The flag used to indicate that a thinking action is in progress, to prevent recursive thinking.
	 */
	private boolean _thinking;

	private int chaostime = 0;
	int lastBuffTick;

	public AttackableAI(Attackable attackable) {
		super(attackable);
		_attackTimeout = Integer.MAX_VALUE;
		_globalAggro = -10; // 10 seconds timeout of ATTACK after respawn
	}

	@Override
	public void run() {
		// Launch actions corresponding to the Event Think
		onEvtThink();
	}

	/**
	 * @param target The targeted WorldObject
	 * @return {@code true} if target can be auto attacked due aggression.
	 */
	private boolean isAggressiveTowards(Creature target) {
		if ((target == null) || (getActiveChar() == null)) {
			return false;
		}

		// Check if the target isn't invulnerable
		if (target.isInvul()) {
			return false;
		}

		// Check if the target isn't a Folk or a Door
		if (target.isDoor()) {
			return false;
		}

		final Attackable me = getActiveChar();

		// Check if the target isn't dead, is in the Aggro range and is at the same height
		if (target.isAlikeDead()) {
			return false;
		}

		// Check if the target is a L2Playable
		if (target.isPlayable()) {
			// Check if the AI isn't a Raid Boss, can See Silent Moving players and the target isn't in silent move mode
			if (!(me.isRaid()) && !(me.canSeeThroughSilentMove()) && ((Playable) target).isSilentMovingAffected()) {
				return false;
			}
		}

		// Gets the player if there is any.
		final Player player = target.getActingPlayer();
		if (player != null) {
			// Don't take the aggro if the GM has the access level below or equal to GM_DONT_TAKE_AGGRO
			if (!player.getAccessLevel().canTakeAggro()) {
				return false;
			}

			// check if the target is within the grace period for JUST getting up from fake death
			if (player.isRecentFakeDeath()) {
				return false;
			}
		} else if (me.isMonster()) {
			// depending on config, do not allow mobs to attack _new_ players in peacezones,
			// unless they are already following those players from outside the peacezone.
			if (!NpcConfig.ALT_MOB_AGRO_IN_PEACEZONE && target.isInsideZone(ZoneId.PEACE)) {
				return false;
			}

			if (me.isChampion() && L2JModsConfig.L2JMOD_CHAMPION_PASSIVE) {
				return false;
			}

			if (!me.isAggressive()) {
				return false;
			}
		}

		return target.isAutoAttackable(me) && GeoData.getInstance().canSeeTarget(me, target);
	}

	public void startAITask() {
		// If not idle - create an AI task (schedule onEvtThink repeatedly)
		if (_aiTask == null) {
			_aiTask = ThreadPool.getInstance().scheduleGeneralAtFixedRate(this, 1000, 1000, TimeUnit.MILLISECONDS);
		}
	}

	@Override
	public void stopAITask() {
		if (_aiTask != null) {
			_aiTask.cancel(false);
			_aiTask = null;
		}
		super.stopAITask();
	}

	/**
	 * Set the Intention of this L2CharacterAI and create an AI Task executed every 1s (call onEvtThink method) for this L2Attackable.<br>
	 * <FONT COLOR=#FF0000><B> <U>Caution</U> : If actor _knowPlayer isn't EMPTY, AI_INTENTION_IDLE will be change in AI_INTENTION_ACTIVE</B></FONT>
	 *
	 * @param intention The new Intention to set to the AI
	 * @param args      The first parameter of the Intention
	 */
	@Override
	synchronized void changeIntention(CtrlIntention intention, Object... args) {
		if ((intention == AI_INTENTION_IDLE) || (intention == AI_INTENTION_ACTIVE)) {
			// Check if actor is not dead
			Attackable npc = getActiveChar();
			if (!npc.isAlikeDead()) {
				// If its _knownPlayer isn't empty set the Intention to AI_INTENTION_ACTIVE
				if (!World.getInstance().getVisibleObjects(npc, Player.class).isEmpty()) {
					intention = AI_INTENTION_ACTIVE;
				} else {
					if (npc.getSpawn() != null) {
						if (!npc.isInRadius3d(npc.getSpawn(), NpcConfig.MAX_DRIFT_RANGE + NpcConfig.MAX_DRIFT_RANGE)) {
							intention = AI_INTENTION_ACTIVE;
						}
					}
				}
			}

			if (intention == AI_INTENTION_IDLE) {
				// Set the Intention of this L2AttackableAI to AI_INTENTION_IDLE
				super.changeIntention(AI_INTENTION_IDLE);

				stopAITask();

				// Cancel the AI
				_actor.detachAI();

				return;
			}
		}

		// Set the Intention of this L2AttackableAI to intention
		super.changeIntention(intention, args);

		// If not idle - create an AI task (schedule onEvtThink repeatedly)
		startAITask();
	}

	@Override
	protected void changeIntentionToCast(Skill skill, WorldObject target, ItemInstance item, boolean forceUse, boolean dontMove) {
		// Set the AI cast target
		setTarget(target);
		super.changeIntentionToCast(skill, target, item, forceUse, dontMove);
	}

	/**
	 * Manage the Attack Intention : Stop current Attack (if necessary), Calculate attack timeout, Start a new Attack and Launch Think Event.
	 *
	 * @param target The L2Character to attack
	 */
	@Override
	protected void onIntentionAttack(Creature target) {
		// Calculate the attack timeout
		_attackTimeout = MAX_ATTACK_TIMEOUT + GameTimeManager.getInstance().getGameTicks();

		// self and buffs
		if ((lastBuffTick + 30) < GameTimeManager.getInstance().getGameTicks()) {
			for (Skill buff : getActiveChar().getTemplate().getAISkills(AISkillScope.BUFF)) {
				target = skillTargetReconsider(buff, true);
				if (target != null) {
					setTarget(target);
					_actor.doCast(buff);
					LOGGER.debug("{} used buff skill {} on {}", this, buff, _actor);
					break;
				}
			}
			lastBuffTick = GameTimeManager.getInstance().getGameTicks();
		}

		// Manage the Attack Intention : Stop current Attack (if necessary), Start a new Attack and Launch Think Event
		super.onIntentionAttack(target);
	}

	protected void thinkCast() {
		final WorldObject target = _skill.getTarget(_actor, getTarget(), _forceUse, _dontMove, false);
		if (checkTargetLost(target)) {
			return;
		}
		if (maybeMoveToPawn(target, _actor.getMagicalAttackRange(_skill))) {
			return;
		}
		setIntention(AI_INTENTION_ACTIVE);
		_actor.doCast(_skill, _item, _forceUse, _dontMove);
	}

	/**
	 * Manage AI standard thinks of a L2Attackable (called by onEvtThink). <B><U> Actions</U> :</B>
	 * <ul>
	 * <li>Update every 1s the _globalAggro counter to come close to 0</li>
	 * <li>If the actor is Aggressive and can attack, add all autoAttackable L2Character in its Aggro Range to its _aggroList, chose a target and order to attack it</li>
	 * <li>If the actor is a GuardInstance that can't attack, order to it to return to its home location</li>
	 * <li>If the actor is a MonsterInstance that can't attack, order to it to random walk (1/100)</li>
	 * </ul>
	 */
	protected void thinkActive() {
		final Attackable npc = getActiveChar();
		WorldObject target = getTarget();
		// Update every 1s the _globalAggro counter to come close to 0
		if (_globalAggro != 0) {
			if (_globalAggro < 0) {
				_globalAggro++;
			} else {
				_globalAggro--;
			}
		}

		// Add all autoAttackable L2Character in L2Attackable Aggro Range to its _aggroList with 0 damage and 1 hate
		// A L2Attackable isn't aggressive during 10s after its spawn because _globalAggro is set to -10
		if (_globalAggro >= 0) {
			if (npc.isAggressive() || (npc instanceof GuardInstance)) {
				final int range = npc instanceof GuardInstance ? 500 : npc.getAggroRange(); // TODO Make sure how guards behave towards players.
				World.getInstance().forEachVisibleObjectInRadius(npc, Creature.class, range, t ->
				{
					// For each L2Character check if the target is autoattackable
					if (isAggressiveTowards(t)) // check aggression
					{
						if (t.isPlayable()) {
							final TerminateReturn term = EventDispatcher.getInstance().notifyEvent(new OnAttackableHate(getActiveChar(), t.getActingPlayer(), t.isSummon()), getActiveChar(), TerminateReturn.class);
							if ((term != null) && term.terminate()) {
								return;
							}
						}

						// Get the hate level of the L2Attackable against this L2Character target contained in _aggroList
						int hating = npc.getHating(t);

						// Add the attacker to the L2Attackable _aggroList with 0 damage and 1 hate
						if (hating == 0) {
							npc.addDamageHate(t, 0, 1);
						}
					}
				});
			}

			// Chose a target from its aggroList
			Creature hated;
			if (npc.isConfused() && (target != null) && target.isCreature()) {
				hated = (Creature) target; // effect handles selection
			} else {
				hated = npc.getMostHated();
			}

			// Order to the L2Attackable to attack the target
			if ((hated != null) && !npc.isCoreAIDisabled()) {
				// Get the hate level of the L2Attackable against this L2Character target contained in _aggroList
				int aggro = npc.getHating(hated);

				if ((aggro + _globalAggro) > 0) {
					// Set the L2Character movement type to run and send Server->Client packet ChangeMoveType to all others L2PcInstance
					if (!npc.isRunning()) {
						npc.setRunning();
					}

					// Set the AI Intention to AI_INTENTION_ATTACK
					setIntention(CtrlIntention.AI_INTENTION_ATTACK, hated);
				}

				return;
			}
		}

		// Chance to forget attackers after some time
		if ((npc.getCurrentHp() == npc.getMaxHp()) && (npc.getCurrentMp() == npc.getMaxMp()) && !npc.getAttackByList().isEmpty() && (Rnd.nextInt(500) == 0) && npc.canStopAttackByTime()) {
			npc.clearAggroList();
			npc.getAttackByList().clear();
			if (npc.isMonster()) {
				if (((MonsterInstance) npc).hasMinions()) {
					((MonsterInstance) npc).getMinionList().deleteReusedMinions();
				}
			}
		}

		// Check if the mob should not return to spawn point
		if (!npc.canReturnToSpawnPoint()) {
			return;
		}

		// Check if the actor is a GuardInstance
		if ((npc instanceof GuardInstance) && !npc.isWalker()) {
			// Order to the GuardInstance to return to its home location because there's no target to attack
			npc.returnHome();
		}

		// Minions following leader
		final Creature leader = npc.getLeader();
		if ((leader != null) && !leader.isAlikeDead()) {
			final int offset;
			final int minRadius = 30;

			if (npc.isRaidMinion()) {
				offset = 500; // for Raids - need correction
			} else {
				offset = 200; // for normal minions - need correction :)
			}

			if (leader.isRunning()) {
				npc.setRunning();
			} else {
				npc.setWalking();
			}

			if (npc.distance2d(leader) > offset) {
				double x1, y1, z1;
				x1 = Rnd.get(minRadius * 2, offset * 2); // x
				y1 = Rnd.get((int) x1, offset * 2); // distance
				y1 = (int) Math.sqrt((y1 * y1) - (x1 * x1)); // y
				if (x1 > (offset + minRadius)) {
					x1 = (leader.getX() + x1) - offset;
				} else {
					x1 = (leader.getX() - x1) + minRadius;
				}
				if (y1 > (offset + minRadius)) {
					y1 = (leader.getY() + y1) - offset;
				} else {
					y1 = (leader.getY() - y1) + minRadius;
				}

				z1 = leader.getZ();
				// Move the actor to Location (x,y,z) server side AND client side by sending Server->Client packet CharMoveToLocation (broadcast)
				moveTo(x1, y1, z1);
				return;
			} else if (Rnd.nextInt(RANDOM_WALK_RATE) == 0) {
				for (Skill sk : npc.getTemplate().getAISkills(AISkillScope.BUFF)) {
					target = skillTargetReconsider(sk, true);
					if (target != null) {
						setTarget(target);
						npc.doCast(sk);
						return;
					}
				}
			}
		}
		// Order to the MonsterInstance to random walk (1/100)
		else if ((npc.getSpawn() != null) && (Rnd.nextInt(RANDOM_WALK_RATE) == 0) && npc.isRandomWalkingEnabled()) {
			double x1 = 0;
			double y1 = 0;
			double z1 = 0;
			final int range = NpcConfig.MAX_DRIFT_RANGE;

			for (Skill sk : npc.getTemplate().getAISkills(AISkillScope.BUFF)) {
				target = skillTargetReconsider(sk, true);
				if (target != null) {
					setTarget(target);
					npc.doCast(sk);
					return;
				}
			}

			x1 = npc.getSpawn().getX();
			y1 = npc.getSpawn().getY();
			z1 = npc.getSpawn().getZ();

			if (!npc.isInRadius2d(npc.getSpawn(), range)) {
				npc.setisReturningToSpawnPoint(true);
			} else {
				int deltaX = Rnd.nextInt(range * 2); // x
				int deltaY = Rnd.get(deltaX, range * 2); // distance
				deltaY = (int) Math.sqrt((deltaY * deltaY) - (deltaX * deltaX)); // y
				x1 = (deltaX + x1) - range;
				y1 = (deltaY + y1) - range;
				z1 = npc.getZ();
			}

			// Move the actor to Location (x,y,z) server side AND client side by sending Server->Client packet CharMoveToLocation (broadcast)
			final Location moveLoc = GeoData.getInstance().moveCheck(npc.getX(), npc.getY(), npc.getZ(), x1, y1, z1, npc.getInstanceWorld());

			moveTo(moveLoc.getX(), moveLoc.getY(), moveLoc.getZ());
		}
	}

	/**
	 * Manage AI attack thinks of a L2Attackable (called by onEvtThink). <B><U> Actions</U> :</B>
	 * <ul>
	 * <li>Update the attack timeout if actor is running</li>
	 * <li>If target is dead or timeout is expired, stop this attack and set the Intention to AI_INTENTION_ACTIVE</li>
	 * <li>Call all L2Object of its Faction inside the Faction Range</li>
	 * <li>Chose a target and order to attack it with magic skill or physical attack</li>
	 * </ul>
	 * TODO: Manage casting rules to healer mobs (like Ant Nurses)
	 */
	protected void thinkAttack() {
		final Attackable npc = getActiveChar();

		if (npc.isCastingNow()) {
			return;
		}

		Creature target = npc.getMostHated();
		if (getTarget() != target) {
			setTarget(target);
		}

		// Check if target is dead or if timeout is expired to stop this attack
		if ((target == null) || target.isAlikeDead() || ((_attackTimeout < GameTimeManager.getInstance().getGameTicks()) && npc.canStopAttackByTime())) {
			// Stop hating this target after the attack timeout or if target is dead
			npc.stopHating(target);

			// Set the AI Intention to AI_INTENTION_ACTIVE
			setIntention(AI_INTENTION_ACTIVE);

			npc.setWalking();
			return;
		}

		final int collision = npc.getTemplate().getCollisionRadius();

		// Handle all L2Object of its Faction inside the Faction Range

		Set<Integer> clans = getActiveChar().getTemplate().getClans();
		if ((clans != null) && !clans.isEmpty()) {
			final int factionRange = npc.getTemplate().getClanHelpRange() + collision;
			// Go through all L2Object that belong to its faction
			try {
				final Creature finalTarget = target;
				World.getInstance().forEachVisibleObjectInRadius(npc, Npc.class, factionRange, called ->
				{
					if (!getActiveChar().getTemplate().isClan(called.getTemplate().getClans())) {
						return;
					}

					// Check if the L2Object is inside the Faction Range of the actor
					if (called.hasAI()) {
						if ((Math.abs(finalTarget.getZ() - called.getZ()) < 600) && npc.getAttackByList().contains(finalTarget) && ((called.getAI()._intention == CtrlIntention.AI_INTENTION_IDLE) || (called.getAI()._intention == CtrlIntention.AI_INTENTION_ACTIVE))) {
							if (finalTarget.isPlayable()) {
								// By default, when a faction member calls for help, attack the caller's attacker.
								// Notify the AI with EVT_AGGRESSION
								called.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, finalTarget, 1);
								EventDispatcher.getInstance().notifyEventAsync(new OnAttackableFactionCall(called, getActiveChar(), finalTarget.getActingPlayer(), finalTarget.isSummon()), called);
							} else if (called.isAttackable() && (called.getAI()._intention != CtrlIntention.AI_INTENTION_ATTACK)) {
								((Attackable) called).addDamageHate(finalTarget, 0, npc.getHating(finalTarget));
								called.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, finalTarget);
							}
						}
					}
				});
			} catch (NullPointerException e) {
				LOGGER.warn(getClass().getSimpleName() + ": thinkAttack() faction call failed: " + e.getMessage());
			}
		}

		if (npc.isCoreAIDisabled()) {
			return;
		}

		final int combinedCollision = collision + target.getTemplate().getCollisionRadius();

		final List<Skill> aiSuicideSkills = npc.getTemplate().getAISkills(AISkillScope.SUICIDE);
		if (!aiSuicideSkills.isEmpty() && ((int) ((npc.getCurrentHp() / npc.getMaxHp()) * 100) < 30) && npc.hasSkillChance()) {
			final Skill skill = aiSuicideSkills.get(Rnd.get(aiSuicideSkills.size()));
			if (SkillCaster.checkUseConditions(npc, skill) && checkSkillTarget(skill, target)) {
				npc.doCast(skill);
				LOGGER.debug("{} used suicide skill {}", this, skill);
				return;
			}
		}

		// ------------------------------------------------------
		// In case many mobs are trying to hit from same place, move a bit,
		// circling around the target
		// Note from Gnacik:
		// On l2js because of that sometimes mobs don't attack player only running
		// around player without any sense, so decrease chance for now
		if (!npc.isMovementDisabled() && (Rnd.nextInt(100) <= 3)) {
			for (Attackable nearby : World.getInstance().getVisibleObjects(npc, Attackable.class)) {
				if (npc.isInRadius2d(nearby, collision) && (nearby != target)) {
					double newX = combinedCollision + Rnd.get(40);
					if (Rnd.nextBoolean()) {
						newX = target.getX() + newX;
					} else {
						newX = target.getX() - newX;
					}
					double newY = combinedCollision + Rnd.get(40);
					if (Rnd.nextBoolean()) {
						newY = target.getY() + newY;
					} else {
						newY = target.getY() - newY;
					}

					if (!npc.isInRadius2d(newX, newY, collision)) {
						double newZ = npc.getZ() + 30;
						if (GeoData.getInstance().canMove(npc, newX, newY, newZ)) {
							moveTo(newX, newY, newZ);
						}
					}
					return;
				}
			}
		}
		// Dodge if its needed
		if (!npc.isMovementDisabled() && (npc.getTemplate().getDodge() > 0)) {
			if (Rnd.get(100) <= npc.getTemplate().getDodge()) {
				// Micht: kepping this one otherwise we should do 2 sqrt
				if (npc.distance2d(target) <= (60 + combinedCollision)) {
					double posX = npc.getX();
					double posY = npc.getY();
					double posZ = npc.getZ() + 30;

					if (target.getX() < posX) {
						posX = posX + 300;
					} else {
						posX = posX - 300;
					}

					if (target.getY() < posY) {
						posY = posY + 300;
					} else {
						posY = posY - 300;
					}

					if (GeoData.getInstance().canMove(npc, posX, posY, posZ)) {
						setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, new Location(posX, posY, posZ, 0));
					}
					return;
				}
			}
		}

		// ------------------------------------------------------------------------------
		// BOSS/Raid Minion Target Reconsider
		if (npc.isRaid() || npc.isRaidMinion()) {
			chaostime++;
			boolean changeTarget = false;
			if ((npc instanceof RaidBossInstance) && (chaostime > NpcConfig.RAID_CHAOS_TIME)) {
				double multiplier = ((MonsterInstance) npc).hasMinions() ? 200 : 100;
				changeTarget = Rnd.get(100) <= (100 - ((npc.getCurrentHp() * multiplier) / npc.getMaxHp()));
			} else if ((npc instanceof GrandBossInstance) && (chaostime > NpcConfig.GRAND_CHAOS_TIME)) {
				double chaosRate = 100 - ((npc.getCurrentHp() * 300) / npc.getMaxHp());
				changeTarget = ((chaosRate <= 10) && (Rnd.get(100) <= 10)) || ((chaosRate > 10) && (Rnd.get(100) <= chaosRate));
			} else if (chaostime > NpcConfig.MINION_CHAOS_TIME) {
				changeTarget = Rnd.get(100) <= (100 - ((npc.getCurrentHp() * 200) / npc.getMaxHp()));
			}

			if (changeTarget) {
				target = targetReconsider(true);
				if (target != null) {
					setTarget(target);
					chaostime = 0;
					return;
				}
			}
		}

		if (target == null) {
			target = targetReconsider(false);
			if (target == null) {
				return;
			}

			setTarget(target);
		}

		if (npc.hasSkillChance()) {
			// First use the most important skill - heal. Even reconsider target.
			if (!npc.getTemplate().getAISkills(AISkillScope.HEAL).isEmpty()) {
				final Skill healSkill = npc.getTemplate().getAISkills(AISkillScope.HEAL).get(Rnd.get(npc.getTemplate().getAISkills(AISkillScope.HEAL).size()));
				if (SkillCaster.checkUseConditions(npc, healSkill)) {
					final Creature healTarget = skillTargetReconsider(healSkill, false);
					if (healTarget != null) {
						double healChance = (100 - healTarget.getCurrentHpPercent()) * 1.5; // Ensure heal chance is always 100% if HP is below 33%.
						if ((Rnd.get(100) < healChance) && checkSkillTarget(healSkill, healTarget)) {
							setTarget(healTarget);
							npc.doCast(healSkill);
							LOGGER.debug("{} used heal skill {} with target {}", this, healSkill, getTarget());
							return;
						}
					}
				}
			}

			// Then use the second most important skill - buff. Even reconsider target.
			if (!npc.getTemplate().getAISkills(AISkillScope.BUFF).isEmpty()) {
				final Skill buffSkill = npc.getTemplate().getAISkills(AISkillScope.BUFF).get(Rnd.get(npc.getTemplate().getAISkills(AISkillScope.BUFF).size()));
				if (SkillCaster.checkUseConditions(npc, buffSkill)) {
					Creature buffTarget = skillTargetReconsider(buffSkill, true);
					if (checkSkillTarget(buffSkill, buffTarget)) {
						setTarget(buffTarget);
						npc.doCast(buffSkill);
						LOGGER.debug("{} used buff skill {} with target {}", this, buffSkill, getTarget());
						return;
					}
				}
			}

			// Then try to immobolize target if moving.
			if (target.isMoving() && !npc.getTemplate().getAISkills(AISkillScope.IMMOBILIZE).isEmpty()) {
				final Skill immobolizeSkill = npc.getTemplate().getAISkills(AISkillScope.IMMOBILIZE).get(Rnd.get(npc.getTemplate().getAISkills(AISkillScope.IMMOBILIZE).size()));
				if (SkillCaster.checkUseConditions(npc, immobolizeSkill) && checkSkillTarget(immobolizeSkill, target)) {
					npc.doCast(immobolizeSkill);
					LOGGER.debug("{} used immobolize skill {} with target {}", this, immobolizeSkill, getTarget());
					return;
				}
			}

			// Then try to mute target if he is casting.
			if (target.isCastingNow() && !npc.getTemplate().getAISkills(AISkillScope.COT).isEmpty()) {
				final Skill muteSkill = npc.getTemplate().getAISkills(AISkillScope.COT).get(Rnd.get(npc.getTemplate().getAISkills(AISkillScope.COT).size()));
				if (SkillCaster.checkUseConditions(npc, muteSkill) && checkSkillTarget(muteSkill, target)) {
					npc.doCast(muteSkill);
					LOGGER.debug("{} used mute skill {} with target {}", this, muteSkill, getTarget());
					return;
				}
			}

			// Try cast short range skill.
			if (!npc.getShortRangeSkills().isEmpty()) {
				final Skill shortRangeSkill = npc.getShortRangeSkills().get(Rnd.get(npc.getShortRangeSkills().size()));
				if (SkillCaster.checkUseConditions(npc, shortRangeSkill) && checkSkillTarget(shortRangeSkill, target)) {
					npc.doCast(shortRangeSkill);
					LOGGER.debug("{} used short range skill {} with target {}", this, shortRangeSkill, getTarget());
					return;
				}
			}

			// Try cast long range skill.
			if (!npc.getLongRangeSkills().isEmpty()) {
				final Skill longRangeSkill = npc.getLongRangeSkills().get(Rnd.get(npc.getLongRangeSkills().size()));
				if (SkillCaster.checkUseConditions(npc, longRangeSkill) && checkSkillTarget(longRangeSkill, target)) {
					npc.doCast(longRangeSkill);
					LOGGER.debug("{} used long range skill {} with target {}", this, longRangeSkill, getTarget());
					return;
				}
			}

			// Finally, if none succeed, try to cast any skill.
			if (!npc.getTemplate().getAISkills(AISkillScope.GENERAL).isEmpty()) {
				final Skill generalSkill = npc.getTemplate().getAISkills(AISkillScope.GENERAL).get(Rnd.get(npc.getTemplate().getAISkills(AISkillScope.GENERAL).size()));
				if (SkillCaster.checkUseConditions(npc, generalSkill) && checkSkillTarget(generalSkill, target)) {
					npc.doCast(generalSkill);
					LOGGER.debug("{} used general skill {} with target {}", this, generalSkill, getTarget());
					return;
				}
			}
		}

		// Check if target is within range or move.
		int range = npc.getPhysicalAttackRange() + combinedCollision;
		if (npc.distance2d(target) > range) {
			if (checkTarget(target)) {
				moveToPawn(target, range);
				return;
			}

			target = targetReconsider(false);
			if (target == null) {
				return;
			}

			setTarget(target);
		}

		// Attacks target
		_actor.doAutoAttack(target);
	}

	private boolean checkSkillTarget(Skill skill, WorldObject target) {
		if (target == null) {
			return false;
		}

		// Check if target is valid and within cast range.
		if (skill.getTarget(getActiveChar(), target, false, getActiveChar().isMovementDisabled(), false) == null) {
			return false;
		}

		if (target.isCreature()) {
			// Skip if target is already affected by such skill.
			if (skill.isContinuous()) {
				if (((Creature) target).getEffectList().hasAbnormalType(skill.getAbnormalType(), i -> (i.getSkill().getAbnormalLvl() >= skill.getAbnormalLvl()))) {
					return false;
				}

				// There are cases where bad skills (negative effect points) are actually buffs and NPCs cast them on players, but they shouldn't.
				if ((!skill.isDebuff() || !skill.isBad()) && target.isAutoAttackable(getActiveChar())) {
					return false;
				}
			}

			// Check if target had buffs if skill is bad cancel, or debuffs if skill is good cancel.
			if (skill.hasEffectType(L2EffectType.DISPEL, L2EffectType.DISPEL_BY_SLOT)) {
				if (skill.isBad()) {
					if (((Creature) target).getEffectList().getBuffCount() == 0) {
						return false;
					}
				} else if (((Creature) target).getEffectList().getDebuffCount() == 0) {
					return false;
				}
			}

			// Check for damaged targets if using healing skill.
			if ((((Creature) target).getCurrentHp() == ((Creature) target).getMaxHp()) && skill.hasEffectType(L2EffectType.HEAL)) {
				return false;
			}
		}

		return true;
	}

	private boolean checkTarget(WorldObject target) {
		if (target == null) {
			return false;
		}

		final Attackable npc = getActiveChar();
		if (target.isCreature()) {
			if (((Creature) target).isDead()) {
				return false;
			}

			if (npc.isMovementDisabled()) {
				if (!npc.isInRadius2d(target, npc.getPhysicalAttackRange() + npc.getTemplate().getCollisionRadius() + ((Creature) target).getTemplate().getCollisionRadius())) {
					return false;
				}

				if (!GeoData.getInstance().canSeeTarget(npc, target)) {
					return false;
				}
			}

			if (!target.isAutoAttackable(npc)) {
				return false;
			}
		}

		return GeoData.getInstance().canMove(npc, target);
	}

	private Creature skillTargetReconsider(Skill skill, boolean insideCastRange) {
		// Check if skill can be casted.
		final Attackable npc = getActiveChar();
		if (!SkillCaster.checkUseConditions(npc, skill)) {
			return null;
		}

		// There are cases where bad skills (negative effect points) are actually buffs and NPCs cast them on players, but they shouldn't.
		final boolean isBad = skill.isContinuous() ? skill.isDebuff() : skill.isBad();

		// Check current target first.
		final int range = insideCastRange ? skill.getCastRange() + getActiveChar().getTemplate().getCollisionRadius() : 2000; // TODO need some forget range

		Stream<Creature> stream;
		if (isBad) {
			//@formatter:off
			stream = npc.getAggroList().values().stream()
					.map(AggroInfo::getAttacker)
					.filter(c -> checkSkillTarget(skill, c))
					.sorted(Comparator.<Creature>comparingInt(npc::getHating).reversed());
			//@formatter:on
		} else {
			stream = World.getInstance().getVisibleObjects(npc, Creature.class, range, c -> checkSkillTarget(skill, c)).stream();

			// Maybe add self to the list of targets since getVisibleObjects doesn't return yourself.
			if (checkSkillTarget(skill, npc)) {
				stream = Stream.concat(stream, Stream.of(npc));
			}

			// For heal skills sort by hp missing.
			if (skill.hasEffectType(L2EffectType.HEAL)) {
				stream = stream.sorted(Comparator.comparingInt(Creature::getCurrentHpPercent));
			}
		}

		// Return any target.
		return stream.findFirst().orElse(null);

	}

	private Creature targetReconsider(boolean randomTarget) {
		final Attackable npc = getActiveChar();

		if (randomTarget) {
			Stream<Creature> stream = npc.getAggroList().values().stream().map(AggroInfo::getAttacker).filter(this::checkTarget);

			// If npc is aggressive, add characters within aggro range too
			if (npc.isAggressive()) {
				stream = Stream.concat(stream, World.getInstance().getVisibleObjects(npc, Creature.class, npc.getAggroRange(), this::checkTarget).stream());
			}

			return stream.findAny().orElse(null);
		}

		//@formatter:off
		return npc.getAggroList().values().stream()
				.filter(a -> checkTarget(a.getAttacker()))
				.sorted(Comparator.comparingInt(AggroInfo::getHate))
				.map(AggroInfo::getAttacker)
				.findFirst()
				.orElse(npc.isAggressive() ? World.getInstance().getVisibleObjects(npc, Creature.class, npc.getAggroRange(), this::checkTarget).stream().findAny().orElse(null) : null);
		//@formatter:on
	}

	/**
	 * Manage AI thinking actions of a L2Attackable.
	 */
	@Override
	protected void onEvtThink() {
		// Check if the actor can't use skills and if a thinking action isn't already in progress
		if (_thinking || getActiveChar().isAllSkillsDisabled()) {
			return;
		}

		// Start thinking action
		_thinking = true;

		try {
			// Manage AI thinks of a L2Attackable
			switch (getIntention()) {
				case AI_INTENTION_ACTIVE:
					thinkActive();
					break;
				case AI_INTENTION_ATTACK:
					thinkAttack();
					break;
				case AI_INTENTION_CAST:
					thinkCast();
					break;
			}
		} catch (Exception e) {
			LOGGER.warn("{} -  onEvtThink() failed", this, e);
		} finally {
			// Stop thinking action
			_thinking = false;
		}
	}

	/**
	 * Launch actions corresponding to the Event Attacked.<br>
	 * <B><U> Actions</U> :</B>
	 * <ul>
	 * <li>Init the attack : Calculate the attack timeout, Set the _globalAggro to 0, Add the attacker to the actor _aggroList</li>
	 * <li>Set the L2Character movement type to run and send Server->Client packet ChangeMoveType to all others L2PcInstance</li>
	 * <li>Set the Intention to AI_INTENTION_ATTACK</li>
	 * </ul>
	 *
	 * @param attacker The L2Character that attacks the actor
	 */
	@Override
	protected void onEvtAttacked(Creature attacker) {
		final Attackable me = getActiveChar();
		final WorldObject target = getTarget();
		// Calculate the attack timeout
		_attackTimeout = MAX_ATTACK_TIMEOUT + GameTimeManager.getInstance().getGameTicks();

		// Set the _globalAggro to 0 to permit attack even just after spawn
		if (_globalAggro < 0) {
			_globalAggro = 0;
		}

		// Add the attacker to the _aggroList of the actor
		me.addDamageHate(attacker, 0, 1);

		// Set the L2Character movement type to run and send Server->Client packet ChangeMoveType to all others L2PcInstance
		if (!me.isRunning()) {
			me.setRunning();
		}

		if (!getActiveChar().isCoreAIDisabled()) {
			// Set the Intention to AI_INTENTION_ATTACK
			if (getIntention() != AI_INTENTION_ATTACK) {
				setIntention(CtrlIntention.AI_INTENTION_ATTACK, attacker);
			} else if (me.getMostHated() != target) {
				setIntention(CtrlIntention.AI_INTENTION_ATTACK, attacker);
			}
		}

		if (me.isMonster()) {
			MonsterInstance master = (MonsterInstance) me;

			if (master.hasMinions()) {
				master.getMinionList().onAssist(me, attacker);
			}

			master = master.getLeader();
			if ((master != null) && master.hasMinions()) {
				master.getMinionList().onAssist(me, attacker);
			}
		}

		super.onEvtAttacked(attacker);
	}

	/**
	 * Launch actions corresponding to the Event Aggression.<br>
	 * <B><U> Actions</U> :</B>
	 * <ul>
	 * <li>Add the target to the actor _aggroList or update hate if already present</li>
	 * <li>Set the actor Intention to AI_INTENTION_ATTACK (if actor is GuardInstance check if it isn't too far from its home location)</li>
	 * </ul>
	 *
	 * @param aggro The value of hate to add to the actor against the target
	 */
	@Override
	protected void onEvtAggression(Creature target, int aggro) {
		final Attackable me = getActiveChar();
		if (me.isDead()) {
			return;
		}

		if (target != null) {
			// Add the target to the actor _aggroList or update hate if already present
			me.addDamageHate(target, 0, aggro);

			// Set the actor AI Intention to AI_INTENTION_ATTACK
			if (getIntention() != CtrlIntention.AI_INTENTION_ATTACK) {
				// Set the L2Character movement type to run and send Server->Client packet ChangeMoveType to all others L2PcInstance
				if (!me.isRunning()) {
					me.setRunning();
				}

				setIntention(CtrlIntention.AI_INTENTION_ATTACK, target);
			}

			if (me.isMonster()) {
				MonsterInstance master = (MonsterInstance) me;

				if (master.hasMinions()) {
					master.getMinionList().onAssist(me, target);
				}

				master = master.getLeader();
				if ((master != null) && master.hasMinions()) {
					master.getMinionList().onAssist(me, target);
				}
			}
		}
	}

	@Override
	protected void onIntentionActive() {
		// Cancel attack timeout
		_attackTimeout = Integer.MAX_VALUE;
		super.onIntentionActive();
	}

	public void setGlobalAggro(int value) {
		_globalAggro = value;
	}

	@Override
	public void setTarget(WorldObject target) {
		// NPCs share their regular target with AI target.
		_actor.setTarget(target);
	}

	@Override
	public WorldObject getTarget() {
		// NPCs share their regular target with AI target.
		return _actor.getTarget();
	}

	public Attackable getActiveChar() {
		return (Attackable) _actor;
	}
}
