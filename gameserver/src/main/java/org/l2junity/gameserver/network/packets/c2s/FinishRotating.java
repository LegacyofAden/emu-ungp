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

import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.StopRotation;


public final class FinishRotating extends GameClientPacket {
	private int _degree;
	@SuppressWarnings("unused")
	private int _unknown;

	@Override
	public void readImpl() {
		_degree = readD();
		_unknown = readD();
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		StopRotation sr;
		if (activeChar.isInAirShip() && activeChar.getAirShip().isCaptain(activeChar)) {
			activeChar.getAirShip().setHeading(_degree);
			sr = new StopRotation(activeChar.getAirShip().getObjectId(), _degree, 0);
			activeChar.getAirShip().broadcastPacket(sr);
		} else {
			sr = new StopRotation(activeChar.getObjectId(), _degree, 0);
			activeChar.broadcastPacket(sr);
		}
	}
}