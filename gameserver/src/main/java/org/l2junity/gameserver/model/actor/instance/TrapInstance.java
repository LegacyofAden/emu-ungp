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
package org.l2junity.gameserver.model.actor.instance;

import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.gameserver.enums.InstanceType;
import org.l2junity.gameserver.enums.TrapAction;
import org.l2junity.gameserver.instancemanager.ZoneManager;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.tasks.npc.trap.TrapTask;
import org.l2junity.gameserver.model.actor.tasks.npc.trap.TrapTriggerTask;
import org.l2junity.gameserver.model.actor.templates.NpcTemplate;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.character.player.OnTrapAction;
import org.l2junity.gameserver.model.holders.SkillHolder;
import org.l2junity.gameserver.model.items.Weapon;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.olympiad.OlympiadGameManager;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.zone.ZoneId;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.s2c.AbstractNpcInfo.TrapInfo;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.gameserver.taskmanager.DecayTaskManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Trap instance.
 *
 * @author Zoey76
 */
public final class TrapInstance extends Npc {
	private static final int TICK = 1000; // 1s
	private boolean _isInArena = false;
	private boolean _isTriggered;
	private final int _lifeTime;
	private Player _owner;
	private final List<Integer> _playersWhoDetectedMe = new ArrayList<>();
	private final SkillHolder _skill;
	private int _remainingTime;
	// Tasks
	private ScheduledFuture<?> _trapTask = null;

	public TrapInstance(NpcTemplate template, int instanceId) {
		super(template);
		setInstanceType(InstanceType.L2TrapInstance);
		setInstanceById(instanceId);
		setName(template.getName());
		setIsInvul(false);
		_owner = null;
		_isTriggered = false;
		_skill = getParameters().getObject("trap_skill", SkillHolder.class);
		_lifeTime = 30000;
		_remainingTime = _lifeTime;
		if (_skill != null) {
			_trapTask = ThreadPool.getInstance().scheduleGeneralAtFixedRate(new TrapTask(this), TICK, TICK, TimeUnit.MILLISECONDS);
		}
	}

	public TrapInstance(NpcTemplate template, Player owner) {
		this(template, owner.getInstanceId());
		_owner = owner;
	}

	@Override
	public void broadcastPacket(GameServerPacket mov) {
		getWorld().forEachVisibleObject(this, Player.class, player ->
		{
			if (_isTriggered || canBeSeen(player)) {
				player.sendPacket(mov);
			}
		});
	}

	@Override
	public void broadcastPacket(GameServerPacket mov, int radiusInKnownlist) {
		getWorld().forEachVisibleObjectInRadius(this, Player.class, radiusInKnownlist, player ->
		{
			if (_isTriggered || canBeSeen(player)) {
				player.sendPacket(mov);
			}
		});
	}

	/**
	 * Verify if the character can see the trap.
	 *
	 * @param cha the character to verify
	 * @return {@code true} if the character can see the trap, {@code false} otherwise
	 */
	public boolean canBeSeen(Creature cha) {
		if ((cha != null) && _playersWhoDetectedMe.contains(cha.getObjectId())) {
			return true;
		}

		if ((_owner == null) || (cha == null)) {
			return false;
		}
		if (cha == _owner) {
			return true;
		}

		if (cha instanceof Player) {
			// observers can't see trap
			if (((Player) cha).inObserverMode()) {
				return false;
			}

			// olympiad competitors can't see trap
			if (_owner.isInOlympiadMode() && ((Player) cha).isInOlympiadMode() && (((Player) cha).getOlympiadSide() != _owner.getOlympiadSide())) {
				return false;
			}
		}

		if (_isInArena) {
			return true;
		}

		if (_owner.isInParty() && cha.isInParty() && (_owner.getParty().getLeaderObjectId() == cha.getParty().getLeaderObjectId())) {
			return true;
		}
		return false;
	}

	public boolean checkTarget(Creature target) {
		if (!target.isInRadius2d(this, 300)) {
			return false;
		}

		return _skill.getSkill().getTarget(this, target, false, true, false) != null;
	}

	@Override
	public boolean deleteMe() {
		_owner = null;
		return super.deleteMe();
	}

	@Override
	public Player getActingPlayer() {
		return _owner;
	}

	@Override
	public Weapon getActiveWeaponItem() {
		return null;
	}

	@Override
	public int getReputation() {
		return _owner != null ? _owner.getReputation() : 0;
	}

