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
package org.l2junity.gameserver.network.packets.c2s.shuttle;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.actor.instance.ShuttleInstance;
import org.l2junity.gameserver.network.packets.GameClientPacket;


/**
 * @author UnAfraid
 */
@Slf4j
public class RequestShuttleGetOn extends GameClientPacket {
	private int _x;
	private int _y;
	private int _z;

	@Override
	public void readImpl() {
		readD(); // charId
		_x = readD();
		_y = readD();
		_z = readD();
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		// TODO: better way?
		for (ShuttleInstance shuttle : activeChar.getWorld().getVisibleObjects(activeChar, ShuttleInstance.class)) {
			if (shuttle.isInRadius3d(activeChar, 1000)) {
				shuttle.addPassenger(activeChar);
				activeChar.getInVehiclePosition().setXYZ(_x, _y, _z);
				break;
			}
			log.info(getClass().getSimpleName() + ": range between char and shuttle: " + shuttle.distance3d(activeChar));
		}
	}
}
