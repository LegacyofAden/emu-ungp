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

import org.l2junity.gameserver.model.interfaces.ILocational;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.network.PacketWriter;

public class Earthquake implements IClientOutgoingPacket {
	private final int _x;
	private final int _y;
	private final int _z;
	private final int _intensity;
	private final int _duration;

	/**
	 * @param location
	 * @param intensity
	 * @param duration
	 */
	public Earthquake(ILocational location, int intensity, int duration) {
		_x = (int) location.getX();
		_y = (int) location.getY();
		_z = (int) location.getZ();
		_intensity = intensity;
		_duration = duration;
	}

	/**
	 * @param x
	 * @param y
	 * @param z
	 * @param intensity
	 * @param duration
	 */
	public Earthquake(double x, double y, double z, int intensity, int duration) {
		_x = (int) x;
		_y = (int) y;
		_z = (int) z;
		_intensity = intensity;
		_duration = duration;
	}

	@Override
	public boolean write(PacketWriter packet) {
		OutgoingPackets.EARTHQUAKE.writeId(packet);

		packet.writeD(_x);
		packet.writeD(_y);
		packet.writeD(_z);
		packet.writeD(_intensity);
		packet.writeD(_duration);
		packet.writeD(0x00); // Unknown
		return true;
	}
}
