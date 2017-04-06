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

import static org.l2junity.gameserver.model.itemcontainer.Inventory.ADENA_ID;

import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.gameserver.datatables.ItemTable;
import org.l2junity.gameserver.enums.ItemLocation;
import org.l2junity.gameserver.enums.PrivateStoreType;
import org.l2junity.gameserver.instancemanager.MailManager;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.entity.Message;
import org.l2junity.gameserver.model.itemcontainer.ItemContainer;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.model.zone.ZoneId;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ExChangePostState;
import org.l2junity.gameserver.network.packets.s2c.InventoryUpdate;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.gameserver.util.Util;
import org.l2junity.network.PacketReader;

/**
 * @author Migi, DS
 */
public final class RequestPostAttachment extends GameClientPacket {
	private int _msgId;

	@Override
	public void readImpl() {
		_msgId = readD();
	}

	@Override
	public void runImpl() {
		if (!GeneralConfig.ALLOW_MAIL || !GeneralConfig.ALLOW_ATTACHMENTS) {
			return;
		}

		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		if (!getClient().getFloodProtectors().getTransaction().tryPerformAction("getattach")) {
			return;
		}

		if (!activeChar.getAccessLevel().allowTransaction()) {
			activeChar.sendMessage("Transactions are disabled for your Access Level");
			return;
		}

		if (!activeChar.isInsideZone(ZoneId.PEACE)) {
			getClient().sendPacket(SystemMessageId.YOU_CANNOT_RECEIVE_IN_A_NON_PEACE_ZONE_LOCATION);
			return;
		}

		if (activeChar.getActiveTradeList() != null) {
			getClient().sendPacket(SystemMessageId.YOU_CANNOT_RECEIVE_DURING_AN_EXCHANGE);
			return;
		}

		if (activeChar.hasItemRequest()) {
			getClient().sendPacket(SystemMessageId.YOU_CANNOT_RECEIVE_MAIL_WHILE_ENCHANTING_AN_ITEM_BESTOWING_AN_ATTRIBUTE_OR_COMBINING_JEWELS);
			return;
		}

		if (activeChar.getPrivateStoreType() != PrivateStoreType.NONE) {
			getClient().sendPacket(SystemMessageId.YOU_CANNOT_RECEIVE_BECAUSE_THE_PRIVATE_STORE_OR_WORKSHOP_IS_IN_PROGRESS);
			return;
		}

		final Message msg = MailManager.getInstance().getMessage(_msgId);
		if (msg == null) {
			return;
		}

		if (msg.getReceiverId() != activeChar.getObjectId()) {
			Util.handleIllegalPlayerAction(activeChar, "Player " + activeChar.getName() + " tried to get not own attachment!", GeneralConfig.DEFAULT_PUNISH);
			return;
		}

		if (!msg.hasAttachments()) {
			return;
		}

		final ItemContainer attachments = msg.getAttachments();
		if (attachments == null) {
			return;
		}

		int weight = 0;
		int slots = 0;

		for (ItemInstance item : attachments.getItems()) {
			if (item == null) {
				continue;
			}

			// Calculate needed slots
			if (item.getOwnerId() != msg.getSenderId()) {
				Util.handleIllegalPlayerAction(activeChar, "Player " + activeChar.getName() + " tried to get wrong item (ownerId != senderId) from attachment!", GeneralConfig.DEFAULT_PUNISH);
				return;
			}

			if (item.getItemLocation() != ItemLocation.MAIL) {
				Util.handleIllegalPlayerAction(activeChar, "Player " + activeChar.getName() + " tried to get wrong item (Location != MAIL) from attachment!", GeneralConfig.DEFAULT_PUNISH);
				return;
			}

			if (item.getLocationSlot() != msg.getId()) {
				Util.handleIllegalPlayerAction(activeChar, "Player " + activeChar.getName() + " tried to get items from different attachment!", GeneralConfig.DEFAULT_PUNISH);
				return;
			}

			weight += item.getCount() * item.getItem().getWeight();
			if (!item.isStackable()) {
				slots += item.getCount();
			} else if (activeChar.getInventory().getItemByItemId(item.getId()) == null) {
				slots++;
			}
		}

		// Item Max Limit Check
		if (!activeChar.getInventory().validateCapacity(slots)) {
			getClient().sendPacket(SystemMessageId.YOU_COULD_NOT_RECEIVE_BECAUSE_YOUR_INVENTORY_IS_FULL);
			return;
		}

		// Weight limit Check
		if (!activeChar.getInventory().validateWeight(weight)) {
			getClient().sendPacket(SystemMessageId.YOU_COULD_NOT_RECEIVE_BECAUSE_YOUR_INVENTORY_IS_FULL);
			return;
		}

		long adena = msg.getReqAdena();
		if ((adena > 0) && !activeChar.reduceAdena("PayMail", adena, null, true)) {
			getClient().sendPacket(SystemMessageId.YOU_CANNOT_RECEIVE_BECAUSE_YOU_DON_T_HAVE_ENOUGH_ADENA);
			return;
		}

		// Proceed to the transfer
		InventoryUpdate playerIU = GeneralConfig.FORCE_INVENTORY_UPDATE ? null : new InventoryUpdate();
		for (ItemInstance item : attachments.getItems()) {
			if (item == null) {
				continue;
			}

			if (item.getOwnerId() != msg.getSenderId()) {
				Util.handleIllegalPlayerAction(activeChar, "Player " + activeChar.getName() + " tried to get items with owner != sender !", GeneralConfig.DEFAULT_PUNISH);
				return;
			}

			long count = item.getCount();
			final ItemInstance newItem = attachments.transferItem(attachments.getName(), item.getObjectId(), item.getCount(), activeChar.getInventory(), activeChar, null);
			if (newItem == null) {
				return;
			}

			if (playerIU != null) {
				if (newItem.getCount() > count) {
					playerIU.addModifiedItem(newItem);
				} else {
					playerIU.addNewItem(newItem);
				}
			}
			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_ACQUIRED_S2_S1);
			sm.addItemName(item.getId());
			sm.addLong(count);
			getClient().sendPacket(sm);
		}

		// Send updated item list to the player
		if (playerIU != null) {
			activeChar.sendInventoryUpdate(playerIU);
		} else {
			activeChar.sendItemList(false);
		}

		msg.removeAttachments();

		SystemMessage sm;
		final Player sender = WorldManager.getInstance().getPlayer(msg.getSenderId());
		if (adena > 0) {
			if (sender != null) {
				sender.addAdena("PayMail", adena, activeChar, false);
				sm = SystemMessage.getSystemMessage(SystemMessageId.S2_HAS_MADE_A_PAYMENT_OF_S1_ADENA_PER_YOUR_PAYMENT_REQUEST_MAIL);
				sm.addLong(adena);
				sm.addCharName(activeChar);
				sender.sendPacket(sm);
			} else {
				ItemInstance paidAdena = ItemTable.getInstance().createItem("PayMail", ADENA_ID, adena, activeChar, null);
				paidAdena.setOwnerId(msg.getSenderId());
				paidAdena.setItemLocation(ItemLocation.INVENTORY);
				paidAdena.updateDatabase(true);
			}
		} else if (sender != null) {
			sm = SystemMessage.getSystemMessage(SystemMessageId.S1_ACQUIRED_THE_ATTACHED_ITEM_TO_YOUR_MAIL);
			sm.addCharName(activeChar);
			sender.sendPacket(sm);
		}

		getClient().sendPacket(new ExChangePostState(true, _msgId, Message.READED));
		getClient().sendPacket(SystemMessageId.MAIL_SUCCESSFULLY_RECEIVED);
	}
}
