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
import org.l2junity.gameserver.data.xml.impl.EnsoulData;
import org.l2junity.gameserver.datatables.ItemTable;
import org.l2junity.gameserver.enums.AttributeType;
import org.l2junity.gameserver.enums.PrivateStoreType;
import org.l2junity.gameserver.model.TradeItem;
import org.l2junity.gameserver.model.TradeList;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.ceremonyofchaos.CeremonyOfChaosEvent;
import org.l2junity.gameserver.model.ensoul.EnsoulOption;
import org.l2junity.gameserver.model.itemcontainer.Inventory;
import org.l2junity.gameserver.model.itemcontainer.ItemContainer;
import org.l2junity.gameserver.model.items.ItemTemplate;
import org.l2junity.gameserver.model.zone.ZoneId;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ActionFailed;
import org.l2junity.gameserver.network.packets.s2c.PrivateStoreManageListBuy;
import org.l2junity.gameserver.network.packets.s2c.PrivateStoreMsgBuy;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.gameserver.taskmanager.AttackStanceTaskManager;
import org.l2junity.gameserver.util.Util;
import org.l2junity.network.PacketReader;

import java.util.Arrays;

public final class SetPrivateStoreListBuy extends GameClientPacket {
	private TradeItem[] _items = null;

	@Override
	public void readImpl() {
		int count = readD();
		if ((count < 1) || (count > PlayerConfig.MAX_ITEM_IN_PACKET)) {
			return;
		}

		_items = new TradeItem[count];
		for (int i = 0; i < count; i++) {
			int itemId = readD();

			final ItemTemplate template = ItemTable.getInstance().getTemplate(itemId);
			if (template == null) {
				_items = null;
				return;
			}

			final int enchantLevel = readH();
			readH(); // TODO analyse this

			long cnt = readQ();
			long price = readQ();

			if ((itemId < 1) || (cnt < 1) || (price < 0)) {
				_items = null;
				return;
			}

			final int option1 = readD();
			final int option2 = readD();
			final short attackAttributeId = (short) readH();
			final int attackAttributeValue = readH();
			final int defenceFire = readH();
			final int defenceWater = readH();
			final int defenceWind = readH();
			final int defenceEarth = readH();
			final int defenceHoly = readH();
			final int defenceDark = readH();
			final int visualId = readD();

			final EnsoulOption[] soulCrystalOptions = new EnsoulOption[readC()];
			for (int k = 0; k < soulCrystalOptions.length; k++) {
				soulCrystalOptions[k] = EnsoulData.getInstance().getOption(readD());
			}
			final EnsoulOption[] soulCrystalSpecialOptions = new EnsoulOption[readC()];
			for (int k = 0; k < soulCrystalSpecialOptions.length; k++) {
				soulCrystalSpecialOptions[k] = EnsoulData.getInstance().getOption(readD());
			}

			final TradeItem item = new TradeItem(template, cnt, price);
			item.setEnchant(enchantLevel);
			item.setAugmentation(option1, option2);
			item.setAttackElementType(AttributeType.findByClientId(attackAttributeId));
			item.setAttackElementPower(attackAttributeValue);
			item.setElementDefAttr(AttributeType.FIRE, defenceFire);
			item.setElementDefAttr(AttributeType.WATER, defenceWater);
			item.setElementDefAttr(AttributeType.WIND, defenceWind);
			item.setElementDefAttr(AttributeType.EARTH, defenceEarth);
			item.setElementDefAttr(AttributeType.HOLY, defenceHoly);
			item.setElementDefAttr(AttributeType.DARK, defenceDark);
			item.setVisualId(visualId);
			item.setSoulCrystalOptions(Arrays.asList(soulCrystalOptions));
			item.setSoulCrystalSpecialOptions(Arrays.asList(soulCrystalSpecialOptions));
			_items[i] = item;
		}

	}

	@Override
	public void runImpl() {
		Player player = getClient().getActiveChar();
		if (player == null) {
			return;
		}

		if (_items == null) {
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
			player.sendPacket(new PrivateStoreManageListBuy(player));
			player.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if (player.isInsideZone(ZoneId.NO_STORE)) {
			player.sendPacket(new PrivateStoreManageListBuy(player));
			player.sendPacket(SystemMessageId.YOU_CANNOT_OPEN_A_PRIVATE_STORE_HERE);
			player.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		// Cannot set private store in Ceremony of Chaos event.
		if (player.isOnEvent(CeremonyOfChaosEvent.class)) {
			player.sendPacket(SystemMessageId.YOU_CANNOT_OPEN_A_PRIVATE_STORE_OR_WORKSHOP_IN_THE_CEREMONY_OF_CHAOS);
			return;
		}

		TradeList tradeList = player.getBuyList();
		tradeList.clear();

		// Check maximum number of allowed slots for pvt shops
		if (_items.length > player.getPrivateBuyStoreLimit()) {
			player.sendPacket(new PrivateStoreManageListBuy(player));
			player.sendPacket(SystemMessageId.YOU_HAVE_EXCEEDED_THE_QUANTITY_THAT_CAN_BE_INPUTTED);
			return;
		}

		long totalCost = 0;
		for (TradeItem i : _items) {
			if (!ItemContainer.validateCount(Inventory.ADENA_ID, i.getCount() * i.getPrice())) {
				Util.handleIllegalPlayerAction(player, "Warning!! Character " + player.getName() + " of account " + player.getAccountName() + " tried to set price of " + (i.getCount() * i.getPrice()) + " adena in Private Store - Buy.", GeneralConfig.DEFAULT_PUNISH);
				return;
			}

			tradeList.addItemByItemId(i.getItem().getId(), i.getCount(), i.getPrice());

			totalCost += (i.getCount() * i.getPrice());
			if (!ItemContainer.validateCount(Inventory.ADENA_ID, totalCost)) {
				Util.handleIllegalPlayerAction(player, "Warning!! Character " + player.getName() + " of account " + player.getAccountName() + " tried to set total price to " + totalCost + " adena in Private Store - Buy.", GeneralConfig.DEFAULT_PUNISH);
				return;
			}
		}

		// Check for available funds
		if (totalCost > player.getAdena()) {
			player.sendPacket(new PrivateStoreManageListBuy(player));
			player.sendPacket(SystemMessageId.THE_PURCHASE_PRICE_IS_HIGHER_THAN_THE_AMOUNT_OF_MONEY_THAT_YOU_HAVE_AND_SO_YOU_CANNOT_OPEN_A_PERSONAL_STORE);
			return;
		}

		player.sitDown();
		player.setPrivateStoreType(PrivateStoreType.BUY);
		player.broadcastUserInfo();
		player.broadcastPacket(new PrivateStoreMsgBuy(player));
	}
}
