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
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.network.PacketWriter;

public class ValidateLocation implements IClientOutgoingPacket {
	private final int _charObjId;
	private final Location _loc;

	public ValidateLocation(WorldObject obj) {
		_charObjId = obj.getObjectId();
		_loc = obj.getLocation();
	}

	@Override
	public boolean write(PacketWriter packet) {
		OutgoingPackets.VALIDATE_LOCATION.writeId(packet);

		packet.writeD(_charObjId);
		packet.writeD((int) _loc.getX());
		packet.writeD((int) _loc.getY());
		packet.writeD((int) _loc.getZ());
		packet.writeD(_loc.getHeading());
		packet.writeC(0xFF); // TODO: Find me!
		return true;
	}
}
