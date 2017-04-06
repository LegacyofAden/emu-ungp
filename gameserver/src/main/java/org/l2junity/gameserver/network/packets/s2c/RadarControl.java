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
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

public class RadarControl extends GameServerPacket {
	private final int _showRadar;
	private final int _type;
	private final int _x;
	private final int _y;
	private final int _z;

	public RadarControl(int showRadar, int type, double x, double y, double z) {
		_showRadar = showRadar; // showRader?? 0 = showradar; 1 = delete radar;
		_type = type; // radar type??
		_x = (int) x;
		_y = (int) y;
		_z = (int) z;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.RADAR_CONTROL.writeId(body);

		body.writeD(_showRadar);
		body.writeD(_type); // maybe type
		body.writeD(_x);
		body.writeD(_y);
		body.writeD(_z);
	}
}