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
import org.l2junity.gameserver.model.CommandChannel;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.network.client.send.SystemMessage;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;

/**
 * Channel Delete user command.
 *
 * @author Chris
 */
public class ChannelDelete implements IUserCommandHandler {
	private static final int[] COMMAND_IDS =
			{
					93
			};

	@Override
	public boolean useUserCommand(int id, PlayerInstance activeChar) {
		if (id != COMMAND_IDS[0]) {
			return false;
		}

		if (activeChar.isInParty()) {
			if (activeChar.getParty().isLeader(activeChar) && activeChar.getParty().isInCommandChannel() && activeChar.getParty().getCommandChannel().getLeader().equals(activeChar)) {
				CommandChannel channel = activeChar.getParty().getCommandChannel();

				SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.THE_COMMAND_CHANNEL_HAS_BEEN_DISBANDED);
				channel.broadcastPacket(sm);

				channel.disbandChannel();
				return true;
			}
		}

		return false;
	}

	@Override
	public int[] getUserCommandList() {
		return COMMAND_IDS;
	}

	public static void main(String[] args) {
		UserCommandHandler.getInstance().registerHandler(new ChannelDelete());
	}
}