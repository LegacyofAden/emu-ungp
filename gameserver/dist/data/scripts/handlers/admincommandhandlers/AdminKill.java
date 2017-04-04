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

import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.core.configs.L2JModsConfig;
import org.l2junity.gameserver.handler.AdminCommandHandler;
import org.l2junity.gameserver.handler.IAdminCommandHandler;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.ControllableMobInstance;
import org.l2junity.gameserver.model.actor.instance.FriendlyNpcInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class handles following admin commands: - kill = kills target L2Character - kill_monster = kills target non-player - kill <radius> = If radius is specified, then ALL players only in that radius will be killed. - kill_monster <radius> = If radius is specified, then ALL non-players only in
 * that radius will be killed.
 *
 * @version $Revision: 1.2.4.5 $ $Date: 2007/07/31 10:06:06 $
 */
public class AdminKill implements IAdminCommandHandler {
	private static Logger _log = LoggerFactory.getLogger(AdminKill.class);
	private static final String[] ADMIN_COMMANDS =
			{
					"admin_kill",
					"admin_killnpc",
					"admin_kill_monster"
			};

	@Override
	public boolean useAdminCommand(String command, Player activeChar) {
		if (command.startsWith("admin_kill")) {
			StringTokenizer st = new StringTokenizer(command, " ");
			st.nextToken(); // skip command

			if (st.hasMoreTokens()) {
				String firstParam = st.nextToken();
				Player plyr = WorldManager.getInstance().getPlayer(firstParam);
				if (plyr != null) {
					if (st.hasMoreTokens()) {
						try {
							int radius = Integer.parseInt(st.nextToken());
							plyr.getWorld().forEachVisibleObjectInRadius(plyr, Creature.class, radius, knownChar ->
							{
								if ((knownChar instanceof ControllableMobInstance) || (knownChar instanceof FriendlyNpcInstance) || (knownChar == activeChar)) {
									return;
								}

								kill(activeChar, knownChar);
							});

							activeChar.sendMessage("Killed all characters within a " + radius + " unit radius.");
							return true;
						} catch (NumberFormatException e) {
							activeChar.sendMessage("Invalid radius.");
							return false;
						}
					}
					kill(activeChar, plyr);
				} else {
					try {
						int radius = Integer.parseInt(firstParam);

						activeChar.getWorld().forEachVisibleObjectInRadius(activeChar, Creature.class, radius, wo ->
						{
							if ((wo instanceof ControllableMobInstance) || (wo instanceof FriendlyNpcInstance)) {
								return;
							}
							kill(activeChar, wo);
						});

						activeChar.sendMessage("Killed all characters within a " + radius + " unit radius.");
						return true;
					} catch (NumberFormatException e) {
						activeChar.sendMessage("Usage: //kill <player_name | radius>");
						return false;
					}
				}
			} else {
				WorldObject obj = activeChar.getTarget();
				if ((obj instanceof ControllableMobInstance) || !(obj instanceof Creature)) {
					activeChar.sendPacket(SystemMessageId.INVALID_TARGET);
				} else {
					kill(activeChar, (Creature) obj);
				}
			}
		}
		return true;
	}

	private void kill(Player activeChar, Creature target) {
		if (target instanceof Player) {
			if (!target.isGM()) {
				target.stopAllEffects(); // e.g. invincibility effect
			}
			target.reduceCurrentHp(target.getMaxHp() + target.getMaxCp() + 1, activeChar, null);
		} else if (L2JModsConfig.L2JMOD_CHAMPION_ENABLE && target.isChampion()) {
			target.reduceCurrentHp((target.getMaxHp() * L2JModsConfig.L2JMOD_CHAMPION_HP) + 1, activeChar, null);
		} else {
			boolean targetIsInvul = false;
			if (target.isInvul()) {
				targetIsInvul = true;
				target.setIsInvul(false);
			}

			target.reduceCurrentHp(target.getMaxHp() + 1, activeChar, null);

			if (targetIsInvul) {
				target.setIsInvul(true);
			}
		}
		if (GeneralConfig.DEBUG) {
			_log.debug("GM: " + activeChar.getName() + "(" + activeChar.getObjectId() + ")" + " killed character " + target.getObjectId());
		}
	}

	@Override
	public String[] getAdminCommandList() {
		return ADMIN_COMMANDS;
	}

	public static void main(String[] args) {
		AdminCommandHandler.getInstance().registerHandler(new AdminKill());
	}
}