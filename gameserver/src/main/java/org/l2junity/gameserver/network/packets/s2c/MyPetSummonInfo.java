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
package org.l2junity.gameserver.network.packets.s2c;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.model.actor.Summon;
import org.l2junity.gameserver.model.actor.instance.PetInstance;
import org.l2junity.gameserver.model.actor.instance.ServitorInstance;
import org.l2junity.gameserver.model.skills.AbnormalVisualEffect;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;
import org.l2junity.gameserver.taskmanager.AttackStanceTaskManager;

import java.util.Set;

public class MyPetSummonInfo extends GameServerPacket {
	private final Summon _summon;
	private final int _val;
	private final int _runSpd, _walkSpd;
	private final int _swimRunSpd;
	private final int _swimWalkSpd;
	private final int _flRunSpd = 0;
	private final int _flWalkSpd = 0;
	private final int _flyRunSpd;
	private final int _flyWalkSpd;
	private final double _moveMultiplier;
	private int _maxFed, _curFed;
	private int _statusMask = 0;

	public MyPetSummonInfo(Summon summon, int val) {
		_summon = summon;
		_moveMultiplier = summon.getMovementSpeedMultiplier();
		_runSpd = (int) Math.round(summon.getRunSpeed() / _moveMultiplier);
		_walkSpd = (int) Math.round(summon.getWalkSpeed() / _moveMultiplier);
		_swimRunSpd = (int) Math.round(summon.getSwimRunSpeed() / _moveMultiplier);
		_swimWalkSpd = (int) Math.round(summon.getSwimWalkSpeed() / _moveMultiplier);
		_flyRunSpd = summon.isFlying() ? _runSpd : 0;
		_flyWalkSpd = summon.isFlying() ? _walkSpd : 0;
		_val = val;
		if (summon.isPet()) {
			final PetInstance pet = (PetInstance) _summon;
			_curFed = pet.getCurrentFed(); // how fed it is
			_maxFed = pet.getMaxFed(); // max fed it can be
		} else if (summon.isServitor()) {
			final ServitorInstance sum = (ServitorInstance) _summon;
			_curFed = sum.getLifeTimeRemaining();
			_maxFed = sum.getLifeTime();
		}

		if (summon.isBetrayed()) {
			_statusMask |= 0x01; // Auto attackable status
		}
		_statusMask |= 0x02; // wtf is that ?

		if (summon.isRunning()) {
			_statusMask |= 0x04;
		}
		if (AttackStanceTaskManager.getInstance().hasAttackStanceTask(summon)) {
			_statusMask |= 0x08;
		}
		if (summon.isDead()) {
			_statusMask |= 0x10;
		}
		if (summon.isMountable()) {
			_statusMask |= 0x20;
		}
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.PET_INFO.writeId(body);

		body.writeC(_summon.getSummonType());
		body.writeD(_summon.getObjectId());
		body.writeD(_summon.getTemplate().getDisplayId() + 1000000);

		body.writeD((int) _summon.getX());
		body.writeD((int) _summon.getY());
		body.writeD((int) _summon.getZ());
		body.writeD(_summon.getHeading());

		body.writeD(_summon.getStat().getMAtkSpd());
		body.writeD(_summon.getStat().getPAtkSpd());

		body.writeH(_runSpd);
		body.writeH(_walkSpd);
		body.writeH(_swimRunSpd);
		body.writeH(_swimWalkSpd);
		body.writeH(_flRunSpd);
		body.writeH(_flWalkSpd);
		body.writeH(_flyRunSpd);
		body.writeH(_flyWalkSpd);

		body.writeF(_moveMultiplier);
		body.writeF(_summon.getAttackSpeedMultiplier()); // attack speed multiplier
		body.writeF(_summon.getTemplate().getfCollisionRadius());
		body.writeF(_summon.getTemplate().getfCollisionHeight());

		body.writeD(_summon.getWeapon()); // right hand weapon
		body.writeD(_summon.getArmor()); // body armor
		body.writeD(0x00); // left hand weapon

		body.writeC(_summon.isShowSummonAnimation() ? 0x02 : _val); // 0=teleported 1=default 2=summoned
		body.writeD(-1); // High Five NPCString ID
		if (_summon.isPet()) {
			body.writeS(_summon.getName()); // Pet name.
		} else {
			body.writeS(_summon.getTemplate().isUsingServerSideName() ? _summon.getName() : ""); // Summon name.
		}
		body.writeD(-1); // High Five NPCString ID
		body.writeS(_summon.getTitle()); // owner name

		body.writeC(_summon.getPvpFlag()); // confirmed
		body.writeD(_summon.getReputation()); // confirmed

		body.writeD(_curFed); // how fed it is
		body.writeD(_maxFed); // max fed it can be
		body.writeD((int) _summon.getCurrentHp());// current hp
		body.writeD(_summon.getMaxHp());// max hp
		body.writeD((int) _summon.getCurrentMp());// current mp
		body.writeD(_summon.getMaxMp());// max mp

		body.writeQ(_summon.getStat().getSp()); // sp
		body.writeC(_summon.getLevel());// lvl
		body.writeQ(_summon.getStat().getExp());

		if (_summon.getExpForThisLevel() > _summon.getStat().getExp()) {
			body.writeQ(_summon.getStat().getExp());// 0% absolute value
		} else {
			body.writeQ(_summon.getExpForThisLevel());// 0% absolute value
		}

		body.writeQ(_summon.getExpForNextLevel());// 100% absoulte value

		body.writeD(_summon.isPet() ? _summon.getInventory().getTotalWeight() : 0);// weight
		body.writeD(_summon.getMaxLoad());// max weight it can carry
		body.writeD(_summon.getPAtk());// patk
		body.writeD(_summon.getPDef());// pdef
		body.writeD(_summon.getAccuracy());// accuracy
		body.writeD(_summon.getEvasionRate());// evasion
		body.writeD(_summon.getCriticalHit());// critical
		body.writeD(_summon.getMAtk());// matk
		body.writeD(_summon.getMDef());// mdef
		body.writeD(_summon.getMagicAccuracy()); // magic accuracy
		body.writeD(_summon.getMagicEvasionRate()); // magic evasion
		body.writeD(_summon.getMCriticalHit()); // mcritical
		body.writeD((int) _summon.getStat().getMoveSpeed());// speed
		body.writeD(_summon.getPAtkSpd());// atkspeed
		body.writeD(_summon.getMAtkSpd());// casting speed

		body.writeC(0); // TODO: Check me, might be ride status
		body.writeC(_summon.getTeam().getId()); // Confirmed
		body.writeC(_summon.getSoulShotsPerHit()); // How many soulshots this servitor uses per hit - Confirmed
		body.writeC(_summon.getSpiritShotsPerHit()); // How many spiritshots this servitor uses per hit - - Confirmed

		body.writeD(0x00); // TODO: Find me
		body.writeD(_summon.getFormId()); // Transformation ID - Confirmed

		body.writeC(_summon.getOwner().getSummonPoints()); // Used Summon Points
		body.writeC(_summon.getOwner().getMaxSummonPoints()); // Maximum Summon Points

		final Set<AbnormalVisualEffect> aves = _summon.getEffectList().getCurrentAbnormalVisualEffects();
		body.writeH(aves.size()); // Confirmed
		for (AbnormalVisualEffect ave : aves) {
			body.writeH(ave.getClientId()); // Confirmed
		}

		body.writeC(_statusMask);
	}
}
