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
import org.l2junity.gameserver.model.PcCondOverride;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.network.client.send.NpcHtmlMessage;
import org.l2junity.gameserver.util.Util;

import java.util.StringTokenizer;

/**
 * Handler provides ability to override server's conditions for admin.<br>
 * Note: //setparam command uses any XML value and ignores case sensitivity.<br>
 * For best results by //setparam enable the maximum stats PcCondOverride here.
 *
 * @author UnAfraid, Nik
 */
public class AdminPcCondOverride implements IAdminCommandHandler {
	// private static final int SETPARAM_ORDER = 0x90;

	private static final String[] COMMANDS =
			{
					"admin_exceptions",
					"admin_set_exception",
			};

	@Override
	public boolean useAdminCommand(String command, PlayerInstance activeChar) {
		StringTokenizer st = new StringTokenizer(command);
		if (st.hasMoreTokens()) {
			switch (st.nextToken())
			// command
			{
				case "admin_exceptions": {
					final NpcHtmlMessage msg = new NpcHtmlMessage(0, 1);
					msg.setFile(activeChar.getHtmlPrefix(), "admin/cond_override.htm");
					StringBuilder sb = new StringBuilder();
					for (PcCondOverride ex : PcCondOverride.values()) {
						sb.append("<tr><td fixwidth=\"180\">" + ex.getDescription() + ":</td><td><a action=\"bypass -h admin_set_exception " + ex.ordinal() + "\">" + (activeChar.canOverrideCond(ex) ? "Disable" : "Enable") + "</a></td></tr>");
					}
					msg.replace("%cond_table%", sb.toString());
					activeChar.sendPacket(msg);
					break;
				}
				case "admin_set_exception": {
					if (st.hasMoreTokens()) {
						String token = st.nextToken();
						if (Util.isDigit(token)) {
							PcCondOverride ex = PcCondOverride.getCondOverride(Integer.valueOf(token));
							if (ex != null) {
								if (activeChar.canOverrideCond(ex)) {
									activeChar.removeOverridedCond(ex);
									activeChar.sendMessage("You've disabled " + ex.getDescription());
								} else {
									activeChar.addOverrideCond(ex);
									activeChar.sendMessage("You've enabled " + ex.getDescription());
								}
							}
						} else {
							switch (token) {
								case "enable_all": {
									for (PcCondOverride ex : PcCondOverride.values()) {
										if (!activeChar.canOverrideCond(ex)) {
											activeChar.addOverrideCond(ex);
										}
									}
									activeChar.sendMessage("All condition exceptions have been enabled.");
									break;
								}
								case "disable_all": {
									for (PcCondOverride ex : PcCondOverride.values()) {
										if (activeChar.canOverrideCond(ex)) {
											activeChar.removeOverridedCond(ex);
										}
									}
									activeChar.sendMessage("All condition exceptions have been disabled.");
									break;
								}
							}
						}
						useAdminCommand(COMMANDS[0], activeChar);
					}
					break;
				}
			}
		}
		return true;
	}

	@Override
	public String[] getAdminCommandList() {
		return COMMANDS;
	}

	public static void main(String[] args) {
		AdminCommandHandler.getInstance().registerHandler(new AdminPcCondOverride());
	}
}