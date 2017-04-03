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
package org.l2junity.gameserver.network.client.recv;

import org.l2junity.core.configs.GeoDataConfig;
import org.l2junity.gameserver.model.World;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.zone.ZoneId;
import org.l2junity.gameserver.network.client.L2GameClient;
import org.l2junity.gameserver.network.client.send.GetOnVehicle;
import org.l2junity.gameserver.network.client.send.ValidateLocation;
import org.l2junity.network.PacketReader;

public class ValidatePosition implements IClientIncomingPacket {
	private int _x;
	private int _y;
	private int _z;
	private int _heading;
	private int _data; // vehicle id

	@Override
	public boolean read(L2GameClient client, PacketReader packet) {
		_x = packet.readD();
		_y = packet.readD();
		_z = packet.readD();
		_heading = packet.readD();
		_data = packet.readD();
		return true;
	}

	@Override
	public void run(L2GameClient client) {
		final Player activeChar = client.getActiveChar();
		if ((activeChar == null) || activeChar.isTeleporting() || activeChar.inObserverMode()) {
			return;
		}

		final double realX = activeChar.getX();
		final double realY = activeChar.getY();
		double realZ = activeChar.getZ();

		if ((_x == 0) && (_y == 0)) {
			if (realX != 0) {
				return;
			}
		}

		double dx, dy, dz;
		double diffSq;

		if (activeChar.isInBoat()) {
			if (GeoDataConfig.COORD_SYNCHRONIZE == 2) {
				dx = _x - activeChar.getInVehiclePosition().getX();
				dy = _y - activeChar.getInVehiclePosition().getY();
				// dz = _z - activeChar.getInVehiclePosition().getZ();
				diffSq = ((dx * dx) + (dy * dy));
				if (diffSq > 250000) {
					client.sendPacket(new GetOnVehicle(activeChar.getObjectId(), _data, activeChar.getInVehiclePosition()));
				}
			}
			return;
		}
		if (activeChar.isInAirShip()) {
			// Zoey76: TODO: Implement or cleanup.
			// if (Config.COORD_SYNCHRONIZE == 2)
			// {
			// dx = _x - activeChar.getInVehiclePosition().getX();
			// dy = _y - activeChar.getInVehiclePosition().getY();
			// dz = _z - activeChar.getInVehiclePosition().getZ();
			// diffSq = ((dx * dx) + (dy * dy));
			// if (diffSq > 250000)
			// {
			// sendPacket(new GetOnVehicle(activeChar.getObjectId(), _data, activeChar.getInBoatPosition()));
			// }
			// }
			return;
		}

		if (activeChar.isFalling(_z)) {
			return; // disable validations during fall to avoid "jumping"
		}

		dx = _x - realX;
		dy = _y - realY;
		dz = _z - realZ;
		diffSq = ((dx * dx) + (dy * dy));

		// Zoey76: TODO: Implement or cleanup.
		// L2Party party = activeChar.getParty();
		// if ((party != null) && (activeChar.getLastPartyPositionDistance(_x, _y, _z) > 150))
		// {
		// activeChar.setLastPartyPosition(_x, _y, _z);
		// party.broadcastToPartyMembers(activeChar, new PartyMemberPosition(activeChar));
		// }

		// Don't allow flying transformations outside gracia area!
		if (activeChar.isFlyingMounted() && (_x > World.GRACIA_MAX_X)) {
			activeChar.untransform();
		}

		if (activeChar.isFlying() || activeChar.isInsideZone(ZoneId.WATER)) {
			activeChar.setXYZ(realX, realY, _z);
			if (diffSq > 90000) {
				activeChar.sendPacket(new ValidateLocation(activeChar));
			}
		} else if (diffSq < 360000) // if too large, messes observation
		{
			if (GeoDataConfig.COORD_SYNCHRONIZE == -1) // Only Z coordinate synched to server,
			// mainly used when no geodata but can be used also with geodata
			{
				activeChar.setXYZ(realX, realY, _z);
				return;
			}
			if (GeoDataConfig.COORD_SYNCHRONIZE == 1) // Trusting also client x,y coordinates (should not be used with geodata)
			{
				if (!activeChar.isMoving() || !activeChar.validateMovementHeading(_heading)) // Heading changed on client = possible obstacle
				{
					// character is not moving, take coordinates from client
					if (diffSq < 2500) {
						activeChar.setXYZ(realX, realY, _z);
					} else {
						activeChar.setXYZ(_x, _y, _z);
					}
				} else {
					activeChar.setXYZ(realX, realY, _z);
				}

				activeChar.setHeading(_heading);
				return;
			}
			// Sync 2 (or other),
			// intended for geodata. Sends a validation packet to client
			// when too far from server calculated true coordinate.
			// Due to geodata/zone errors, some Z axis checks are made. (maybe a temporary solution)
			// Important: this code part must work together with L2Character.updatePosition
			if ((diffSq > 250000) || (Math.abs(dz) > 200)) {
				// if ((_z - activeChar.getClientZ()) < 200 && Math.abs(activeChar.getLastServerPosition().getZ()-realZ) > 70)

				if ((Math.abs(dz) > 200) && (Math.abs(dz) < 1500) && (Math.abs(_z - activeChar.getClientZ()) < 800)) {
					activeChar.setXYZ(realX, realY, _z);
					realZ = _z;
				} else {
					activeChar.sendPacket(new ValidateLocation(activeChar));
				}
			}
		}

		activeChar.setClientX(_x);
		activeChar.setClientY(_y);
		activeChar.setClientZ(_z);
		activeChar.setClientHeading(_heading); // No real need to validate heading.
		activeChar.setLastServerPosition(realX, realY, realZ);
	}
}
