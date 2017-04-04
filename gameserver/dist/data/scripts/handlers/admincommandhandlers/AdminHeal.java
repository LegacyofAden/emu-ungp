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

import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.gameserver.handler.AdminCommandHandler;
import org.l2junity.gameserver.handler.IAdminCommandHandler;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class handles following admin commands: - heal = restores HP/MP/CP on target, name or radius
 *
 * @version $Revision: 1.2.4.5 $ $Date: 2005/04/11 10:06:06 $ Small typo fix by Zoey76 24/02/2011
 */
public class AdminHeal implements IAdminCommandHandler {
	private static Logger LOGGER = LoggerFactory.getLogger(AdminHeal.class);

	private static final String[] ADMIN_COMMANDS =
			{
					"admin_heal"
			};

	@Override
	public boolean useAdminCommand(String command, Player activeChar) {

		if (command.equals("admin_heal")) {
			handleHeal(activeChar);
		} else if (command.startsWith("admin_heal")) {
			try {
				String healTarget = command.substring(11);
				handleHeal(activeChar, healTarget);
			} catch (StringIndexOutOfBoundsException e) {
				if (GeneralConfig.DEVELOPER) {
					LOGGER.warn("Heal error", e);
				}
				activeChar.sendMessage("Incorrect target/radius specified.");
			}
		}
		return true;
	}

	@Override
	public String[] getAdminCommandList() {
		return ADMIN_COMMANDS;
	}

	private void handleHeal(Player activeChar) {
		handleHeal(activeChar, null);
	}

	private void handleHeal(Player activeChar, String player) {

		WorldObject obj = activeChar.getTarget();
		if (player != null) {
			Player plyr = WorldManager.getInstance().getPlayer(player);

			if (plyr != null) {
				obj = plyr;
			} else {
				try {
					int radius = Integer.parseInt(player);
					activeChar.getWorld().forEachVisibleObject(activeChar, Creature.class, character ->
					{
						character.setCurrentHpMp(character.getMaxHp(), character.getMaxMp());
						if (character instanceof Player) {
							character.setCurrentCp(character.getMaxCp());
						}
					});

					activeChar.sendMessage("Healed within " + radius + " unit radius.");
					return;
				} catch (NumberFormatException nbe) {
				}
			}
		}
		if (obj == null) {
			obj = activeChar;
		}
		if (obj instanceof Creature) {
			Creature target = (Creature) obj;
			target.setCurrentHpMp(target.getMaxHp(), target.getMaxMp());
			if (target instanceof Player) {
				target.setCurrentCp(target.getMaxCp());
			}
			if (GeneralConfig.DEBUG) {
				LOGGER.debug("GM: {}({}) healed character {}", activeChar.getName(), activeChar.getObjectId(), target.getName());
			}
		} else {
			activeChar.sendPacket(SystemMessageId.INVALID_TARGET);
		}
	}

	public static void main(String[] args) {
		AdminCommandHandler.getInstance().registerHandler(new AdminHeal());
	}
}