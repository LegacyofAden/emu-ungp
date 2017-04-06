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
package org.l2junity.gameserver.network.packets.c2s;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.gameserver.network.packets.GameClientPacket;


@Slf4j
public class ExGetOnAirShip extends GameClientPacket {
	private int _x;
	private int _y;
	private int _z;
	private int _shipId;

	@Override
	public void readImpl() {
		_x = readD();
		_y = readD();
		_z = readD();
		_shipId = readD();
	}

	@Override
	public void runImpl() {
		log.info("[T1:ExGetOnAirShip] x: " + _x);
		log.info("[T1:ExGetOnAirShip] y: " + _y);
		log.info("[T1:ExGetOnAirShip] z: " + _z);
		log.info("[T1:ExGetOnAirShip] ship ID: " + _shipId);
	}
}