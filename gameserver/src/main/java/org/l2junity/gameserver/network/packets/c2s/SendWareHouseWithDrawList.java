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
import org.l2junity.gameserver.model.ClanPrivilege;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.holders.ItemHolder;
import org.l2junity.gameserver.model.itemcontainer.ClanWarehouse;
import org.l2junity.gameserver.model.itemcontainer.ItemContainer;
import org.l2junity.gameserver.model.itemcontainer.PcWarehouse;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.InventoryUpdate;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.gameserver.util.Util;


@Slf4j
public final class SendWareHouseWithDrawList extends GameClientPacket {
	private static final int BATCH_LENGTH = 12; // length of the one item

	private ItemHolder _items[] = null;

	@Override
	public void readImpl() {
		final int count = readD();
		if ((count <= 0) || (count > PlayerConfig.MAX_ITEM_IN_PACKET) || ((count * BATCH_LENGTH) != getAvailableBytes())) {
			return;
		}

		_items = new ItemHolder[count];
		for (int i = 0; i < count; i++) {
			int objId = readD();
			long cnt = readQ();
			if ((objId < 1) || (cnt < 0)) {
				_items = null;
				return;
			}
			_items[i] = new ItemHolder(objId, cnt);
		}
	}

	@Override
	public void runImpl() {
		if (_items == null) {
			return;
		}

		final Player player = getClient().getActiveChar();
		if (player == null) {
			return;
		}

		if (!getClient().getFloodProtectors().getTransaction().tryPerformAction("withdraw")) {
			player.sendMessage("You are withdrawing items too fast.");
			return;
		}

		final ItemContainer warehouse = player.getActiveWarehouse();
		if (warehouse == null) {
			return;
		}

		final Npc manager = player.getLastFolkNPC();
		if (((manager == null) || !manager.isWarehouse() || !manager.canInteract(player)) && !player.isGM()) {
			return;
		}

		if (!(warehouse instanceof PcWarehouse) && !player.getAccessLevel().allowTransaction()) {
			player.sendMessage("Transactions are disabled for your Access Level.");
			return;
		}

		// Alt game - Karma punishment
		if (!PlayerConfig.ALT_GAME_KARMA_PLAYER_CAN_USE_WAREHOUSE && (player.getReputation() < 0)) {
			return;
		}

		if (PlayerConfig.ALT_MEMBERS_CAN_WITHDRAW_FROM_CLANWH) {
			if ((warehouse instanceof ClanWarehouse) && !player.hasClanPrivilege(ClanPrivilege.CL_VIEW_WAREHOUSE)) {
				return;
			}
		} else {
			if ((warehouse instanceof ClanWarehouse) && !player.isClanLeader()) {
				player.sendPacket(SystemMessageId.ITEMS_LEFT_AT_THE_CLAN_HALL_WAREHOUSE_CAN_ONLY_BE_RETRIEVED_BY_THE_CLAN_LEADER_DO_YOU_WANT_TO_CONTINUE);
				return;
			}
		}

		int weight = 0;
		int slots = 0;

		for (ItemHolder i : _items) {
			// Calculate needed slots
			ItemInstance item = warehouse.getItemByObjectId(i.getId());
			if ((item == null) || (item.getCount() < i.getCount())) {
				Util.handleIllegalPlayerAction(player, "Warning!! Character " + player.getName() + " of account " + player.getAccountName() + " tried to withdraw non-existent item from warehouse.", GeneralConfig.DEFAULT_PUNISH);
				return;
			}

			weight += i.getCount() * item.getItem().getWeight();
			if (!item.isStackable()) {
				slots += i.getCount();
			} else if (player.getInventory().getItemByItemId(item.getId()) == null) {
				slots++;
			}
		}

		// Item Max Limit Check
		if (!player.getInventory().validateCapacity(slots)) {
			player.sendPacket(SystemMessageId.YOUR_INVENTORY_IS_FULL);
			return;
		}

		// Weight limit Check
		if (!player.getInventory().validateWeight(weight)) {
			player.sendPacket(SystemMessageId.YOU_HAVE_EXCEEDED_THE_WEIGHT_LIMIT);
			return;
		}

		// Proceed to the transfer
		InventoryUpdate playerIU = GeneralConfig.FORCE_INVENTORY_UPDATE ? null : new InventoryUpdate();
		for (ItemHolder i : _items) {
			ItemInstance oldItem = warehouse.getItemByObjectId(i.getId());
			if ((oldItem == null) || (oldItem.getCount() < i.getCount())) {
				log.warn("Error withdrawing a warehouse object for char " + player.getName() + " (olditem == null)");
				return;
			}
			final ItemInstance newItem = warehouse.transferItem(warehouse.getName(), i.getId(), i.getCount(), player.getInventory(), player, manager);
			if (newItem == null) {
				log.warn("Error withdrawing a warehouse object for char " + player.getName() + " (newitem == null)");
				return;
			}

			if (playerIU != null) {
				if (newItem.getCount() > i.getCount()) {
					playerIU.addModifiedItem(newItem);
				} else {
					playerIU.addNewItem(newItem);
				}
			}
		}

		// Send updated item list to the player
		if (playerIU != null) {
			player.sendInventoryUpdate(playerIU);
		} else {
			player.sendItemList(false);
		}
	}
}