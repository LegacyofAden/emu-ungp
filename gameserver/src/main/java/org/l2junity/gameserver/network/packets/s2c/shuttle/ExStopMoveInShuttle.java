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
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author UnAfraid
 */
public class ExStopMoveInShuttle extends GameServerPacket {
	private final int _charObjId;
	private final int _boatId;
	private final Location _pos;
	private final int _heading;

	public ExStopMoveInShuttle(Player player, int boatId) {
		_charObjId = player.getObjectId();
		_boatId = boatId;
		_pos = player.getInVehiclePosition();
		_heading = player.getHeading();
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_STOP_MOVE_IN_SHUTTLE.writeId(body);

		body.writeD(_charObjId);
		body.writeD(_boatId);
		body.writeD((int) _pos.getX());
		body.writeD((int) _pos.getY());
		body.writeD((int) _pos.getZ());
		body.writeD(_heading);
	}
}
