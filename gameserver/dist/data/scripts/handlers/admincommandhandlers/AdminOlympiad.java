/*
 * Copyright (C) 2004-2015 L2J Unity
 * 
 * This file is part of L2J Unity.
 * 
 * L2J Unity is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J Unity is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package handlers.admincommandhandlers;

import java.util.StringTokenizer;

import org.l2junity.gameserver.handler.AdminCommandHandler;
import org.l2junity.gameserver.handler.IAdminCommandHandler;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.olympiad.Olympiad;
import org.l2junity.gameserver.model.olympiad.OlympiadGameManager;
import org.l2junity.gameserver.model.olympiad.OlympiadGameNonClassed;
import org.l2junity.gameserver.model.olympiad.OlympiadGameTask;
import org.l2junity.gameserver.model.olympiad.OlympiadManager;
import org.l2junity.gameserver.model.olympiad.Participant;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;
import org.l2junity.gameserver.util.Util;

/**
 * @author UnAfraid
 */
public class AdminOlympiad implements IAdminCommandHandler {
	private static final String[] ADMIN_COMMANDS =
			{
					"admin_olympiad_game",
					"admin_addolypoints",
					"admin_removeolypoints",
					"admin_setolypoints",
			};

	@Override
	public boolean useAdminCommand(String command, Player activeChar) {
		final StringTokenizer st = new StringTokenizer(command);
		final String cmd = st.nextToken();
		switch (cmd) {
			case "admin_olympiad_game": {
				if (!st.hasMoreTokens()) {
					activeChar.sendMessage("Syntax: //olympiad_game <player name>");
					return false;
				}

				final Player player = WorldManager.getInstance().getMainWorld().getPlayer(st.nextToken());
				if (player == null) {
					activeChar.sendPacket(SystemMessageId.YOUR_TARGET_CANNOT_BE_FOUND);
					return false;
				}

				if (player == activeChar) {
					activeChar.sendPacket(SystemMessageId.YOU_CANNOT_USE_THIS_ON_YOURSELF);
					return false;
				}

				if (!checkplayer(player, activeChar) || !checkplayer(activeChar, activeChar)) {
					return false;
				}

				for (int i = 0; i < OlympiadGameManager.getInstance().getNumberOfStadiums(); i++) {
					final OlympiadGameTask task = OlympiadGameManager.getInstance().getOlympiadTask(i);
					if (task != null) {
						synchronized (task) {
							if (!task.isRunning()) {
								final Participant[] players = new Participant[2];
								players[0] = new Participant(activeChar, 1);
								players[1] = new Participant(player, 2);
								task.attachGame(new OlympiadGameNonClassed(i, players));
								return true;
							}
						}
					}
				}
				break;
			}
			case "admin_addolypoints": {
				final WorldObject target = activeChar.getTarget();
				final Player player = target != null ? target.getActingPlayer() : null;
				if (player != null) {
					final int val = parseInt(st, Integer.MIN_VALUE);
					if (val == Integer.MIN_VALUE) {
						activeChar.sendMessage("Syntax: //addolypoints <points>");
						return false;
					}

					if (player.isNoble()) {
						final StatsSet statDat = getPlayerSet(player);
						final int oldpoints = Olympiad.getInstance().getNoblePoints(player);
						final int points = Math.max(oldpoints + val, 0);
						if (points > 1000) {
							activeChar.sendMessage("You can't set more than 1000 or less than 0 Olympiad points!");
							return false;
						}

						statDat.set(Olympiad.POINTS, points);
						activeChar.sendMessage("Player " + player.getName() + " now has " + points + " Olympiad points.");
					} else {
						activeChar.sendMessage("This player is not noblesse!");
						return false;
					}
				} else {
					activeChar.sendMessage("Usage: target a player and write the amount of points you would like to add.");
					activeChar.sendMessage("Example: //addolypoints 10");
					activeChar.sendMessage("However, keep in mind that you can't have less than 0 or more than 1000 points.");
				}
				break;
			}
			case "admin_removeolypoints": {
				final WorldObject target = activeChar.getTarget();
				final Player player = target != null ? target.getActingPlayer() : null;
				if (player != null) {
					final int val = parseInt(st, Integer.MIN_VALUE);
					if (val == Integer.MIN_VALUE) {
						activeChar.sendMessage("Syntax: //removeolypoints <points>");
						return false;
					}

					if (player.isNoble()) {
						final StatsSet playerStat = Olympiad.getNobleStats(player.getObjectId());
						if (playerStat == null) {
							activeChar.sendMessage("This player hasn't played on Olympiad yet!");
							return false;
						}

						final int oldpoints = Olympiad.getInstance().getNoblePoints(player);
						final int points = Math.max(oldpoints - val, 0);
						playerStat.set(Olympiad.POINTS, points);

						activeChar.sendMessage("Player " + player.getName() + " now has " + points + " Olympiad points.");
					} else {
						activeChar.sendMessage("This player is not noblesse!");
						return false;
					}
				} else {
					activeChar.sendMessage("Usage: target a player and write the amount of points you would like to remove.");
					activeChar.sendMessage("Example: //removeolypoints 10");
					activeChar.sendMessage("However, keep in mind that you can't have less than 0 or more than 1000 points.");
				}
				break;
			}
			case "admin_setolypoints": {
				final WorldObject target = activeChar.getTarget();
				final Player player = target != null ? target.getActingPlayer() : null;
				if (player != null) {
					final int val = parseInt(st, Integer.MIN_VALUE);
					if (val == Integer.MIN_VALUE) {
						activeChar.sendMessage("Syntax: //setolypoints <points>");
						return false;
					}

					if (player.isNoble()) {
						final StatsSet statDat = getPlayerSet(player);
						final int oldpoints = Olympiad.getInstance().getNoblePoints(player);
						final int points = oldpoints - val;
						if ((points < 1) && (points > 1000)) {
							activeChar.sendMessage("You can't set more than 1000 or less than 0 Olympiad points! or lower then 0");
							return false;
						}

						statDat.set(Olympiad.POINTS, points);
						activeChar.sendMessage("Player " + player.getName() + " now has " + points + " Olympiad points.");
					} else {
						activeChar.sendMessage("This player is not noblesse!");
						return false;
					}
				} else {
					activeChar.sendMessage("Usage: target a player and write the amount of points you would like to set.");
					activeChar.sendMessage("Example: //setolypoints 10");
					activeChar.sendMessage("However, keep in mind that you can't have less than 0 or more than 1000 points.");
				}
				break;
			}
		}
		return false;
	}

