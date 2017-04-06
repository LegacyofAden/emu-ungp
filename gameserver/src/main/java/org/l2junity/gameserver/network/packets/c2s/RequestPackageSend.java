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
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.holders.ItemHolder;
import org.l2junity.gameserver.model.itemcontainer.Inventory;
import org.l2junity.gameserver.model.itemcontainer.ItemContainer;
import org.l2junity.gameserver.model.itemcontainer.PcFreight;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.InventoryUpdate;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.gameserver.util.Util;
import org.l2junity.network.PacketReader;

/**
 * @author -Wooden-
 * @author UnAfraid Thanks mrTJO
 */
@Slf4j
public class RequestPackageSend extends GameClientPacket {
	private static final int BATCH_LENGTH = 12; // length of the one item

	private ItemHolder _items[] = null;
	private int _objectId;

	@Override
	public void readImpl() {
		_objectId = readD();

		int count = readD();
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
		final Player player = getClient().getActiveChar();
		if ((_items == null) || (player == null) || !player.getAccountChars().containsKey(_objectId)) {
			return;
		}

		if (!getClient().getFloodProtectors().getTransaction().tryPerformAction("deposit")) {
			player.sendMessage("You depositing items too fast.");
			return;
		}

		final Npc manager = player.getLastFolkNPC();
		if (((manager == null) || !player.isInRadius2d(manager, Npc.INTERACTION_DISTANCE))) {
			return;
		}

		if (player.hasItemRequest()) {
			Util.handleIllegalPlayerAction(player, "Player " + player.getName() + " tried to use enchant Exploit!", GeneralConfig.DEFAULT_PUNISH);
			return;
		}

		// get current tradelist if any
		if (player.getActiveTradeList() != null) {
			return;
		}

		// Alt game - Karma punishment
		if (!PlayerConfig.ALT_GAME_KARMA_PLAYER_CAN_USE_WAREHOUSE && (player.getReputation() < 0)) {
			return;
		}

		// Freight price from config per item slot.
		final int fee = _items.length * PlayerConfig.ALT_FREIGHT_PRICE;
		long currentAdena = player.getAdena();
		int slots = 0;

		final ItemContainer warehouse = new PcFreight(_objectId);
		for (ItemHolder i : _items) {
			// Check validity of requested item
			final ItemInstance item = player.checkItemManipulation(i.getId(), i.getCount(), "freight");
			if (item == null) {
				log.warn("Error depositing a warehouse object for char " + player.getName() + " (validity check)");
				warehouse.deleteMe();
				return;
			} else if (!item.isFreightable()) {
				warehouse.deleteMe();
				return;
			}

			// Calculate needed adena and slots
			if (item.getId() == Inventory.ADENA_ID) {
				currentAdena -= i.getCount();
			} else if (!item.isStackable()) {
				slots += i.getCount();
			} else if (warehouse.getItemByItemId(item.getId()) == null) {
				slots++;
			}
		}

		// Item Max Limit Check
		if (!warehouse.validateCapacity(slots)) {
			player.sendPacket(SystemMessageId.YOU_HAVE_EXCEEDED_THE_QUANTITY_THAT_CAN_BE_INPUTTED);
			warehouse.deleteMe();
			return;
		}

		// Check if enough adena and charge the fee
		if ((currentAdena < fee) || !player.reduceAdena(warehouse.getName(), fee, manager, false)) {
			player.sendPacket(SystemMessageId.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
			warehouse.deleteMe();
			return;
		}

		// Proceed to the transfer
		final InventoryUpdate playerIU = GeneralConfig.FORCE_INVENTORY_UPDATE ? null : new InventoryUpdate();
		for (ItemHolder i : _items) {
			// Check validity of requested item
			final ItemInstance oldItem = player.checkItemManipulation(i.getId(), i.getCount(), "deposit");
			if (oldItem == null) {
				log.warn("Error depositing a warehouse object for char " + player.getName() + " (olditem == null)");
				warehouse.deleteMe();
				return;
			}

			final ItemInstance newItem = player.getInventory().transferItem("Trade", i.getId(), i.getCount(), warehouse, player, null);
			if (newItem == null) {
				log.warn("Error depositing a warehouse object for char " + player.getName() + " (newitem == null)");
				continue;
			}

			if (playerIU != null) {
				if ((oldItem.getCount() > 0) && (oldItem != newItem)) {
					playerIU.addModifiedItem(oldItem);
				} else {
					playerIU.addRemovedItem(oldItem);
				}
			}
		}

		warehouse.deleteMe();

		// Send updated item list to the player
		if (playerIU != null) {
			player.sendInventoryUpdate(playerIU);
		} else {
			player.sendItemList(false);
		}
	}
}