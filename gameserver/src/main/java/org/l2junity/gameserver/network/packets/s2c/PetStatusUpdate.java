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
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

public class PetStatusUpdate extends GameServerPacket {
	private final Summon _summon;
	private int _maxFed, _curFed;

	public PetStatusUpdate(Summon summon) {
		_summon = summon;
		if (_summon instanceof PetInstance) {
			PetInstance pet = (PetInstance) _summon;
			_curFed = pet.getCurrentFed(); // how fed it is
			_maxFed = pet.getMaxFed(); // max fed it can be
		} else if (_summon instanceof ServitorInstance) {
			ServitorInstance sum = (ServitorInstance) _summon;
			_curFed = sum.getLifeTimeRemaining();
			_maxFed = sum.getLifeTime();
		}
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.PET_STATUS_UPDATE.writeId(body);

		body.writeD(_summon.getSummonType());
		body.writeD(_summon.getObjectId());
		body.writeD((int) _summon.getX());
		body.writeD((int) _summon.getY());
		body.writeD((int) _summon.getZ());
		body.writeS(_summon.getTitle());
		body.writeD(_curFed);
		body.writeD(_maxFed);
		body.writeD((int) _summon.getCurrentHp());
		body.writeD(_summon.getMaxHp());
		body.writeD((int) _summon.getCurrentMp());
		body.writeD(_summon.getMaxMp());
		body.writeD(_summon.getLevel());
		body.writeQ(_summon.getStat().getExp());
		body.writeQ(_summon.getExpForThisLevel()); // 0% absolute value
		body.writeQ(_summon.getExpForNextLevel()); // 100% absolute value
		body.writeD(0x01); // TODO: Find me!
	}
}
