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
package handlers.admincommandhandlers;

import org.l2junity.gameserver.handler.AdminCommandHandler;
import org.l2junity.gameserver.handler.IAdminCommandHandler;
import org.l2junity.gameserver.model.World;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.network.client.Disconnection;

import java.util.StringTokenizer;

public class AdminKick implements IAdminCommandHandler {
	private static final String[] ADMIN_COMMANDS =
			{
					"admin_kick",
					"admin_kick_non_gm"
			};

	@Override
	public boolean useAdminCommand(String command, PlayerInstance activeChar) {
		if (command.startsWith("admin_kick")) {
			StringTokenizer st = new StringTokenizer(command);
			if (st.countTokens() > 1) {
				st.nextToken();
				String player = st.nextToken();
				PlayerInstance plyr = World.getInstance().getPlayer(player);
				if (plyr != null) {
					Disconnection.of(plyr).defaultSequence(false);
					activeChar.sendMessage("You kicked " + plyr.getName() + " from the game.");
				}
			}
		}
		if (command.startsWith("admin_kick_non_gm")) {
			int counter = 0;
			for (PlayerInstance player : World.getInstance().getPlayers()) {
				if (!player.isGM()) {
					counter++;
					Disconnection.of(player).defaultSequence(false);
				}
			}
			activeChar.sendMessage("Kicked " + counter + " players");
		}
		return true;
	}

	@Override
	public String[] getAdminCommandList() {
		return ADMIN_COMMANDS;
	}

	public static void main(String[] args) {
		AdminCommandHandler.getInstance().registerHandler(new AdminKick());
	}
}