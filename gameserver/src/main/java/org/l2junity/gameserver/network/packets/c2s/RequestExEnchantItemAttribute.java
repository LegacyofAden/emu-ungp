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

import org.l2junity.commons.util.Rnd;
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.enums.AttributeType;
import org.l2junity.gameserver.enums.PrivateStoreType;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.actor.request.EnchantItemAttributeRequest;
import org.l2junity.gameserver.model.items.enchant.attribute.AttributeHolder;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ExAttributeEnchantResult;
import org.l2junity.gameserver.network.packets.s2c.InventoryUpdate;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.UserInfo;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.gameserver.util.Util;
import org.l2junity.network.PacketReader;

public class RequestExEnchantItemAttribute extends GameClientPacket {
	public static final int FIRST_WEAPON_BONUS = 20;
	public static final int NEXT_WEAPON_BONUS = 5;
	public static final int ARMOR_BONUS = 6;

	private int _objectId;
	private long _count;

	@Override
	public void readImpl() {
		_objectId = readD();
		_count = readQ();
	}

	@Override
	public void runImpl() {
		final Player player = getClient().getActiveChar();
		if (player == null) {
			return;
		}

		final EnchantItemAttributeRequest request = player.getRequest(EnchantItemAttributeRequest.class);
		if (request == null) {
			return;
		}

		request.setProcessing(true);

		if (_objectId == 0xFFFFFFFF) {
			// Player canceled enchant
			player.removeRequest(request.getClass());
			getClient().sendPacket(SystemMessageId.ATTRIBUTE_ITEM_USAGE_HAS_BEEN_CANCELLED);
			return;
		}

		if (!player.isOnline()) {
			player.removeRequest(request.getClass());
			return;
		}

		if (player.getPrivateStoreType() != PrivateStoreType.NONE) {
			getClient().sendPacket(SystemMessageId.YOU_CANNOT_ADD_ELEMENTAL_POWER_WHILE_OPERATING_A_PRIVATE_STORE_OR_PRIVATE_WORKSHOP);
			player.removeRequest(request.getClass());
			return;
		}

		// Restrict enchant during a trade (bug if enchant fails)
		if (player.getActiveRequester() != null) {
			// Cancel trade
			player.cancelActiveTrade();
			player.removeRequest(request.getClass());
			getClient().sendPacket(SystemMessageId.YOU_CANNOT_DO_THAT_WHILE_TRADING);
			return;
		}

		final ItemInstance item = player.getInventory().getItemByObjectId(_objectId);
		final ItemInstance stone = request.getEnchantingStone();
		if ((item == null) || (stone == null)) {
			player.removeRequest(request.getClass());
			getClient().sendPacket(SystemMessageId.ATTRIBUTE_ITEM_USAGE_HAS_BEEN_CANCELLED);
			return;
		}

		if (!item.isElementable()) {
			getClient().sendPacket(SystemMessageId.ELEMENTAL_POWER_ENHANCER_USAGE_REQUIREMENT_IS_NOT_SUFFICIENT);
			player.removeRequest(request.getClass());
			return;
		}

		switch (item.getItemLocation()) {
			case INVENTORY:
			case PAPERDOLL: {
				if (item.getOwnerId() != player.getObjectId()) {
					player.removeRequest(request.getClass());
					return;
				}
				break;
			}
			default: {
				player.removeRequest(request.getClass());
				Util.handleIllegalPlayerAction(player, "Player " + player.getName() + " tried to use enchant Exploit!", GeneralConfig.DEFAULT_PUNISH);
				return;
			}
		}

		final long count = Math.min(stone.getCount(), _count);
		final AttributeType elementToAdd = item.isWeapon() ? request.getWeaponAttribute() : request.getArmorAttribute();
		final AttributeType opositeElement = item.isWeapon() ? request.getArmorAttribute() : request.getWeaponAttribute();

		final AttributeHolder oldElement = item.getAttribute(elementToAdd);
		final int elementValue = oldElement == null ? 0 : oldElement.getValue();
		final int limit = request.getLimit(item.isWeapon());
		int powerToAdd = 0;
		if (request.getMinValue() > 0) {
			powerToAdd = Rnd.nextBoolean() ? request.getMaxValue() : request.getMinValue();
		} else if (item.isWeapon()) {
			powerToAdd = elementValue == 0 ? FIRST_WEAPON_BONUS : NEXT_WEAPON_BONUS;
		} else if (item.isArmor()) {
			powerToAdd = ARMOR_BONUS;
		}

		if ((item.isWeapon() && (oldElement != null) && (oldElement.getType() != elementToAdd) && (oldElement.getType() != AttributeType.NONE)) || (item.isArmor() && (item.getAttribute(elementToAdd) == null) && (item.getAttributes() != null) && (item.getAttributes().size() >= 3))) {
			getClient().sendPacket(SystemMessageId.ANOTHER_ELEMENTAL_POWER_HAS_ALREADY_BEEN_ADDED_THIS_ELEMENTAL_POWER_CANNOT_BE_ADDED);
			player.removeRequest(request.getClass());
			return;
		}

		if (item.isArmor() && (item.getAttributes() != null)) {
			// can't add opposite element
			for (AttributeHolder attribute : item.getAttributes()) {
				if (attribute.getType() == opositeElement) {
					player.removeRequest(request.getClass());
					Util.handleIllegalPlayerAction(player, "Player " + player.getName() + " tried to add oposite attribute to item!", GeneralConfig.DEFAULT_PUNISH);
					return;
				}
			}
		}

		int newPower = elementValue + powerToAdd;
		if (newPower > limit) {
			newPower = limit;
			powerToAdd = limit - elementValue;
		}

		if (powerToAdd <= 0) {
			getClient().sendPacket(SystemMessageId.ATTRIBUTE_ITEM_USAGE_HAS_BEEN_CANCELLED);
			player.removeRequest(request.getClass());
			return;
		}

		int usedStones = 0;
		int successfulAttempts = 0;
		int failedAttempts = 0;
		for (int i = 0; i < count; i++) {
			usedStones++;
			final int result = addElement(player, stone, item, elementToAdd, limit, powerToAdd, request.getMaxLevel());
			if (result == 1) {
				successfulAttempts++;
			} else if (result == 0) {
				failedAttempts++;
			} else {
				break;
			}
		}

		item.updateItemElementals();
		player.destroyItem("AttrEnchant", stone, usedStones, player, true);
		final AttributeHolder newElement = item.getAttribute(elementToAdd);
		final int newValue = newElement != null ? newElement.getValue() : 0;
		final AttributeType realElement = item.isArmor() ? opositeElement : elementToAdd;
		final InventoryUpdate iu = new InventoryUpdate();

		if (successfulAttempts > 0) {
			SystemMessage sm;
			if (item.getEnchantLevel() == 0) {
				if (item.isArmor()) {
					sm = SystemMessage.getSystemMessage(SystemMessageId.THE_S2_S_ATTRIBUTE_WAS_SUCCESSFULLY_BESTOWED_ON_S1_AND_RESISTANCE_TO_S3_WAS_INCREASED);
				} else {
					sm = SystemMessage.getSystemMessage(SystemMessageId.S2_ELEMENTAL_POWER_HAS_BEEN_ADDED_SUCCESSFULLY_TO_S1);
				}
				sm.addItemName(item);
				sm.addAttribute(realElement.getClientId());
				if (item.isArmor()) {
					sm.addAttribute(realElement.getOpposite().getClientId());
				}
			} else {
				if (item.isArmor()) {
					sm = SystemMessage.getSystemMessage(SystemMessageId.THE_S3_S_ATTRIBUTE_WAS_SUCCESSFULLY_BESTOWED_ON_S1_S2_AND_RESISTANCE_TO_S4_WAS_INCREASED);
				} else {
					sm = SystemMessage.getSystemMessage(SystemMessageId.S3_ELEMENTAL_POWER_HAS_BEEN_ADDED_SUCCESSFULLY_TO_S1_S2);
				}
				sm.addInt(item.getEnchantLevel());
				sm.addItemName(item);
				sm.addAttribute(realElement.getClientId());
				if (item.isArmor()) {
					sm.addAttribute(realElement.getOpposite().getClientId());
				}
			}
			player.sendPacket(sm);

			// send packets
			iu.addModifiedItem(item);
		} else {
			getClient().sendPacket(SystemMessageId.YOU_HAVE_FAILED_TO_ADD_ELEMENTAL_POWER);
		}

		int result = 0;
		if (successfulAttempts == 0) {
			// Failed
			result = 2;
		}

		// Stone must be removed
		if (stone.getCount() == 0) {
			iu.addRemovedItem(stone);
		} else {
			iu.addModifiedItem(stone);
		}

		player.removeRequest(request.getClass());
		getClient().sendPacket(new ExAttributeEnchantResult(result, item.isWeapon(), elementToAdd, elementValue, newValue, successfulAttempts, failedAttempts));
		getClient().sendPacket(new UserInfo(player));
		player.sendInventoryUpdate(iu);
	}