	/**
	 * Get the owner of this trap.
	 *
	 * @return the owner
	 */
	public Player getOwner() {
		return _owner;
	}

	@Override
	public byte getPvpFlag() {
		return _owner != null ? _owner.getPvpFlag() : 0;
	}

	@Override
	public ItemInstance getSecondaryWeaponInstance() {
		return null;
	}

	@Override
	public Weapon getSecondaryWeaponItem() {
		return null;
	}

	public Skill getSkill() {
		return _skill.getSkill();
	}

	@Override
	public boolean isAutoAttackable(Creature attacker) {
		return !canBeSeen(attacker);
	}

	@Override
	public boolean isTrap() {
		return true;
	}

	@Override
	public TrapInstance asTrap() {
		return this;
	}

	/**
	 * Checks is triggered
	 *
	 * @return True if trap is triggered.
	 */
	public boolean isTriggered() {
		return _isTriggered;
	}

	@Override
	public void onSpawn() {
		super.onSpawn();
		_isInArena = isInsideZone(ZoneId.PVP) && !isInsideZone(ZoneId.SIEGE);
		_playersWhoDetectedMe.clear();
	}

	@Override
	public void doAttack(double damage, Creature target, Skill skill, boolean isDOT, boolean directlyToHp, boolean critical, boolean reflect) {
		super.doAttack(damage, target, skill, isDOT, directlyToHp, critical, reflect);
		sendDamageMessage(target, skill, (int) damage, critical, false);
	}

	@Override
	public void sendDamageMessage(Creature target, Skill skill, int damage, boolean crit, boolean miss) {
		if (miss || (_owner == null)) {
			return;
		}

		if (_owner.isInOlympiadMode() && (target instanceof Player) && ((Player) target).isInOlympiadMode() && (((Player) target).getOlympiadGameId() == _owner.getOlympiadGameId())) {
			OlympiadGameManager.getInstance().notifyCompetitorDamage(getOwner(), damage);
		}

		if (target.isHpBlocked() && !(target instanceof NpcInstance)) {
			_owner.sendPacket(SystemMessageId.THE_ATTACK_HAS_BEEN_BLOCKED);
		} else {
			final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_HAS_INFLICTED_S3_DAMAGE_ON_C2_S4);
			sm.addCharName(this);
			sm.addCharName(target);
			sm.addInt(damage);
			sm.addPopup(target.getObjectId(), getObjectId(), (damage * -1));
			_owner.sendPacket(sm);
		}
	}

	@Override
	public void sendInfo(Player activeChar) {
		if (_isTriggered || canBeSeen(activeChar)) {
			activeChar.sendPacket(new TrapInfo(this, activeChar));
		}
	}

	public void setDetected(Creature detector) {
		if (_isInArena) {
			if (detector.isPlayable()) {
				sendInfo(detector.getActingPlayer());
			}
			return;
		}

		if ((_owner != null) && (_owner.getPvpFlag() == 0) && (_owner.getReputation() >= 0)) {
			return;
		}

		_playersWhoDetectedMe.add(detector.getObjectId());

		// Notify to scripts
		EventDispatcher.getInstance().notifyEventAsync(new OnTrapAction(this, detector, TrapAction.TRAP_DETECTED), this);

		if (detector.isPlayable()) {
			sendInfo(detector.getActingPlayer());
		}
	}

	public void stopDecay() {
		DecayTaskManager.getInstance().cancel(this);
	}

	/**
	 * Trigger the trap.
	 *
	 * @param target the target
	 */
	public void triggerTrap(Creature target) {
		if (_trapTask != null) {
			_trapTask.cancel(true);
			_trapTask = null;
		}

		_isTriggered = true;
		broadcastPacket(new TrapInfo(this, null));
		setTarget(target);

		EventDispatcher.getInstance().notifyEventAsync(new OnTrapAction(this, target, TrapAction.TRAP_TRIGGERED), this);

		ThreadPool.getInstance().scheduleGeneral(new TrapTriggerTask(this), 500, TimeUnit.MILLISECONDS);
	}

	public void unSummon() {
		if (_trapTask != null) {
			_trapTask.cancel(true);
			_trapTask = null;
		}

		_owner = null;

		if (isSpawned() && !isDead()) {
			ZoneManager.getInstance().getRegion(this).removeFromZones(this, false);
			deleteMe();
		}
	}

	public int getRemainingTime() {
		return _remainingTime;
	}

	public void setRemainingTime(int time) {
		_remainingTime = time;
	}

	public int getLifeTime() {
		return _lifeTime;
	}
}
