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
import org.l2junity.gameserver.model.actor.instance.ShuttleInstance;
import org.l2junity.gameserver.model.shuttle.L2ShuttleStop;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.List;

/**
 * @author UnAfraid
 */
public class ExShuttleInfo extends GameServerPacket {
	private final ShuttleInstance _shuttle;
	private final List<L2ShuttleStop> _stops;

	public ExShuttleInfo(ShuttleInstance shuttle) {
		_shuttle = shuttle;
		_stops = shuttle.getStops();
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_SHUTTLE_INFO.writeId(body);

		body.writeD(_shuttle.getObjectId());
		body.writeD((int) _shuttle.getX());
		body.writeD((int) _shuttle.getY());
		body.writeD((int) _shuttle.getZ());
		body.writeD(_shuttle.getHeading());
		body.writeD(_shuttle.getId());
		body.writeD(_stops.size());
		for (L2ShuttleStop stop : _stops) {
			body.writeD(stop.getId());
			for (Location loc : stop.getDimensions()) {
				body.writeD((int) loc.getX());
				body.writeD((int) loc.getY());
				body.writeD((int) loc.getZ());
			}
			body.writeD(stop.isDoorOpen() ? 0x01 : 0x00);
			body.writeD(stop.hasDoorChanged() ? 0x01 : 0x00);
		}
	}
}
