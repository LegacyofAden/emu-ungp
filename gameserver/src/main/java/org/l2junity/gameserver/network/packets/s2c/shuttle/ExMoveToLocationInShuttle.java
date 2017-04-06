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
package org.l2junity.gameserver.network.packets.s2c.shuttle;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author UnAfraid
 */
public class ExMoveToLocationInShuttle extends GameServerPacket {
	private final int _charObjId;
	private final int _airShipId;
	private final int _targetX, _targetY, _targetZ;
	private final int _fromX, _fromY, _fromZ;

	public ExMoveToLocationInShuttle(Player player, int fromX, int fromY, int fromZ) {
		_charObjId = player.getObjectId();
		_airShipId = player.getShuttle().getObjectId();
		_targetX = (int) player.getInVehiclePosition().getX();
		_targetY = (int) player.getInVehiclePosition().getY();
		_targetZ = (int) player.getInVehiclePosition().getZ();
		_fromX = fromX;
		_fromY = fromY;
		_fromZ = fromZ;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_MOVE_TO_LOCATION_IN_SUTTLE.writeId(body);

		body.writeD(_charObjId);
		body.writeD(_airShipId);
		body.writeD(_targetX);
		body.writeD(_targetY);
		body.writeD(_targetZ);
		body.writeD(_fromX);
		body.writeD(_fromY);
		body.writeD(_fromZ);
	}
}
