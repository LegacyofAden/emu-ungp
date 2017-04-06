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

import lombok.extern.slf4j.Slf4j;
import org.l2junity.gameserver.model.TradeItem;
import org.l2junity.gameserver.model.TradeList;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.TradeOtherAdd;
import org.l2junity.gameserver.network.packets.s2c.TradeOwnAdd;
import org.l2junity.gameserver.network.packets.s2c.TradeUpdate;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;

@Slf4j
public final class AddTradeItem extends GameClientPacket {
	private int _tradeId;
	private int _objectId;
	private long _count;

	@Override
	public void readImpl() {
		_tradeId = readD();
		_objectId = readD();
		_count = readQ();
	}

	@Override
	public void runImpl() {
		final Player player = getClient().getActiveChar();
		if (player == null) {
			return;
		}

		final TradeList trade = player.getActiveTradeList();
		if (trade == null) {
			log.warn("Character: " + player.getName() + " requested item:" + _objectId + " add without active tradelist:" + _tradeId);
			return;
		}

		final Player partner = trade.getPartner();
		if ((partner == null) || (player.getWorld().getPlayer(partner.getObjectId()) == null) || (partner.getActiveTradeList() == null)) {
			// Trade partner not found, cancel trade
			if (partner != null) {
				log.warn("Character:" + player.getName() + " requested invalid trade object: " + _objectId);
			}
			player.sendPacket(SystemMessageId.THAT_PLAYER_IS_NOT_ONLINE);
			player.cancelActiveTrade();
			return;
		}

		if (trade.isConfirmed() || partner.getActiveTradeList().isConfirmed()) {
			player.sendPacket(SystemMessageId.YOU_MAY_NO_LONGER_ADJUST_ITEMS_IN_THE_TRADE_BECAUSE_THE_TRADE_HAS_BEEN_CONFIRMED);
			return;
		}

		if (!player.getAccessLevel().allowTransaction()) {
			player.sendMessage("Transactions are disabled for your Access Level.");
			player.cancelActiveTrade();
			return;
		}

		if (!player.validateItemManipulation(_objectId, "trade")) {
			player.sendPacket(SystemMessageId.NOTHING_HAPPENED);
			return;
		}

		final TradeItem item = trade.addItem(_objectId, _count);
		if (item != null) {
			player.sendPacket(new TradeOwnAdd(item));
			player.sendPacket(new TradeUpdate(player, item));
			partner.sendPacket(new TradeOtherAdd(item));
		}
	}
}
