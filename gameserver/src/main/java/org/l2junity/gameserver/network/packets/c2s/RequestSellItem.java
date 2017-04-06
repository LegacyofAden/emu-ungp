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

import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.data.xml.impl.BuyListData;
import org.l2junity.gameserver.enums.TaxType;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.instance.MerchantInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.buylist.ProductList;
import org.l2junity.gameserver.model.holders.UniqueItemHolder;
import org.l2junity.gameserver.model.itemcontainer.Inventory;
import org.l2junity.gameserver.model.itemcontainer.ItemContainer;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ActionFailed;
import org.l2junity.gameserver.network.packets.s2c.ExBuySellList;
import org.l2junity.gameserver.network.packets.s2c.ExUserInfoInvenWeight;
import org.l2junity.gameserver.util.Util;
import org.l2junity.network.PacketReader;

import java.util.ArrayList;
import java.util.List;

import static org.l2junity.gameserver.model.actor.Npc.INTERACTION_DISTANCE;

/**
 * RequestSellItem client packet class.
 */
public final class RequestSellItem extends GameClientPacket {
	private static final int BATCH_LENGTH = 16;

	private int _listId;
	private List<UniqueItemHolder> _items = null;

	@Override
	public void readImpl() {
		_listId = readD();
		int size = readD();
		if ((size <= 0) || (size > PlayerConfig.MAX_ITEM_IN_PACKET) || ((size * BATCH_LENGTH) != getAvailableBytes())) {
			return;
		}

		_items = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			int objectId = readD();
			int itemId = readD();
			long count = readQ();
			if ((objectId < 1) || (itemId < 1) || (count < 1)) {
				_items = null;
				return;
			}
			_items.add(new UniqueItemHolder(itemId, objectId, count));
		}
	}

	@Override
	public void runImpl() {
		Player player = getClient().getActiveChar();
		if (player == null) {
			return;
		}

		if (!getClient().getFloodProtectors().getTransaction().tryPerformAction("buy")) {
			player.sendMessage("You are buying too fast.");
			return;
		}

		if (_items == null) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		// Alt game - Karma punishment
		if (!PlayerConfig.ALT_GAME_KARMA_PLAYER_CAN_SHOP && (player.getReputation() < 0)) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		WorldObject target = player.getTarget();
		MerchantInstance merchant = null;
		if (!player.isGM()) {
			if ((target == null) || (!player.isInRadius3d(target, INTERACTION_DISTANCE)) || (player.getInstanceWorld() != target.getInstanceWorld())) {
				getClient().sendPacket(ActionFailed.STATIC_PACKET);
				return;
			}
			if (target instanceof MerchantInstance) {
				merchant = (MerchantInstance) target;
			} else {
				getClient().sendPacket(ActionFailed.STATIC_PACKET);
				return;
			}
		}

		final ProductList buyList = BuyListData.getInstance().getBuyList(_listId);
		if (buyList == null) {
			Util.handleIllegalPlayerAction(player, "Warning!! Character " + player.getName() + " of account " + player.getAccountName() + " sent a false BuyList list_id " + _listId, GeneralConfig.DEFAULT_PUNISH);
			return;
		}

		if ((merchant != null) && !buyList.isNpcAllowed(merchant.getId())) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		long totalPrice = 0;
		// Proceed the sell
		for (UniqueItemHolder i : _items) {
			ItemInstance item = player.checkItemManipulation(i.getObjectId(), i.getCount(), "sell");
			if ((item == null) || (!item.isSellable())) {
				continue;
			}

			long price = item.getReferencePrice() / 2;
			totalPrice += price * i.getCount();
			if (!ItemContainer.validateCount(Inventory.ADENA_ID, totalPrice)) {
				Util.handleIllegalPlayerAction(player, "Warning!! Character " + player.getName() + " of account " + player.getAccountName() + " tried to purchase " + totalPrice + " adena worth of goods.", GeneralConfig.DEFAULT_PUNISH);
				return;
			}

			if (GeneralConfig.ALLOW_REFUND) {
				player.getInventory().transferItem("Sell", i.getObjectId(), i.getCount(), player.getRefund(), player, merchant);
			} else {
				player.getInventory().destroyItem("Sell", i.getObjectId(), i.getCount(), player, merchant);
			}
		}

		// add to castle treasury
		if (merchant != null) {
			// Keep here same formula as in {@link ExBuySellList} to produce same result.
			final long profit = (long) (totalPrice * (1.0 - merchant.getCastleTaxRate(TaxType.SELL)));
			merchant.handleTaxPayment(totalPrice - profit);
			totalPrice = profit;
		}

		player.addAdena("Sell", totalPrice, merchant, false);

		// Update current load as well
		getClient().sendPacket(new ExUserInfoInvenWeight(player));
		getClient().sendPacket(new ExBuySellList(player, true));
	}
}
