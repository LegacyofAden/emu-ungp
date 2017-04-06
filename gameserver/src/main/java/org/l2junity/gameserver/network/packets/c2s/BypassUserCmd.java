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

import org.l2junity.gameserver.handler.IUserCommandHandler;
import org.l2junity.gameserver.handler.UserCommandHandler;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameClientPacket;


public class BypassUserCmd extends GameClientPacket {
	private int _command;

	@Override
	public void readImpl() {
		_command = readD();
	}

	@Override
	public void runImpl() {
		Player player = getClient().getActiveChar();
		if (player == null) {
			return;
		}

		final IUserCommandHandler handler = UserCommandHandler.getInstance().getHandler(_command);
		if (handler == null) {
			if (player.isGM()) {
				player.sendMessage("User commandID " + _command + " not implemented yet.");
			}
		} else {
			handler.useUserCommand(_command, getClient().getActiveChar());
		}
	}
}