	private int addElement(final Player player, final ItemInstance stone, final ItemInstance item, AttributeType elementToAdd, int limit, int powerToAdd, int maxLevel) {
		final AttributeHolder oldElement = item.getAttribute(elementToAdd);
		final int elementValue = oldElement == null ? 0 : oldElement.getValue();

		int newPower = elementValue + powerToAdd;
		if (newPower > limit) {
			newPower = limit;
			powerToAdd = limit - elementValue;
		}

		if (powerToAdd <= 0) {
			player.sendPacket(SystemMessageId.ATTRIBUTE_ITEM_USAGE_HAS_BEEN_CANCELLED);
			player.removeRequest(EnchantItemAttributeRequest.class);
			return -1;
		}

		boolean success = false;
		switch (stone.getItem().getCrystalType()) {
			case R: {
				success = Rnd.get(100) < 80;
				break;
			}
			case R95:
			case R99: {
				success = true;
				break;
			}
			default: {
				switch (maxLevel) {
					case 6: {
						success = Rnd.get(100) < PlayerConfig.ENCHANT_CHANCE_ELEMENT_CRYSTAL;
						break;
					}
					case 9: {
						success = Rnd.get(100) < PlayerConfig.ENCHANT_CHANCE_ELEMENT_JEWEL;
						break;
					}
					case 12: {
						success = Rnd.get(100) < PlayerConfig.ENCHANT_CHANCE_ELEMENT_ENERGY;
						break;
					}
					default: {
						success = Rnd.get(100) < PlayerConfig.ENCHANT_CHANCE_ELEMENT_STONE;
						break;
					}
				}
			}
		}

		if (success) {
			item.setAttribute(new AttributeHolder(elementToAdd, newPower), false);
		}

		return success ? 1 : 0;
	}
}
