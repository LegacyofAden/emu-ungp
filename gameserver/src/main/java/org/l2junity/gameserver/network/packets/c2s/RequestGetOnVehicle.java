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

import org.l2junity.gameserver.instancemanager.BoatManager;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.actor.instance.BoatInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.zone.ZoneId;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ActionFailed;
import org.l2junity.gameserver.network.packets.s2c.GetOnVehicle;
import org.l2junity.network.PacketReader;

public final class RequestGetOnVehicle extends GameClientPacket {
	private int _boatId;
	private Location _pos;

	@Override
	public void readImpl() {
		int x, y, z;
		_boatId = readD();
		x = readD();
		y = readD();
		z = readD();
		_pos = new Location(x, y, z);
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		BoatInstance boat;
		if (activeChar.isInBoat()) {
			boat = activeChar.getBoat();
			if (boat.getObjectId() != _boatId) {
				getClient().sendPacket(ActionFailed.STATIC_PACKET);
				return;
			}
		} else {
			boat = BoatManager.getInstance().getBoat(_boatId);
			if ((boat == null) || boat.isMoving() || !activeChar.isInRadius3d(boat, 1000)) {
				getClient().sendPacket(ActionFailed.STATIC_PACKET);
				return;
			}
		}

		activeChar.setInVehiclePosition(_pos);
		activeChar.setVehicle(boat);
		activeChar.broadcastPacket(new GetOnVehicle(activeChar.getObjectId(), boat.getObjectId(), _pos));

		activeChar.setXYZ(boat.getX(), boat.getY(), boat.getZ());
		activeChar.setInsideZone(ZoneId.PEACE, true);
		activeChar.revalidateZone(true);
	}
}
