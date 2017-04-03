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
package org.l2junity.gameserver.network.client.recv;

import org.l2junity.gameserver.instancemanager.ItemAuctionManager;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.itemauction.ItemAuction;
import org.l2junity.gameserver.model.itemauction.ItemAuctionInstance;
import org.l2junity.gameserver.model.itemcontainer.Inventory;
import org.l2junity.gameserver.model.itemcontainer.ItemContainer;
import org.l2junity.gameserver.network.client.L2GameClient;
import org.l2junity.network.PacketReader;

/**
 * @author Forsaiken
 */
public final class RequestBidItemAuction implements IClientIncomingPacket {
	private int _instanceId;
	private long _bid;

	@Override
	public boolean read(L2GameClient client, PacketReader packet) {
		_instanceId = packet.readD();
		_bid = packet.readQ();
		return true;
	}

	@Override
	public void run(L2GameClient client) {
		final Player activeChar = client.getActiveChar();
		if (activeChar == null) {
			return;
		}

		// can't use auction fp here
		if (!client.getFloodProtectors().getTransaction().tryPerformAction("auction")) {
			activeChar.sendMessage("You are bidding too fast.");
			return;
		}

		if (!ItemContainer.validateCount(Inventory.ADENA_ID, _bid)) {
			return;
		}

		final ItemAuctionInstance instance = ItemAuctionManager.getInstance().getManagerInstance(_instanceId);
		if (instance != null) {
			final ItemAuction auction = instance.getCurrentAuction();
			if (auction != null) {
				auction.registerBid(activeChar, _bid);
			}
		}
	}
}