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
package handlers.communityboard;

import org.l2junity.commons.sql.DatabaseFactory;
import org.l2junity.gameserver.data.HtmRepository;
import org.l2junity.gameserver.data.sql.impl.ClanTable;
import org.l2junity.gameserver.handler.CommunityBoardHandler;
import org.l2junity.gameserver.handler.IParseBoardHandler;
import org.l2junity.gameserver.model.actor.instance.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Home board.
 *
 * @author Zoey76
 */
public final class HomeBoard implements IParseBoardHandler {
	// SQL Queries
	private static final String COUNT_FAVORITES = "SELECT COUNT(*) AS favorites FROM `bbs_favorites` WHERE `playerId`=?";

	private static final String[] COMMANDS =
			{
					"_bbshome",
					"_bbstop"
			};

	@Override
	public String[] getCommunityBoardCommands() {
		return COMMANDS;
	}

	@Override
	public boolean parseCommunityBoardCommand(String command, Player activeChar) {
		if (command.equals("_bbshome") || command.equals("_bbstop")) {
			CommunityBoardHandler.getInstance().addBypass(activeChar, "Home", command);

			String html = HtmRepository.getInstance().getCustomHtm("CommunityBoard/home.html");
			html = html.replaceAll("%fav_count%", Integer.toString(getFavoriteCount(activeChar)));
			html = html.replaceAll("%region_count%", Integer.toString(getRegionCount(activeChar)));
			html = html.replaceAll("%clan_count%", Integer.toString(ClanTable.getInstance().getClanCount()));
			CommunityBoardHandler.separateAndSend(html, activeChar);
		} else if (command.startsWith("_bbstop;")) {
			final String path = command.replace("_bbstop;", "");
			if ((path.length() > 0) && path.endsWith(".html")) {
				final String html = HtmRepository.getInstance().getCustomHtm("CommunityBoard/" + path);
				CommunityBoardHandler.separateAndSend(html, activeChar);
			}
		}
		return true;
	}

	/**
	 * Gets the Favorite links for the given player.
	 *
	 * @param player the player
	 * @return the favorite links count
	 */
	private static int getFavoriteCount(Player player) {
		int count = 0;
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps = con.prepareStatement(COUNT_FAVORITES)) {
			ps.setInt(1, player.getObjectId());
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					count = rs.getInt("favorites");
				}
			}
		} catch (Exception e) {
			LOG.warn(FavoriteBoard.class.getSimpleName() + ": Coudn't load favorites count for player " + player.getName());
		}
		return count;
	}

	/**
	 * Gets the registered regions count for the given player.
	 *
	 * @param player the player
	 * @return the registered regions count
	 */
	private static int getRegionCount(Player player) {
		return 0; // TODO: Implement.
	}

	public static void main(String[] args) {
		CommunityBoardHandler.getInstance().registerHandler(new HomeBoard());
	}
}