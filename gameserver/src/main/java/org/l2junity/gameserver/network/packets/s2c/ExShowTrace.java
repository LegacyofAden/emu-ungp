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
import org.l2junity.gameserver.model.interfaces.ILocational;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.ArrayList;
import java.util.List;

/**
 * This packet shows the mouse click particle for 30 seconds on every location.
 *
 * @author NosBit
 */
public final class ExShowTrace extends GameServerPacket {
	private final List<Location> _locations = new ArrayList<>();

	public void addLocation(double x, double y, double z) {
		_locations.add(new Location(x, y, z));
	}

	public void addLocation(ILocational loc) {
		addLocation(loc.getX(), loc.getY(), loc.getZ());
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_SHOW_TRACE.writeId(body);

		body.writeH(0); // type broken in H5
		body.writeD(0); // time broken in H5
		body.writeH(_locations.size());
		for (Location loc : _locations) {
			body.writeD((int) loc.getX());
			body.writeD((int) loc.getY());
			body.writeD((int) loc.getZ());
		}
	}
}
