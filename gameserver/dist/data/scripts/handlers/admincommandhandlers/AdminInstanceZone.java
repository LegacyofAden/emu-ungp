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

import java.util.Map;
import java.util.StringTokenizer;

import org.l2junity.gameserver.handler.AdminCommandHandler;
import org.l2junity.gameserver.handler.IAdminCommandHandler;
import org.l2junity.gameserver.instancemanager.InstanceManager;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.packets.s2c.NpcHtmlMessage;
import org.l2junity.gameserver.util.GMAudit;

public class AdminInstanceZone implements IAdminCommandHandler {
	private static final String[] ADMIN_COMMANDS =
			{
					"admin_instancezone",
					"admin_instancezone_clear"
			};

	@Override
	public boolean useAdminCommand(String command, Player activeChar) {
		String target = (activeChar.getTarget() != null) ? activeChar.getTarget().getName() : "no-target";
		GMAudit.auditGMAction(activeChar.getName(), command, target, "");

		if (command.startsWith("admin_instancezone_clear")) {
			try {
				StringTokenizer st = new StringTokenizer(command, " ");

				st.nextToken();
				final Player player = WorldManager.getInstance().getPlayer(st.nextToken());
				final int instanceId = Integer.parseInt(st.nextToken());
				final String name = InstanceManager.getInstance().getInstanceName(instanceId);
				InstanceManager.getInstance().deleteInstanceTime(player, instanceId);
				activeChar.sendMessage("Instance zone " + name + " cleared for player " + player.getName());
				player.sendMessage("Admin cleared instance zone " + name + " for you");

				return true;
			} catch (Exception e) {
				activeChar.sendMessage("Failed clearing instance time: " + e.getMessage());
				activeChar.sendMessage("Usage: //instancezone_clear <playername> [instanceId]");
				return false;
			}
		} else if (command.startsWith("admin_instancezone")) {
			StringTokenizer st = new StringTokenizer(command, " ");
			command = st.nextToken();

			if (st.hasMoreTokens()) {
				Player player = null;
				String playername = st.nextToken();

				try {
					player = WorldManager.getInstance().getPlayer(playername);
				} catch (Exception e) {
				}

				if (player != null) {
					display(player, activeChar);
				} else {
					activeChar.sendMessage("The player " + playername + " is not online");
					activeChar.sendMessage("Usage: //instancezone [playername]");
					return false;
				}
			} else if (activeChar.getTarget() != null) {
				if (activeChar.getTarget() instanceof Player) {
					display((Player) activeChar.getTarget(), activeChar);
				}
			} else {
				display(activeChar, activeChar);
			}
		}
		return true;
	}

	@Override
	public String[] getAdminCommandList() {
		return ADMIN_COMMANDS;
	}

	private void display(Player player, Player activeChar) {
		Map<Integer, Long> instanceTimes = InstanceManager.getInstance().getAllInstanceTimes(player);

		final StringBuilder html = new StringBuilder(500 + (instanceTimes.size() * 200));
		html.append("<html><center><table width=260><tr><td width=40><button value=\"Main\" action=\"bypass -h admin_admin\" width=40 height=21 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td><td width=180><center>Character Instances</center></td><td width=40><button value=\"Back\" action=\"bypass -h admin_current_player\" width=40 height=21 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td></tr></table><br><font color=\"LEVEL\">Instances for " + player.getName() + "</font><center><br><table><tr><td width=150>Name</td><td width=50>Time</td><td width=70>Action</td></tr>");

		for (int id : instanceTimes.keySet()) {
			int hours = 0;
			int minutes = 0;
			long remainingTime = (instanceTimes.get(id) - System.currentTimeMillis()) / 1000;
			if (remainingTime > 0) {
				hours = (int) (remainingTime / 3600);
				minutes = (int) ((remainingTime % 3600) / 60);
			}

			html.append("<tr><td>" + InstanceManager.getInstance().getInstanceName(id) + "</td><td>" + hours + ":" + minutes + "</td><td><button value=\"Clear\" action=\"bypass -h admin_instancezone_clear " + player.getName() + " " + id + "\" width=60 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td></tr>");
		}

		html.append("</table></html>");

		final NpcHtmlMessage ms = new NpcHtmlMessage(0, 1);
		ms.setHtml(html.toString());
		activeChar.sendPacket(ms);
	}

	public static void main(String[] args) {
		AdminCommandHandler.getInstance().registerHandler(new AdminInstanceZone());
	}
}