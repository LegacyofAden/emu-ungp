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
import org.l2junity.gameserver.instancemanager.CastleManager;
import org.l2junity.gameserver.instancemanager.CastleManorManager;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.entity.Castle;
import org.l2junity.gameserver.network.client.send.NpcHtmlMessage;
import org.l2junity.gameserver.util.Util;

/**
 * @author malyelfik
 */
public final class AdminManor implements IAdminCommandHandler {
	@Override
	public boolean useAdminCommand(String command, Player activeChar) {
		final CastleManorManager manor = CastleManorManager.getInstance();
		final NpcHtmlMessage msg = new NpcHtmlMessage(0, 1);
		msg.setFile(activeChar.getHtmlPrefix(), "admin/manor.htm");
		msg.replace("%status%", manor.getCurrentModeName());
		msg.replace("%change%", manor.getNextModeChange());

		final StringBuilder sb = new StringBuilder(3400);
		for (Castle c : CastleManager.getInstance().getCastles()) {
			sb.append("<tr><td>Name:</td><td><font color=008000>" + c.getName() + "</font></td></tr>");
			sb.append("<tr><td>Current period cost:</td><td><font color=FF9900>" + Util.formatAdena(manor.getManorCost(c.getResidenceId(), false)) + " Adena</font></td></tr>");
			sb.append("<tr><td>Next period cost:</td><td><font color=FF9900>" + Util.formatAdena(manor.getManorCost(c.getResidenceId(), true)) + " Adena</font></td></tr>");
			sb.append("<tr><td><font color=808080>--------------------------</font></td><td><font color=808080>--------------------------</font></td></tr>");
		}
		msg.replace("%castleInfo%", sb.toString());
		activeChar.sendPacket(msg);

		sb.setLength(0);
		return true;
	}

	@Override
	public String[] getAdminCommandList() {
		return new String[]
				{
						"admin_manor"
				};
	}

	public static void main(String[] args) {
		AdminCommandHandler.getInstance().registerHandler(new AdminManor());
	}
}