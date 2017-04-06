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

import org.l2junity.gameserver.enums.AttributeType;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.items.Weapon;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ExBaseAttributeCancelResult;
import org.l2junity.gameserver.network.packets.s2c.InventoryUpdate;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.UserInfo;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;


public class RequestExRemoveItemAttribute extends GameClientPacket {
	private int _objectId;
	private long _price;
	private byte _element;

	public RequestExRemoveItemAttribute() {
	}

	@Override
	public void readImpl() {
		_objectId = readD();
		_element = (byte) readD();
	}

	@Override
	public void runImpl() {
		Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		final ItemInstance targetItem = activeChar.getInventory().getItemByObjectId(_objectId);
		if (targetItem == null) {
			return;
		}

		final AttributeType type = AttributeType.findByClientId(_element);
		if (type == null) {
			return;
		}

		if ((targetItem.getAttributes() == null) || (targetItem.getAttribute(type) == null)) {
			return;
		}

		if (activeChar.reduceAdena("RemoveElement", getPrice(targetItem), activeChar, true)) {
			targetItem.clearAttribute(type);
			getClient().sendPacket(new UserInfo(activeChar));

			final InventoryUpdate iu = new InventoryUpdate();
			iu.addModifiedItem(targetItem);
			activeChar.sendInventoryUpdate(iu);
			SystemMessage sm;
			AttributeType realElement = targetItem.isArmor() ? type.getOpposite() : type;
			if (targetItem.getEnchantLevel() > 0) {
				if (targetItem.isArmor()) {
					sm = SystemMessage.getSystemMessage(SystemMessageId.S1_S2_S_S3_ATTRIBUTE_WAS_REMOVED_SO_RESISTANCE_TO_S4_WAS_DECREASED);
				} else {
					sm = SystemMessage.getSystemMessage(SystemMessageId.S1_S2_S_S3_ATTRIBUTE_HAS_BEEN_REMOVED);
				}
				sm.addInt(targetItem.getEnchantLevel());
				sm.addItemName(targetItem);
				if (targetItem.isArmor()) {
					sm.addAttribute(realElement.getClientId());
					sm.addAttribute(realElement.getOpposite().getClientId());
				}
			} else {
				if (targetItem.isArmor()) {
					sm = SystemMessage.getSystemMessage(SystemMessageId.S1_S_S2_ATTRIBUTE_WAS_REMOVED_AND_RESISTANCE_TO_S3_WAS_DECREASED);
				} else {
					sm = SystemMessage.getSystemMessage(SystemMessageId.S1_S_S2_ATTRIBUTE_HAS_BEEN_REMOVED);
				}
				sm.addItemName(targetItem);
				if (targetItem.isArmor()) {
					sm.addAttribute(realElement.getClientId());
					sm.addAttribute(realElement.getOpposite().getClientId());
				}
			}
			getClient().sendPacket(sm);
			getClient().sendPacket(new ExBaseAttributeCancelResult(targetItem.getObjectId(), _element));
		} else {
			getClient().sendPacket(SystemMessageId.YOU_DO_NOT_HAVE_ENOUGH_FUNDS_TO_CANCEL_THIS_ATTRIBUTE);
		}
	}

	private long getPrice(ItemInstance item) {
		switch (item.getItem().getCrystalType()) {
			case S:
				if (item.getItem() instanceof Weapon) {
					_price = 50000;
				} else {
					_price = 40000;
				}
				break;
			case S80:
				if (item.getItem() instanceof Weapon) {
					_price = 100000;
				} else {
					_price = 80000;
				}
				break;
			case S84:
				if (item.getItem() instanceof Weapon) {
					_price = 200000;
				} else {
					_price = 160000;
				}
				break;
		}

		return _price;
	}
}
