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
package org.l2junity.gameserver.network.packets.c2s;

import org.l2junity.gameserver.model.CommandChannel;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.network.PacketReader;

/**
 * format: (ch) d
 *
 * @author -Wooden-
 */
public final class RequestExAcceptJoinMPCC extends GameClientPacket {
	private int _response;

	@Override
	public void readImpl() {
		_response = readD();
	}

	@Override
	public void runImpl() {
		Player player = getClient().getActiveChar();
		if (player != null) {
			Player requestor = player.getActiveRequester();
			SystemMessage sm;
			if (requestor == null) {
				return;
			}

			if (_response == 1) {
				boolean newCc = false;
				if (!requestor.getParty().isInCommandChannel()) {
					new CommandChannel(requestor); // Create new CC
					sm = SystemMessage.getSystemMessage(SystemMessageId.THE_COMMAND_CHANNEL_HAS_BEEN_FORMED);
					requestor.sendPacket(sm);
					newCc = true;
				}
				requestor.getParty().getCommandChannel().addParty(player.getParty());
				if (!newCc) {
					sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_JOINED_THE_COMMAND_CHANNEL);
					player.sendPacket(sm);
				}
			} else {
				requestor.sendMessage("The player declined to join your Command Channel.");
			}

			player.setActiveRequester(null);
			requestor.onTransactionResponse();
		}
	}
}
