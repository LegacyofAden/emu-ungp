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

import org.l2junity.gameserver.model.TradeList;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;


/**
 * This packet manages the trade response.
 */
public final class TradeDone extends GameClientPacket {
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

		if (!getClient().getFloodProtectors().getTransaction().tryPerformAction("trade")) {
			player.sendMessage("You are trading too fast.");
			return;
		}

		final TradeList trade = player.getActiveTradeList();
		if (trade == null) {
			return;
		}

		if (trade.isLocked()) {
			return;
		}

		if (_response == 1) {
			if ((trade.getPartner() == null) || (player.getWorld().getPlayer(trade.getPartner().getObjectId()) == null)) {
				// Trade partner not found, cancel trade
				player.cancelActiveTrade();
				player.sendPacket(SystemMessageId.THAT_PLAYER_IS_NOT_ONLINE);
				return;
			}

			if ((trade.getOwner().hasItemRequest()) || (trade.getPartner().hasItemRequest())) {
				return;
			}

			if (!player.getAccessLevel().allowTransaction()) {
				player.cancelActiveTrade();
				player.sendPacket(SystemMessageId.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT);
				return;
			}

			if (player.getInstanceWorld() != trade.getPartner().getInstanceWorld()) {
				player.cancelActiveTrade();
				return;
			}

			if (!player.isInRadius3d(trade.getPartner(), 150)) {
				player.cancelActiveTrade();
				return;
			}
			trade.confirm();
		} else {
			player.cancelActiveTrade();
		}
	}
}