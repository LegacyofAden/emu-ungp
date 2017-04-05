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

import java.util.StringTokenizer;

import org.l2junity.gameserver.data.HtmRepository;
import org.l2junity.gameserver.data.sql.impl.ClanTable;
import org.l2junity.gameserver.data.xml.impl.ClanHallData;
import org.l2junity.gameserver.handler.AdminCommandHandler;
import org.l2junity.gameserver.handler.IAdminCommandHandler;
import org.l2junity.gameserver.instancemanager.CastleManager;
import org.l2junity.gameserver.instancemanager.FortManager;
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.ClanMember;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.client.send.NpcHtmlMessage;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;
import org.l2junity.gameserver.util.Util;

/**
 * @author UnAfraid, Zoey76
 */
public class AdminClan implements IAdminCommandHandler {
	private static final String[] ADMIN_COMMANDS =
			{
					"admin_clan_info",
					"admin_clan_changeleader",
					"admin_clan_show_pending",
					"admin_clan_force_pending"
			};

	@Override
	public boolean useAdminCommand(String command, Player activeChar) {
		final StringTokenizer st = new StringTokenizer(command);
		final String cmd = st.nextToken();
		switch (cmd) {
			case "admin_clan_info": {
				final Player player = getPlayer(activeChar, st);
				if (player == null) {
					break;
				}

				final Clan clan = player.getClan();
				if (clan == null) {
					activeChar.sendPacket(SystemMessageId.THE_TARGET_MUST_BE_A_CLAN_MEMBER);
					return false;
				}

				final NpcHtmlMessage html = new NpcHtmlMessage(0, 1);
				html.setHtml(HtmRepository.getInstance().getCustomHtm("admin/claninfo.htm"));
				html.replace("%clan_name%", clan.getName());
				html.replace("%clan_leader%", clan.getLeaderName());
				html.replace("%clan_level%", String.valueOf(clan.getLevel()));
				html.replace("%clan_has_castle%", clan.getCastleId() > 0 ? CastleManager.getInstance().getCastleById(clan.getCastleId()).getName() : "No");
				html.replace("%clan_has_clanhall%", clan.getHideoutId() > 0 ? ClanHallData.getInstance().getClanHallById(clan.getHideoutId()).getName() : "No");
				html.replace("%clan_has_fortress%", clan.getFortId() > 0 ? FortManager.getInstance().getFortById(clan.getFortId()).getName() : "No");
				html.replace("%clan_points%", String.valueOf(clan.getReputationScore()));
				html.replace("%clan_players_count%", String.valueOf(clan.getMembersCount()));
				html.replace("%clan_ally%", clan.getAllyId() > 0 ? clan.getAllyName() : "Not in ally");
				html.replace("%current_player_objectId%", String.valueOf(player.getObjectId()));
				html.replace("%current_player_name%", player.getName());
				activeChar.sendPacket(html);
				break;
			}
			case "admin_clan_changeleader": {
				final Player player = getPlayer(activeChar, st);
				if (player == null) {
					break;
				}

				final Clan clan = player.getClan();
				if (clan == null) {
					activeChar.sendPacket(SystemMessageId.THE_TARGET_MUST_BE_A_CLAN_MEMBER);
					return false;
				}

				final ClanMember member = clan.getClanMember(player.getObjectId());
				if (member != null) {
					if (player.isAcademyMember()) {
						player.sendPacket(SystemMessageId.THAT_PRIVILEGE_CANNOT_BE_GRANTED_TO_A_CLAN_ACADEMY_MEMBER);
					} else {
						clan.setNewLeader(member);
					}
				}
				break;
			}
			case "admin_clan_show_pending": {
				final NpcHtmlMessage html = new NpcHtmlMessage(0, 1);
				html.setHtml(HtmRepository.getInstance().getCustomHtm("admin/clanchanges.htm"));
				StringBuilder sb = new StringBuilder();
				for (Clan clan : ClanTable.getInstance().getClans()) {
					if (clan.getNewLeaderId() != 0) {
						sb.append("<tr>");
						sb.append("<td>" + clan.getName() + "</td>");
						sb.append("<td>" + clan.getNewLeaderName() + "</td>");
						sb.append("<td><a action=\"bypass -h admin_clan_force_pending " + clan.getId() + "\">Force</a></td>");
						sb.append("</tr>");
					}
				}
				html.replace("%data%", sb.toString());
				activeChar.sendPacket(html);
				break;
			}
			case "admin_clan_force_pending": {
				if (st.hasMoreElements()) {
					String token = st.nextToken();
					if (!Util.isDigit(token)) {
						break;
					}
					int clanId = Integer.parseInt(token);

					final Clan clan = ClanTable.getInstance().getClan(clanId);
					if (clan == null) {
						break;
					}

					final ClanMember member = clan.getClanMember(clan.getNewLeaderId());
					if (member == null) {
						break;
					}

					clan.setNewLeader(member);
					activeChar.sendMessage("Task have been forcely executed.");
					break;
				}
			}
		}
		return true;
	}

	/**
	 * @param activeChar
	 * @param st
	 * @return
	 */
	private Player getPlayer(Player activeChar, StringTokenizer st) {
		String val;
		Player player = null;
		if (st.hasMoreTokens()) {
			val = st.nextToken();
			// From the HTML we receive player's object Id.
			if (Util.isDigit(val)) {
				player = WorldManager.getInstance().getPlayer(Integer.parseInt(val));
				if (player == null) {
					activeChar.sendPacket(SystemMessageId.THAT_PLAYER_IS_NOT_ONLINE);
					return null;
				}
			} else {
				player = WorldManager.getInstance().getPlayer(val);
				if (player == null) {
					activeChar.sendPacket(SystemMessageId.INCORRECT_NAME_PLEASE_TRY_AGAIN);
					return null;
				}
			}
		} else {
			WorldObject targetObj = activeChar.getTarget();
			if (targetObj instanceof Player) {
				player = targetObj.getActingPlayer();
			} else {
				activeChar.sendPacket(SystemMessageId.INVALID_TARGET);
				return null;
			}
		}
		return player;
	}

	@Override
	public String[] getAdminCommandList() {
		return ADMIN_COMMANDS;
	}

	public static void main(String[] args) {
		AdminCommandHandler.getInstance().registerHandler(new AdminClan());
	}
}