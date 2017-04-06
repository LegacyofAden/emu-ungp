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
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

public class TargetUnselected extends GameServerPacket {
	private final int _targetObjId;
	private final int _x;
	private final int _y;
	private final int _z;

	/**
	 * @param character
	 */
	public TargetUnselected(Creature character) {
		_targetObjId = character.getObjectId();
		_x = (int) character.getX();
		_y = (int) character.getY();
		_z = (int) character.getZ();
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.TARGET_UNSELECTED.writeId(body);

		body.writeD(_targetObjId);
		body.writeD(_x);
		body.writeD(_y);
		body.writeD(_z);
		body.writeD(0x00); // ??
	}
}