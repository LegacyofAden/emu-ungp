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
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.TrapInstance;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

public abstract class AbstractNpcInfo extends GameServerPacket {
	protected int _x, _y, _z, _heading;
	protected int _idTemplate;
	protected boolean _isAttackable, _isSummoned;
	protected int _mAtkSpd, _pAtkSpd;
	protected final int _runSpd, _walkSpd;
	protected final int _swimRunSpd, _swimWalkSpd;
	protected final int _flyRunSpd, _flyWalkSpd;
	protected double _moveMultiplier;

	protected int _rhand, _lhand, _chest, _enchantEffect;
	protected double _collisionHeight, _collisionRadius;
	protected String _name = "";
	protected String _title = "";

	public AbstractNpcInfo(Creature cha) {
		_isSummoned = cha.isShowSummonAnimation();
		_x = (int) cha.getX();
		_y = (int) cha.getY();
		_z = (int) cha.getZ();
		_heading = cha.getHeading();
		_mAtkSpd = cha.getMAtkSpd();
		_pAtkSpd = cha.getPAtkSpd();
		_moveMultiplier = cha.getMovementSpeedMultiplier();
		_runSpd = (int) Math.round(cha.getRunSpeed() / _moveMultiplier);
		_walkSpd = (int) Math.round(cha.getWalkSpeed() / _moveMultiplier);
		_swimRunSpd = (int) Math.round(cha.getSwimRunSpeed() / _moveMultiplier);
		_swimWalkSpd = (int) Math.round(cha.getSwimWalkSpeed() / _moveMultiplier);
		_flyRunSpd = cha.isFlying() ? _runSpd : 0;
		_flyWalkSpd = cha.isFlying() ? _walkSpd : 0;
	}

	public static class TrapInfo extends AbstractNpcInfo {
		private final TrapInstance _trap;

		public TrapInfo(TrapInstance cha, Creature attacker) {
			super(cha);

			_trap = cha;
			_idTemplate = cha.getTemplate().getDisplayId();
			_isAttackable = cha.isAutoAttackable(attacker);
			_rhand = 0;
			_lhand = 0;
			_collisionHeight = _trap.getTemplate().getfCollisionHeight();
			_collisionRadius = _trap.getTemplate().getfCollisionRadius();
			if (cha.getTemplate().isUsingServerSideName()) {
				_name = cha.getName();
			}
			_title = cha.getOwner() != null ? cha.getOwner().getName() : "";
		}

		@Override
		protected void writeImpl(PacketBody body) {
			GameServerPacketType.NPC_INFO.writeId(body);

			body.writeD(_trap.getObjectId());
			body.writeD(_idTemplate + 1000000); // npctype id
			body.writeD(_isAttackable ? 1 : 0);
			body.writeD(_x);
			body.writeD(_y);
			body.writeD(_z);
			body.writeD(_heading);
			body.writeD(0x00);
			body.writeD(_mAtkSpd);
			body.writeD(_pAtkSpd);
			body.writeD(_runSpd);
			body.writeD(_walkSpd);
			body.writeD(_swimRunSpd);
			body.writeD(_swimWalkSpd);
			body.writeD(_flyRunSpd);
			body.writeD(_flyWalkSpd);
			body.writeD(_flyRunSpd);
			body.writeD(_flyWalkSpd);
			body.writeF(_moveMultiplier);
			body.writeF(_trap.getAttackSpeedMultiplier());
			body.writeF(_collisionRadius);
			body.writeF(_collisionHeight);
			body.writeD(_rhand); // right hand weapon
			body.writeD(_chest);
			body.writeD(_lhand); // left hand weapon
			body.writeC(1); // name above char 1=true ... ??
			body.writeC(1);
			body.writeC(_trap.isInCombat() ? 1 : 0);
			body.writeC(_trap.isAlikeDead() ? 1 : 0);
			body.writeC(_isSummoned ? 2 : 0); // invisible ?? 0=false 1=true 2=summoned (only works if model has a summon animation)
			body.writeD(-1); // High Five NPCString ID
			body.writeS(_name);
			body.writeD(-1); // High Five NPCString ID
			body.writeS(_title);
			body.writeD(0x00); // title color 0 = client default

			body.writeD(_trap.getPvpFlag());
			body.writeD(_trap.getReputation());

			body.writeD(0); // was AVE and was adding stealth
			body.writeD(0x00); // clan id
			body.writeD(0x00); // crest id
			body.writeD(0000); // C2
			body.writeD(0000); // C2
			body.writeC(0000); // C2

			body.writeC(_trap.getTeam().getId());

			body.writeF(_collisionRadius);
			body.writeF(_collisionHeight);
			body.writeD(0x00); // C4
			body.writeD(0x00); // C6
			body.writeD(0x00);
			body.writeD(0);// CT1.5 Pet form and skills
			body.writeC(0x01);
			body.writeC(0x01);
			body.writeD(0x00);
		}
	}
}
