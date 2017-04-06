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
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

public class MoveToPawn extends GameServerPacket {
	private final int _charObjId;
	private final int _targetId;
	private final int _distance;
	private final int _x, _y, _z, _tx, _ty, _tz;

	public MoveToPawn(Creature cha, WorldObject target, int distance) {
		_charObjId = cha.getObjectId();
		_targetId = target.getObjectId();
		_distance = distance;
		_x = (int) cha.getX();
		_y = (int) cha.getY();
		_z = (int) cha.getZ();
		_tx = (int) target.getX();
		_ty = (int) target.getY();
		_tz = (int) target.getZ();
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.MOVE_TO_PAWN.writeId(body);

		body.writeD(_charObjId);
		body.writeD(_targetId);
		body.writeD(_distance);

		body.writeD(_x);
		body.writeD(_y);
		body.writeD(_z);
		body.writeD(_tx);
		body.writeD(_ty);
		body.writeD(_tz);
	}
}