	private int parseInt(StringTokenizer st, int defaultVal) {
		final String token = st.nextToken();
		if (!Util.isDigit(token)) {
			return -1;
		}
		return Integer.decode(token);
	}

	private StatsSet getPlayerSet(Player player) {
		StatsSet statDat = Olympiad.getNobleStats(player.getObjectId());
		if (statDat == null) {
			statDat = new StatsSet();
			statDat.set(Olympiad.CLASS_ID, player.getBaseClass());
			statDat.set(Olympiad.CHAR_NAME, player.getName());
			statDat.set(Olympiad.POINTS, Olympiad.DEFAULT_POINTS);
			statDat.set(Olympiad.COMP_DONE, 0);
			statDat.set(Olympiad.COMP_WON, 0);
			statDat.set(Olympiad.COMP_LOST, 0);
			statDat.set(Olympiad.COMP_DRAWN, 0);
			statDat.set(Olympiad.COMP_DONE_WEEK, 0);
			statDat.set(Olympiad.COMP_DONE_WEEK_CLASSED, 0);
			statDat.set(Olympiad.COMP_DONE_WEEK_NON_CLASSED, 0);
			statDat.set(Olympiad.COMP_DONE_WEEK_TEAM, 0);
			statDat.set("to_save", true);
			Olympiad.addNobleStats(player.getObjectId(), statDat);
		}
		return statDat;
	}

	private boolean checkplayer(Player player, Player activeChar) {
		if (player.isSubClassActive()) {
			activeChar.sendMessage("Player " + player + " subclass active.");
			return false;
		} else if (!player.isAwakenedClass()) {
			activeChar.sendMessage("Player " + player + " has not awakened.");
			return false;
		} else if (Olympiad.getInstance().getNoblePoints(player) <= 0) {
			activeChar.sendMessage("Player " + player + " has 0 oly points (add them with (//addolypoints).");
			return false;
		} else if (OlympiadManager.getInstance().isRegistered(player)) {
			activeChar.sendMessage("Player " + player + " registered to oly.");
			return false;
		}
		return true;
	}

	@Override
	public String[] getAdminCommandList() {
		return ADMIN_COMMANDS;
	}

	public static void main(String[] args) {
		AdminCommandHandler.getInstance().registerHandler(new AdminOlympiad());
	}
}