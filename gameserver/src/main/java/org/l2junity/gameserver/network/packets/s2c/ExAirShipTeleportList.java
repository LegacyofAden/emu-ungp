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
import org.l2junity.gameserver.model.VehiclePathPoint;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

public class ExAirShipTeleportList extends GameServerPacket {
	private final int _dockId;
	private final VehiclePathPoint[][] _teleports;
	private final int[] _fuelConsumption;

	public ExAirShipTeleportList(int dockId, VehiclePathPoint[][] teleports, int[] fuelConsumption) {
		_dockId = dockId;
		_teleports = teleports;
		_fuelConsumption = fuelConsumption;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_AIR_SHIP_TELEPORT_LIST.writeId(body);

		body.writeD(_dockId);
		if (_teleports != null) {
			body.writeD(_teleports.length);

			for (int i = 0; i < _teleports.length; i++) {
				body.writeD(i - 1);
				body.writeD(_fuelConsumption[i]);
				final VehiclePathPoint[] path = _teleports[i];
				final VehiclePathPoint dst = path[path.length - 1];
				body.writeD((int) dst.getX());
				body.writeD((int) dst.getY());
				body.writeD((int) dst.getZ());
			}
		} else {
			body.writeD(0);
		}
	}
}
