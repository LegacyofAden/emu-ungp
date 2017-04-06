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
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.MerchantInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.buylist.ProductList;
import org.l2junity.gameserver.model.items.ItemTemplate;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ActionFailed;
import org.l2junity.gameserver.network.packets.s2c.ExBuySellList;
import org.l2junity.gameserver.network.packets.s2c.ExUserInfoInvenWeight;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.gameserver.util.Util;
import org.l2junity.network.PacketReader;

import static org.l2junity.gameserver.model.actor.Npc.INTERACTION_DISTANCE;

/**
 * RequestRefundItem client packet class.
 */
@Slf4j
public final class RequestRefundItem extends GameClientPacket {
	private static final int BATCH_LENGTH = 4; // length of the one item

	private int _listId;
	private int[] _items = null;

	@Override
	public void readImpl() {
		_listId = readD();
		final int count = readD();
		if ((count <= 0) || (count > PlayerConfig.MAX_ITEM_IN_PACKET) || ((count * BATCH_LENGTH) != getAvailableBytes())) {
			return;
		}

		_items = new int[count];
		for (int i = 0; i < count; i++) {
			_items[i] = readD();
		}

	}

	@Override
	public void runImpl() {
		final Player player = getClient().getActiveChar();
		if (player == null) {
			return;
		}

		if (!getClient().getFloodProtectors().getTransaction().tryPerformAction("refund")) {
			player.sendMessage("You are using refund too fast.");
			return;
		}

		if ((_items == null) || !player.hasRefund()) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		WorldObject target = player.getTarget();
		if (!player.isGM() && ((target == null) || !(target instanceof MerchantInstance) || (player.getInstanceWorld() != target.getInstanceWorld()) || !player.isInRadius3d(target, INTERACTION_DISTANCE))) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		Creature merchant = null;
		if (target instanceof MerchantInstance) {
			merchant = (Creature) target;
		} else if (!player.isGM()) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if (merchant == null) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		final ProductList buyList = BuyListData.getInstance().getBuyList(_listId);
		if (buyList == null) {
			Util.handleIllegalPlayerAction(player, "Warning!! Character " + player.getName() + " of account " + player.getAccountName() + " sent a false BuyList list_id " + _listId, GeneralConfig.DEFAULT_PUNISH);
			return;
		}

		if (!buyList.isNpcAllowed(merchant.getId())) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		long weight = 0;
		long adena = 0;
		long slots = 0;

		ItemInstance[] refund = player.getRefund().getItems().toArray(new ItemInstance[0]);
		int[] objectIds = new int[_items.length];

		for (int i = 0; i < _items.length; i++) {
			int idx = _items[i];
			if ((idx < 0) || (idx >= refund.length)) {
				Util.handleIllegalPlayerAction(player, "Warning!! Character " + player.getName() + " of account " + player.getAccountName() + " sent invalid refund index", GeneralConfig.DEFAULT_PUNISH);
				return;
			}

			// check for duplicates - indexes
			for (int j = i + 1; j < _items.length; j++) {
				if (idx == _items[j]) {
					Util.handleIllegalPlayerAction(player, "Warning!! Character " + player.getName() + " of account " + player.getAccountName() + " sent duplicate refund index", GeneralConfig.DEFAULT_PUNISH);
					return;
				}
			}

			final ItemInstance item = refund[idx];
			final ItemTemplate template = item.getItem();
			objectIds[i] = item.getObjectId();

			// second check for duplicates - object ids
			for (int j = 0; j < i; j++) {
				if (objectIds[i] == objectIds[j]) {
					Util.handleIllegalPlayerAction(player, "Warning!! Character " + player.getName() + " of account " + player.getAccountName() + " has duplicate items in refund list", GeneralConfig.DEFAULT_PUNISH);
					return;
				}
			}

			long count = item.getCount();
			weight += count * template.getWeight();
			adena += (count * template.getReferencePrice()) / 2;
			if (!template.isStackable()) {
				slots += count;
			} else if (player.getInventory().getItemByItemId(template.getId()) == null) {
				slots++;
			}
		}

		if ((weight > Integer.MAX_VALUE) || (weight < 0) || !player.getInventory().validateWeight((int) weight)) {
			getClient().sendPacket(SystemMessageId.YOU_HAVE_EXCEEDED_THE_WEIGHT_LIMIT);
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if ((slots > Integer.MAX_VALUE) || (slots < 0) || !player.getInventory().validateCapacity((int) slots)) {
			getClient().sendPacket(SystemMessageId.YOUR_INVENTORY_IS_FULL);
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if ((adena < 0) || !player.reduceAdena("Refund", adena, player.getLastFolkNPC(), false)) {
			getClient().sendPacket(SystemMessageId.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		for (int i = 0; i < _items.length; i++) {
			ItemInstance item = player.getRefund().transferItem("Refund", objectIds[i], Long.MAX_VALUE, player.getInventory(), player, player.getLastFolkNPC());
			if (item == null) {
				log.warn("Error refunding object for char " + player.getName() + " (newitem == null)");
			}
		}

		// Update current load status on player
		getClient().sendPacket(new ExUserInfoInvenWeight(player));
		getClient().sendPacket(new ExBuySellList(player, true));
	}
}
