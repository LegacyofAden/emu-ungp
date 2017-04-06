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
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author Maktakien
 */
public class MoveToLocationInVehicle extends GameServerPacket {
	private final int _charObjId;
	private final int _boatId;
	private final Location _destination;
	private final Location _origin;

	/**
	 * @param player
	 * @param destination
	 * @param origin
	 */
	public MoveToLocationInVehicle(Player player, Location destination, Location origin) {
		_charObjId = player.getObjectId();
		_boatId = player.getBoat().getObjectId();
		_destination = destination;
		_origin = origin;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.MOVE_TO_LOCATION_IN_VEHICLE.writeId(body);

		body.writeD(_charObjId);
		body.writeD(_boatId);
		body.writeD((int) _destination.getX());
		body.writeD((int) _destination.getY());
		body.writeD((int) _destination.getZ());
		body.writeD((int) _origin.getX());
		body.writeD((int) _origin.getY());
		body.writeD((int) _origin.getZ());
	}
}