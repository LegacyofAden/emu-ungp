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
import org.l2junity.gameserver.model.actor.instance.AirShipInstance;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

public class ExAirShipInfo extends GameServerPacket {
	// store some parameters, because they can be changed during broadcast
	private final AirShipInstance _ship;
	private final int _x, _y, _z, _heading, _moveSpeed, _rotationSpeed, _captain, _helm;

	public ExAirShipInfo(AirShipInstance ship) {
		_ship = ship;
		_x = (int) ship.getX();
		_y = (int) ship.getY();
		_z = (int) ship.getZ();
		_heading = ship.getHeading();
		_moveSpeed = (int) ship.getStat().getMoveSpeed();
		_rotationSpeed = (int) ship.getStat().getRotationSpeed();
		_captain = ship.getCaptainId();
		_helm = ship.getHelmObjectId();
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_AIR_SHIP_INFO.writeId(body);

		body.writeD(_ship.getObjectId());
		body.writeD(_x);
		body.writeD(_y);
		body.writeD(_z);
		body.writeD(_heading);

		body.writeD(_captain);
		body.writeD(_moveSpeed);
		body.writeD(_rotationSpeed);
		body.writeD(_helm);
		if (_helm != 0) {
			// TODO: unhardcode these!
			body.writeD(0x16e); // Controller X
			body.writeD(0x00); // Controller Y
			body.writeD(0x6b); // Controller Z
			body.writeD(0x15c); // Captain X
			body.writeD(0x00); // Captain Y
			body.writeD(0x69); // Captain Z
		} else {
			body.writeD(0x00);
			body.writeD(0x00);
			body.writeD(0x00);
			body.writeD(0x00);
			body.writeD(0x00);
			body.writeD(0x00);
		}

		body.writeD(_ship.getFuel());
		body.writeD(_ship.getMaxFuel());
	}
}
