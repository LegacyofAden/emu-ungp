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
import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.data.xml.impl.BuyListData;
import org.l2junity.gameserver.enums.Race;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.MerchantInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.buylist.Product;
import org.l2junity.gameserver.model.buylist.ProductList;
import org.l2junity.gameserver.model.itemcontainer.Inventory;
import org.l2junity.gameserver.model.itemcontainer.ItemContainer;
import org.l2junity.gameserver.model.items.Armor;
import org.l2junity.gameserver.model.items.ItemTemplate;
import org.l2junity.gameserver.model.items.Weapon;
import org.l2junity.gameserver.model.items.type.ArmorType;
import org.l2junity.gameserver.model.items.type.WeaponType;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ActionFailed;
import org.l2junity.gameserver.network.packets.s2c.ExUserInfoEquipSlot;
import org.l2junity.gameserver.network.packets.s2c.ShopPreviewInfo;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.gameserver.util.Util;
import org.l2junity.network.PacketReader;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * * @author Gnacik
 */
@Slf4j
public final class RequestPreviewItem extends GameClientPacket {
	@SuppressWarnings("unused")
	private int _unk;
	private int _listId;
	private int _count;
	private int[] _items;

	private class RemoveWearItemsTask implements Runnable {
		private final Player activeChar;

		protected RemoveWearItemsTask(Player player) {
			activeChar = player;
		}

		@Override
		public void run() {
			try {
				activeChar.sendPacket(SystemMessageId.YOU_ARE_NO_LONGER_TRYING_ON_EQUIPMENT2);
				activeChar.sendPacket(new ExUserInfoEquipSlot(activeChar));
			} catch (Exception e) {
				log.error("", e);
			}
		}
	}

	@Override
	public void readImpl() {
		_unk = readD();
		_listId = readD();
		_count = readD();

		if (_count < 0) {
			_count = 0;
		}
		if (_count > 100) {
			return; // prevent too long lists
		}

		// Create _items table that will contain all ItemID to Wear
		_items = new int[_count];

		// Fill _items table with all ItemID to Wear
		for (int i = 0; i < _count; i++) {
			_items[i] = readD();
		}

	}

	@Override
	public void runImpl() {
		if (_items == null) {
			return;
		}

		// Get the current player and return if null
		Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		if (!getClient().getFloodProtectors().getTransaction().tryPerformAction("buy")) {
			activeChar.sendMessage("You are buying too fast.");
			return;
		}

		// If Alternate rule Karma punishment is set to true, forbid Wear to player with Karma
		if (!PlayerConfig.ALT_GAME_KARMA_PLAYER_CAN_SHOP && (activeChar.getReputation() < 0)) {
			return;
		}

		// Check current target of the player and the INTERACTION_DISTANCE
		WorldObject target = activeChar.getTarget();
		if (!activeChar.isGM() && ((target == null // No target (i.e. GM Shop)
		) || !((target instanceof MerchantInstance)) // Target not a merchant
				|| !activeChar.isInRadius2d(target, Npc.INTERACTION_DISTANCE) // Distance is too far
		)) {
			return;
		}

		if ((_count < 1) || (_listId >= 4000000)) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		// Get the current merchant targeted by the player
		final MerchantInstance merchant = (target instanceof MerchantInstance) ? (MerchantInstance) target : null;
		if (merchant == null) {
			log.warn("Null merchant!");
			return;
		}

		final ProductList buyList = BuyListData.getInstance().getBuyList(_listId);
		if (buyList == null) {
			Util.handleIllegalPlayerAction(activeChar, "Warning!! Character " + activeChar.getName() + " of account " + activeChar.getAccountName() + " sent a false BuyList list_id " + _listId, GeneralConfig.DEFAULT_PUNISH);
			return;
		}

		long totalPrice = 0;
		Map<Integer, Integer> itemList = new HashMap<>();

		for (int i = 0; i < _count; i++) {
			int itemId = _items[i];

			final Product product = buyList.getProductByItemId(itemId);
			if (product == null) {
				Util.handleIllegalPlayerAction(activeChar, "Warning!! Character " + activeChar.getName() + " of account " + activeChar.getAccountName() + " sent a false BuyList list_id " + _listId + " and item_id " + itemId, GeneralConfig.DEFAULT_PUNISH);
				return;
			}

			ItemTemplate template = product.getItem();
			if (template == null) {
				continue;
			}

			int slot = Inventory.getPaperdollIndex(template.getBodyPart());
			if (slot < 0) {
				continue;
			}

			if (template instanceof Weapon) {
				if (activeChar.getRace() == Race.KAMAEL) {
					if (template.getItemType() == WeaponType.NONE) {
						continue;
					} else if ((template.getItemType() == WeaponType.RAPIER) || (template.getItemType() == WeaponType.CROSSBOW) || (template.getItemType() == WeaponType.ANCIENTSWORD)) {
						continue;
					}
				}
			} else if (template instanceof Armor) {
				if (activeChar.getRace() == Race.KAMAEL) {
					if ((template.getItemType() == ArmorType.HEAVY) || (template.getItemType() == ArmorType.MAGIC)) {
						continue;
					}
				}
			}

			if (itemList.containsKey(slot)) {
				activeChar.sendPacket(SystemMessageId.YOU_CAN_NOT_TRY_THOSE_ITEMS_ON_AT_THE_SAME_TIME);
				return;
			}

			itemList.put(slot, itemId);
			totalPrice += GeneralConfig.WEAR_PRICE;
			if (!ItemContainer.validateCount(Inventory.ADENA_ID, totalPrice)) {
				Util.handleIllegalPlayerAction(activeChar, "Warning!! Character " + activeChar.getName() + " of account " + activeChar.getAccountName() + " tried to purchase " + totalPrice + " adena worth of goods.", GeneralConfig.DEFAULT_PUNISH);
				return;
			}
		}

		// Charge buyer and add tax to castle treasury if not owned by npc clan because a Try On is not Free
		if ((totalPrice < 0) || !activeChar.reduceAdena("Wear", totalPrice, activeChar.getLastFolkNPC(), true)) {
			activeChar.sendPacket(SystemMessageId.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
			return;
		}

		if (!itemList.isEmpty()) {
			activeChar.sendPacket(new ShopPreviewInfo(itemList));
			// Schedule task
			ThreadPool.getInstance().scheduleGeneral(new RemoveWearItemsTask(activeChar), GeneralConfig.WEAR_DELAY * 1000, TimeUnit.MILLISECONDS);
		}
	}

}
