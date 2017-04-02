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

import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.enums.PrivateStoreType;
import org.l2junity.gameserver.model.TradeList;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.itemcontainer.Inventory;
import org.l2junity.gameserver.model.itemcontainer.ItemContainer;
import org.l2junity.gameserver.model.zone.ZoneId;
import org.l2junity.gameserver.network.client.L2GameClient;
import org.l2junity.gameserver.network.client.send.ActionFailed;
import org.l2junity.gameserver.network.client.send.ExPrivateStoreSetWholeMsg;
import org.l2junity.gameserver.network.client.send.PrivateStoreManageListSell;
import org.l2junity.gameserver.network.client.send.PrivateStoreMsgSell;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;
import org.l2junity.gameserver.taskmanager.AttackStanceTaskManager;
import org.l2junity.gameserver.util.Util;
import org.l2junity.network.PacketReader;

public class SetPrivateStoreListSell implements IClientIncomingPacket {
	private static final int BATCH_LENGTH = 20; // length of the one item

	private boolean _packageSale;
	private Item[] _items = null;

	@Override
	public boolean read(L2GameClient client, PacketReader packet) {
		_packageSale = (packet.readD() == 1);
		int count = packet.readD();
		if ((count < 1) || (count > PlayerConfig.MAX_ITEM_IN_PACKET) || ((count * BATCH_LENGTH) != packet.getReadableBytes())) {
			return false;
		}

		_items = new Item[count];
		for (int i = 0; i < count; i++) {
			int objectId = packet.readD();
			long cnt = packet.readQ();
			long price = packet.readQ();

			if ((objectId < 1) || (cnt < 1) || (price < 0)) {
				_items = null;
				return false;
			}
			_items[i] = new Item(objectId, cnt, price);
		}
		return true;
	}

	@Override
	public void run(L2GameClient client) {
		PlayerInstance player = client.getActiveChar();
		if (player == null) {
			return;
		}

		if (_items == null) {
			player.sendPacket(SystemMessageId.INCORRECT_ITEM_COUNT);
			player.setPrivateStoreType(PrivateStoreType.NONE);
			player.broadcastUserInfo();
			return;
		}

		if (!player.getAccessLevel().allowTransaction()) {
			player.sendPacket(SystemMessageId.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT);
			return;
		}

		if (AttackStanceTaskManager.getInstance().hasAttackStanceTask(player) || player.isInDuel()) {
			player.sendPacket(SystemMessageId.WHILE_YOU_ARE_ENGAGED_IN_COMBAT_YOU_CANNOT_OPERATE_A_PRIVATE_STORE_OR_PRIVATE_WORKSHOP);
			player.sendPacket(new PrivateStoreManageListSell(player, _packageSale));
			player.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if (player.isInsideZone(ZoneId.NO_STORE)) {
			player.sendPacket(new PrivateStoreManageListSell(player, _packageSale));
			player.sendPacket(SystemMessageId.YOU_CANNOT_OPEN_A_PRIVATE_STORE_HERE);
			player.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		// Check maximum number of allowed slots for pvt shops
		if (_items.length > player.getPrivateSellStoreLimit()) {
			player.sendPacket(new PrivateStoreManageListSell(player, _packageSale));
			player.sendPacket(SystemMessageId.YOU_HAVE_EXCEEDED_THE_QUANTITY_THAT_CAN_BE_INPUTTED);
			return;
		}

		TradeList tradeList = player.getSellList();
		tradeList.clear();
		tradeList.setPackaged(_packageSale);

		long totalCost = player.getAdena();
		for (Item i : _items) {
			if (!i.addToTradeList(tradeList)) {
				Util.handleIllegalPlayerAction(player, "Warning!! Character " + player.getName() + " of account " + player.getAccountName() + " tried to set price more than " + ItemContainer.getMaximumAllowedCount(Inventory.ADENA_ID) + " adena in Private Store - Sell.", GeneralConfig.DEFAULT_PUNISH);
				return;
			}

			totalCost += i.getPrice();
			if (!ItemContainer.validateCount(Inventory.ADENA_ID, totalCost)) {
				Util.handleIllegalPlayerAction(player, "Warning!! Character " + player.getName() + " of account " + player.getAccountName() + " tried to set total price to " + totalCost + " adena in Private Store - Sell.", GeneralConfig.DEFAULT_PUNISH);
				return;
			}
		}

		player.sitDown();
		if (_packageSale) {
			player.setPrivateStoreType(PrivateStoreType.PACKAGE_SELL);
		} else {
			player.setPrivateStoreType(PrivateStoreType.SELL);
		}

		player.broadcastUserInfo();

		if (_packageSale) {
			player.broadcastPacket(new ExPrivateStoreSetWholeMsg(player));
		} else {
			player.broadcastPacket(new PrivateStoreMsgSell(player));
		}
	}

	private static class Item {
		private final int _objectId;
		private final long _count;
		private final long _price;

		public Item(int objectId, long count, long price) {
			_objectId = objectId;
			_count = count;
			_price = price;
		}

		public boolean addToTradeList(TradeList list) {
			if (!ItemContainer.validateCount(Inventory.ADENA_ID, _price * _count)) {
				return false;
			}

			list.addItem(_objectId, _count, _price);
			return true;
		}

		public long getPrice() {
			return _count * _price;
		}
	}
}
