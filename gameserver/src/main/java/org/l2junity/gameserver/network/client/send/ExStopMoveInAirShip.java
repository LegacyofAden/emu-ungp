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
package org.l2junity.gameserver.network.client.send;

import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.network.PacketWriter;

/**
 * update 27.8.10
 *
 * @author kerberos, JIV
 */
public class ExStopMoveInAirShip implements IClientOutgoingPacket {
	private final Player _activeChar;
	private final int _shipObjId;
	private final int _h;
	private final Location _loc;

	public ExStopMoveInAirShip(Player player, int shipObjId) {
		_activeChar = player;
		_shipObjId = shipObjId;
		_h = player.getHeading();
		_loc = player.getInVehiclePosition();
	}

	@Override
	public boolean write(PacketWriter packet) {
		OutgoingPackets.EX_STOP_MOVE_IN_AIR_SHIP.writeId(packet);

		packet.writeD(_activeChar.getObjectId());
		packet.writeD(_shipObjId);
		packet.writeD((int) _loc.getX());
		packet.writeD((int) _loc.getY());
		packet.writeD((int) _loc.getZ());
		packet.writeD(_h);
		return true;
	}
}
