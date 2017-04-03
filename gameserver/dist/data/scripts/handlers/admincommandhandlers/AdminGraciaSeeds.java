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
import org.l2junity.gameserver.instancemanager.GraciaSeedsManager;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.client.send.NpcHtmlMessage;

import java.util.Calendar;
import java.util.StringTokenizer;

public class AdminGraciaSeeds implements IAdminCommandHandler {
	private static final String[] ADMIN_COMMANDS =
			{
					"admin_gracia_seeds",
					"admin_kill_tiat",
					"admin_set_sodstate"
			};

	@Override
	public boolean useAdminCommand(String command, Player activeChar) {
		StringTokenizer st = new StringTokenizer(command, " ");
		String actualCommand = st.nextToken(); // Get actual command

		String val = "";
		if (st.countTokens() >= 1) {
			val = st.nextToken();
		}

		if (actualCommand.equalsIgnoreCase("admin_kill_tiat")) {
			GraciaSeedsManager.getInstance().increaseSoDTiatKilled();
		} else if (actualCommand.equalsIgnoreCase("admin_set_sodstate")) {
			GraciaSeedsManager.getInstance().setSoDState(Integer.parseInt(val), true);
		}

		showMenu(activeChar);
		return true;
	}

	private void showMenu(Player activeChar) {
		final NpcHtmlMessage html = new NpcHtmlMessage(0, 1);
		html.setFile(activeChar.getHtmlPrefix(), "admin/graciaseeds.htm");
		html.replace("%sodstate%", String.valueOf(GraciaSeedsManager.getInstance().getSoDState()));
		html.replace("%sodtiatkill%", String.valueOf(GraciaSeedsManager.getInstance().getSoDTiatKilled()));
		if (GraciaSeedsManager.getInstance().getSoDTimeForNextStateChange() > 0) {
			Calendar nextChangeDate = Calendar.getInstance();
			nextChangeDate.setTimeInMillis(System.currentTimeMillis() + GraciaSeedsManager.getInstance().getSoDTimeForNextStateChange());
			html.replace("%sodtime%", nextChangeDate.getTime().toString());
		} else {
			html.replace("%sodtime%", "-1");
		}
		activeChar.sendPacket(html);
	}

	@Override
	public String[] getAdminCommandList() {
		return ADMIN_COMMANDS;
	}

	public static void main(String[] args) {
		AdminCommandHandler.getInstance().registerHandler(new AdminGraciaSeeds());
	}
}