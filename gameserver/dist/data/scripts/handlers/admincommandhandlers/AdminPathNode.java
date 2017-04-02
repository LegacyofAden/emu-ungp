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

import org.l2junity.core.configs.GeoDataConfig;
import org.l2junity.gameserver.geodata.pathfinding.AbstractNodeLoc;
import org.l2junity.gameserver.geodata.pathfinding.PathFinding;
import org.l2junity.gameserver.handler.AdminCommandHandler;
import org.l2junity.gameserver.handler.IAdminCommandHandler;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;

import java.util.List;

public class AdminPathNode implements IAdminCommandHandler {
	private static final String[] ADMIN_COMMANDS =
			{
					"admin_pn_info",
					"admin_show_path",
					"admin_path_debug",
					"admin_show_pn",
					"admin_find_path",
			};

	@Override
	public boolean useAdminCommand(String command, PlayerInstance activeChar) {
		if (command.equals("admin_pn_info")) {
			final String[] info = PathFinding.getInstance().getStat();
			if (info == null) {
				activeChar.sendMessage("Not supported");
			} else {
				for (String msg : info) {
					activeChar.sendMessage(msg);
				}
			}
		} else if (command.equals("admin_show_path")) {

		} else if (command.equals("admin_path_debug")) {

		} else if (command.equals("admin_show_pn")) {

		} else if (command.equals("admin_find_path")) {
			if (GeoDataConfig.PATHFINDING == 0) {
				activeChar.sendMessage("PathFinding is disabled.");
				return true;
			}
			if (activeChar.getTarget() != null) {
				List<AbstractNodeLoc> path = PathFinding.getInstance().findPath(activeChar.getX(), activeChar.getY(), (short) activeChar.getZ(), activeChar.getTarget().getX(), activeChar.getTarget().getY(), (short) activeChar.getTarget().getZ(), activeChar.getInstanceWorld(), true);
				if (path == null) {
					activeChar.sendMessage("No Route!");
					return true;
				}
				for (AbstractNodeLoc a : path) {
					activeChar.sendMessage("x:" + a.getX() + " y:" + a.getY() + " z:" + a.getZ());
				}
			} else {
				activeChar.sendMessage("No Target!");
			}
		}
		return true;
	}

	@Override
	public String[] getAdminCommandList() {
		return ADMIN_COMMANDS;
	}

	public static void main(String[] args) {
		AdminCommandHandler.getInstance().registerHandler(new AdminPathNode());
	}
}