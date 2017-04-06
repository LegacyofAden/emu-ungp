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
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author UnAfraid
 */
public class ExTeleportToLocationActivate extends GameServerPacket {
	private final int _objectId;
	private final Location _loc;

	public ExTeleportToLocationActivate(Creature character) {
		_objectId = character.getObjectId();
		_loc = character.getLocation();
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_TELEPORT_TO_LOCATION_ACTIVATE.writeId(body);

		body.writeD(_objectId);
		body.writeD((int) _loc.getX());
		body.writeD((int) _loc.getY());
		body.writeD((int) _loc.getZ());
		body.writeD(0); // Unknown (this isn't instanceId)
		body.writeD(_loc.getHeading());
		body.writeD(0); // Unknown
	}
}