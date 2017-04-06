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
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.data.xml.impl.BuyListData;
import org.l2junity.gameserver.enums.TaxType;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.instance.MerchantInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.buylist.Product;
import org.l2junity.gameserver.model.buylist.ProductList;
import org.l2junity.gameserver.model.holders.ItemHolder;
import org.l2junity.gameserver.model.itemcontainer.Inventory;
import org.l2junity.gameserver.model.itemcontainer.ItemContainer;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ActionFailed;
import org.l2junity.gameserver.network.packets.s2c.ExBuySellList;
import org.l2junity.gameserver.network.packets.s2c.ExUserInfoInvenWeight;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.gameserver.util.Util;

import java.util.ArrayList;
import java.util.List;

import static org.l2junity.gameserver.model.actor.Npc.INTERACTION_DISTANCE;

@Slf4j
public final class RequestBuyItem extends GameClientPacket {
	private static final int BATCH_LENGTH = 12;
	private int _listId;
	private List<ItemHolder> _items = null;

	@Override
	public void readImpl() {
		_listId = readD();
		int size = readD();
		if ((size <= 0) || (size > PlayerConfig.MAX_ITEM_IN_PACKET) || ((size * BATCH_LENGTH) != getAvailableBytes())) {
			return;
		}

		_items = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			int itemId = readD();
			long count = readQ();
			if ((itemId < 1) || (count < 1)) {
				_items = null;
				return;
			}
			_items.add(new ItemHolder(itemId, count));
		}
	}

	@Override
	public void runImpl() {
		final Player player = getClient().getActiveChar();
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
			if (!(target instanceof MerchantInstance) || (!player.isInRadius3d(target, INTERACTION_DISTANCE)) || (player.getInstanceWorld() != target.getInstanceWorld())) {
				getClient().sendPacket(ActionFailed.STATIC_PACKET);
				return;
			}
			merchant = (MerchantInstance) target;
		}

		final ProductList buyList = BuyListData.getInstance().getBuyList(_listId);
		if (buyList == null) {
			Util.handleIllegalPlayerAction(player, "Warning!! Character " + player.getName() + " of account " + player.getAccountName() + " sent a false BuyList list_id " + _listId, GeneralConfig.DEFAULT_PUNISH);
			return;
		}

		double castleTaxRate = 0;
		if (merchant != null) {
			if (!buyList.isNpcAllowed(merchant.getId())) {
				getClient().sendPacket(ActionFailed.STATIC_PACKET);
				return;
			}
			castleTaxRate = merchant.getCastleTaxRate(TaxType.BUY);
		}

		long subTotal = 0;

		// Check for buylist validity and calculates summary values
		long slots = 0;
		long weight = 0;
		for (ItemHolder i : _items) {
			final Product product = buyList.getProductByItemId(i.getId());
			if (product == null) {
				Util.handleIllegalPlayerAction(player, "Warning!! Character " + player.getName() + " of account " + player.getAccountName() + " sent a false BuyList list_id " + _listId + " and item_id " + i.getId(), GeneralConfig.DEFAULT_PUNISH);
				return;
			}

			if (!product.getItem().isStackable() && (i.getCount() > 1)) {
				Util.handleIllegalPlayerAction(player, "Warning!! Character " + player.getName() + " of account " + player.getAccountName() + " tried to purchase invalid quantity of items at the same time.", GeneralConfig.DEFAULT_PUNISH);
				getClient().sendPacket(SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_EXCEEDED_THE_QUANTITY_THAT_CAN_BE_INPUTTED));
				return;
			}

			long price = product.getPrice();
			if (price < 0) {
				log.warn("ERROR, no price found .. wrong buylist ??");
				getClient().sendPacket(ActionFailed.STATIC_PACKET);
				return;
			}

			if ((price == 0) && !player.isGM() && GeneralConfig.ONLY_GM_ITEMS_FREE) {
				player.sendMessage("Ohh Cheat dont work? You have a problem now!");
				Util.handleIllegalPlayerAction(player, "Warning!! Character " + player.getName() + " of account " + player.getAccountName() + " tried buy item for 0 adena.", GeneralConfig.DEFAULT_PUNISH);
				return;
			}

			if (product.hasLimitedStock()) {
				// trying to buy more then available
				if (i.getCount() > product.getCount()) {
					getClient().sendPacket(ActionFailed.STATIC_PACKET);
					return;
				}
			}

			if (!ItemContainer.validateCount(Inventory.ADENA_ID, i.getCount() * price)) {
				Util.handleIllegalPlayerAction(player, "Warning!! Character " + player.getName() + " of account " + player.getAccountName() + " tried to purchase over " + ItemContainer.getMaximumAllowedCount(Inventory.ADENA_ID) + " adena worth of goods.", GeneralConfig.DEFAULT_PUNISH);
				return;
			}
			// first calculate price per item with tax, then multiply by count
			price = (long) (price * (1 + castleTaxRate + product.getBaseTaxRate()));
			subTotal += i.getCount() * price;
			if (!ItemContainer.validateCount(Inventory.ADENA_ID, subTotal)) {
				Util.handleIllegalPlayerAction(player, "Warning!! Character " + player.getName() + " of account " + player.getAccountName() + " tried to purchase " + subTotal + " adena worth of goods.", GeneralConfig.DEFAULT_PUNISH);
				return;
			}

			weight += i.getCount() * product.getItem().getWeight();
			if (player.getInventory().getItemByItemId(product.getItemId()) == null) {
				slots++;
			}
		}

		if (!player.isGM() && ((weight > Integer.MAX_VALUE) || (weight < 0) || !player.getInventory().validateWeight((int) weight))) {
			getClient().sendPacket(SystemMessageId.YOU_HAVE_EXCEEDED_THE_WEIGHT_LIMIT);
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if (!player.isGM() && ((slots > Integer.MAX_VALUE) || (slots < 0) || !player.getInventory().validateCapacity((int) slots))) {
			getClient().sendPacket(SystemMessageId.YOUR_INVENTORY_IS_FULL);
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		// Charge buyer and add tax to castle treasury if not owned by npc clan
		if ((subTotal < 0) || !player.reduceAdena("Buy", subTotal, player.getLastFolkNPC(), false)) {
			getClient().sendPacket(SystemMessageId.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		// Proceed the purchase
		for (ItemHolder i : _items) {
			Product product = buyList.getProductByItemId(i.getId());
			if (product == null) {
				Util.handleIllegalPlayerAction(player, "Warning!! Character " + player.getName() + " of account " + player.getAccountName() + " sent a false BuyList list_id " + _listId + " and item_id " + i.getId(), GeneralConfig.DEFAULT_PUNISH);
				continue;
			}

			if (product.hasLimitedStock()) {
				if (product.decreaseCount(i.getCount())) {
					player.getInventory().addItem("Buy", i.getId(), i.getCount(), player, merchant);
				}
			} else {
				player.getInventory().addItem("Buy", i.getId(), i.getCount(), player, merchant);
			}
		}

		// add to castle treasury
		if (merchant != null) {
			merchant.handleTaxPayment((long) (subTotal * castleTaxRate));
		}

		getClient().sendPacket(new ExUserInfoInvenWeight(player));
		getClient().sendPacket(new ExBuySellList(player, true));
	}
}
