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

import java.util.Collection;
import java.util.StringTokenizer;

import org.l2junity.gameserver.data.HtmRepository;
import org.l2junity.gameserver.handler.AdminCommandHandler;
import org.l2junity.gameserver.handler.IAdminCommandHandler;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.packets.s2c.NpcHtmlMessage;
import org.l2junity.gameserver.util.Util;

/**
 * Admin Pc Cafe Points manage admin commands.
 *
 * @author St3eT
 */
public final class AdminPcCafePoints implements IAdminCommandHandler {
	private static final String[] ADMIN_COMMANDS =
			{
					"admin_pccafepoints",
			};

	@Override
	public boolean useAdminCommand(String command, Player activeChar) {
		final StringTokenizer st = new StringTokenizer(command, " ");
		final String actualCommand = st.nextToken();

		if (actualCommand.equals("admin_pccafepoints")) {
			if (st.hasMoreTokens()) {
				final String action = st.nextToken();

				final Player target = getTarget(activeChar);
				if ((target == null) || !st.hasMoreTokens()) {
					return false;
				}

				int value = 0;
				try {
					value = Integer.parseInt(st.nextToken());
				} catch (Exception e) {
					showMenuHtml(activeChar);
					activeChar.sendMessage("Invalid Value!");
					return false;
				}

				switch (action) {
					case "increase": {
						if (target.getPcCafePoints() >= 200_000) {
							showMenuHtml(activeChar);
							activeChar.sendMessage(target.getName() + " already have max count of Pc Cafe Points!");
							return false;
						}

						activeChar.increasePcCafePoints(value);
						target.sendMessage("Admin increase your Pc Cafe Point(s) by " + value + "!");
						activeChar.sendMessage("You increased Pc Cafe Point(s) of " + target.getName() + " by " + value);
						break;
					}
					case "decrease": {
						if (target.getPcCafePoints() == 0) {
							showMenuHtml(activeChar);
							activeChar.sendMessage(target.getName() + " already have min count of Pc Cafe Points!");
							return false;
						}

						activeChar.decreasePcCafePoints(value);
						target.sendMessage("Admin decreased your Pc Cafe Point(s) by " + value + "!");
						activeChar.sendMessage("You decreased Pc Cafe Point(s) of " + target.getName() + " by " + value);
						break;
					}
					case "rewardOnline": {
						int range = 0;
						try {
							range = Integer.parseInt(st.nextToken());
						} catch (Exception e) {

						}

						if (range <= 0) {
							final int count = increaseForAll(WorldManager.getInstance().getAllPlayers(), value);
							activeChar.sendMessage("You increased Pc Cafe Point(s) of all online players (" + count + ") by " + value + ".");
						} else if (range > 0) {
							final int count = increaseForAll(activeChar.getWorld().getVisibleObjects(activeChar, Player.class, range), value);
							activeChar.sendMessage("You increased Pc Cafe Point(s) of all players (" + count + ") in range " + range + " by " + value + ".");
						}
						break;
					}
				}
				showMenuHtml(activeChar);
			} else {
				showMenuHtml(activeChar);
			}
		}
		return true;
	}

	private int increaseForAll(Collection<Player> playerList, int value) {
		int counter = 0;
		for (Player temp : playerList) {
			if ((temp != null) && (temp.isOnlineInt() == 1)) {
				if (temp.getPcCafePoints() >= 200_000) {
					continue;
				}

				temp.increasePcCafePoints(value);
				temp.sendMessage("Admin increase your Pc Cafe Point(s) by " + value + "!");
				counter++;
			}
		}
		return counter;
	}

	private Player getTarget(Player activeChar) {
		return ((activeChar.getTarget() != null) && (activeChar.getTarget().getActingPlayer() != null)) ? activeChar.getTarget().getActingPlayer() : activeChar;
	}

	private void showMenuHtml(Player activeChar) {
		final NpcHtmlMessage html = new NpcHtmlMessage(0, 1);
		final Player target = getTarget(activeChar);
		final long points = target.getPcCafePoints();
		html.setHtml(HtmRepository.getInstance().getCustomHtm("admin/pccafepoints.htm"));
		html.replace("%points%", Util.formatAdena(points));
		html.replace("%targetName%", target.getName());
		activeChar.sendPacket(html);
	}

	@Override
	public String[] getAdminCommandList() {
		return ADMIN_COMMANDS;
	}

	public static void main(String[] args) {
		AdminCommandHandler.getInstance().registerHandler(new AdminPcCafePoints());
	}
}