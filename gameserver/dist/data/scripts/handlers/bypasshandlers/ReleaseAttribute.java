/*
 * Copyright (C) 2004-2015 L2J DataPack
 * 
 * This file is part of L2J DataPack.
 * 
 * L2J DataPack is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J DataPack is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package handlers.bypasshandlers;

import org.l2junity.gameserver.handler.BypassHandler;
import org.l2junity.gameserver.handler.IBypassHandler;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.network.client.send.ExShowBaseAttributeCancelWindow;

public class ReleaseAttribute implements IBypassHandler {
	private static final String[] COMMANDS =
			{
					"ReleaseAttribute"
			};

	@Override
	public boolean useBypass(String command, PlayerInstance activeChar, Creature target) {
		if (!target.isNpc()) {
			return false;
		}

		activeChar.sendPacket(new ExShowBaseAttributeCancelWindow(activeChar));
		return true;
	}

	@Override
	public String[] getBypassList() {
		return COMMANDS;
	}

	public static void main(String[] args) {
		BypassHandler.getInstance().registerHandler(new ReleaseAttribute());
	}
}