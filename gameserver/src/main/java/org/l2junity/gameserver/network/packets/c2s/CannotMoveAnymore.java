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

import org.l2junity.gameserver.ai.CtrlEvent;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameClientPacket;


public final class CannotMoveAnymore extends GameClientPacket {
	private int _x;
	private int _y;
	private int _z;
	private int _heading;

	@Override
	public void readImpl() {
		_x = readD();
		_y = readD();
		_z = readD();
		_heading = readD();
	}

	@Override
	public void runImpl() {
		Player player = getClient().getActiveChar();
		if (player == null) {
			return;
		}

		if (player.getAI() != null) {
			player.getAI().notifyEvent(CtrlEvent.EVT_ARRIVED_BLOCKED, new Location(_x, _y, _z, _heading));
		}
	}
}