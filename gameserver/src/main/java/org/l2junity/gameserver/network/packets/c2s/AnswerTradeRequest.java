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

import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ActionFailed;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.TradeDone;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.network.PacketReader;

public final class AnswerTradeRequest extends GameClientPacket {
	private int _response;

	@Override
	public void readImpl() {
		_response = readD();

	}

	@Override
	public void runImpl() {
		final Player player = getClient().getActiveChar();
		if (player == null) {
			return;
		}

		if (!player.getAccessLevel().allowTransaction()) {
			player.sendPacket(SystemMessageId.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT);
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		Player partner = player.getActiveRequester();
		if (partner == null) {
			// Trade partner not found, cancel trade
			player.sendPacket(new TradeDone(0));
			player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.THAT_PLAYER_IS_NOT_ONLINE));
			player.setActiveRequester(null);
			return;
		} else if (player.getWorld().getPlayer(partner.getObjectId()) == null) {
			// Trade partner not found, cancel trade
			player.sendPacket(new TradeDone(0));
			player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.THAT_PLAYER_IS_NOT_ONLINE));
			player.setActiveRequester(null);
			return;
		}

		if ((_response == 1) && !partner.isRequestExpired()) {
			player.startTrade(partner);
		} else {
			SystemMessage msg = SystemMessage.getSystemMessage(SystemMessageId.C1_HAS_DENIED_YOUR_REQUEST_TO_TRADE);
			msg.addString(player.getName());
			partner.sendPacket(msg);
		}

		// Clears requesting status
		player.setActiveRequester(null);
		partner.onTransactionResponse();
	}
}
