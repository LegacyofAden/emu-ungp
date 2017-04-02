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
package handlers.usercommandhandlers;

import org.l2junity.gameserver.handler.IUserCommandHandler;
import org.l2junity.gameserver.handler.UserCommandHandler;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.network.client.send.NpcHtmlMessage;

import java.text.SimpleDateFormat;

/**
 * Clan Penalty user command.
 *
 * @author Tempy
 */
public class ClanPenalty implements IUserCommandHandler {
	private static final int[] COMMAND_IDS =
			{
					100
			};

	@Override
	public boolean useUserCommand(int id, PlayerInstance activeChar) {
		if (id != COMMAND_IDS[0]) {
			return false;
		}

		boolean penalty = false;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		final StringBuilder htmlContent = new StringBuilder(500);
		htmlContent.append("<html><body><center><table width=270 border=0 bgcolor=111111><tr><td width=170>Penalty</td><td width=100 align=center>Expiration Date</td></tr></table><table width=270 border=0><tr>");

		if (activeChar.getClanJoinExpiryTime() > System.currentTimeMillis()) {
			htmlContent.append("<td width=170>Unable to join a clan.</td><td width=100 align=center>");
			htmlContent.append(format.format(activeChar.getClanJoinExpiryTime()));
			htmlContent.append("</td>");
			penalty = true;
		}

		if (activeChar.getClanCreateExpiryTime() > System.currentTimeMillis()) {
			htmlContent.append("<td width=170>Unable to create a clan.</td><td width=100 align=center>");
			htmlContent.append(format.format(activeChar.getClanCreateExpiryTime()));
			htmlContent.append("</td>");
			penalty = true;
		}

		if ((activeChar.getClan() != null) && (activeChar.getClan().getCharPenaltyExpiryTime() > System.currentTimeMillis())) {
			htmlContent.append("<td width=170>Unable to invite a clan member.</td><td width=100 align=center>");
			htmlContent.append(format.format(activeChar.getClan().getCharPenaltyExpiryTime()));
			htmlContent.append("</td>");
			penalty = true;
		}

		if (!penalty) {
			htmlContent.append("<td width=170>No penalty is imposed.</td><td width=100 align=center></td>");
		}

		htmlContent.append("</tr></table><img src=\"L2UI.SquareWhite\" width=270 height=1></center></body></html>");

		final NpcHtmlMessage penaltyHtml = new NpcHtmlMessage();
		penaltyHtml.setHtml(htmlContent.toString());
		activeChar.sendPacket(penaltyHtml);

		return true;
	}

	@Override
	public int[] getUserCommandList() {
		return COMMAND_IDS;
	}

	public static void main(String[] args) {
		UserCommandHandler.getInstance().registerHandler(new ClanPenalty());
	}
}