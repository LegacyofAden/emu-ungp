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

import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.handler.AdminCommandHandler;
import org.l2junity.gameserver.handler.IAdminCommandHandler;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.StaticObjectInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.client.send.CreatureSay;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;

/**
 * This class handles following admin commands: - targetsay <message> = makes talk a L2Character
 *
 * @author nonom
 */
public class AdminTargetSay implements IAdminCommandHandler {
	private static final String[] ADMIN_COMMANDS =
			{
					"admin_targetsay"
			};

	@Override
	public boolean useAdminCommand(String command, Player activeChar) {
		if (command.startsWith("admin_targetsay")) {
			try {
				final WorldObject obj = activeChar.getTarget();
				if ((obj instanceof StaticObjectInstance) || !(obj instanceof Creature)) {
					activeChar.sendPacket(SystemMessageId.INVALID_TARGET);
					return false;
				}

				final String message = command.substring(16);
				final Creature target = (Creature) obj;
				target.broadcastPacket(new CreatureSay(target.getObjectId(), target.isPlayer() ? ChatType.GENERAL : ChatType.NPC_GENERAL, target.getName(), message));
			} catch (StringIndexOutOfBoundsException e) {
				activeChar.sendMessage("Usage: //targetsay <text>");
				return false;
			}
		}
		return true;
	}

	@Override
	public String[] getAdminCommandList() {
		return ADMIN_COMMANDS;
	}

	public static void main(String[] args) {
		AdminCommandHandler.getInstance().registerHandler(new AdminTargetSay());
	}
}