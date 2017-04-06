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
package org.l2junity.gameserver.network.packets.s2c;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.enums.AttributeType;
import org.l2junity.gameserver.enums.ItemListType;
import org.l2junity.gameserver.model.ItemInfo;
import org.l2junity.gameserver.model.TradeItem;
import org.l2junity.gameserver.model.buylist.Product;
import org.l2junity.gameserver.model.ensoul.EnsoulOption;
import org.l2junity.gameserver.model.itemcontainer.PcInventory;
import org.l2junity.gameserver.model.items.WarehouseItem;
import org.l2junity.gameserver.model.items.instance.ItemInstance;

/**
 * @author UnAfraid
 */
public abstract class AbstractItemPacket extends AbstractMaskPacket<ItemListType> {
	private static final byte[] MASKS =
			{
					0x00
			};

	@Override
	protected byte[] getMasks() {
		return MASKS;
	}

	protected void writeItem(PacketBody body, TradeItem item) {
		writeItem(body, new ItemInfo(item));
	}

	protected void writeItem(PacketBody body, WarehouseItem item) {
		writeItem(body, new ItemInfo(item));
	}

	protected void writeItem(PacketBody body, ItemInstance item) {
		writeItem(body, new ItemInfo(item));
	}

	protected void writeItem(PacketBody body, Product item) {
		writeItem(body, new ItemInfo(item));
	}

	protected void writeItem(PacketBody body, ItemInfo item) {
		final int mask = calculateMask(item);
		// cddcQcchQccddc
		body.writeC(mask);
		body.writeD(item.getObjectId()); // ObjectId
		body.writeD(item.getItem().getDisplayId()); // ItemId
		body.writeC(item.getItem().isQuestItem() || (item.getEquipped() == 1) ? 0xFF : item.getLocation()); // T1
		body.writeQ(item.getCount()); // Quantity
		body.writeC(item.getItem().getType2()); // Item Type 2 : 00-weapon, 01-shield/armor, 02-ring/earring/necklace, 03-questitem, 04-adena, 05-item
		body.writeC(item.getCustomType1()); // Filler (always 0)
		body.writeH(item.getEquipped()); // Equipped : 00-No, 01-yes
		body.writeQ(item.getItem().getBodyPart()); // Slot : 0006-lr.ear, 0008-neck, 0030-lr.finger, 0040-head, 0100-l.hand, 0200-gloves, 0400-chest, 0800-pants, 1000-feet, 4000-r.hand, 8000-r.hand
		body.writeC(item.getEnchantLevel()); // Enchant level (pet level shown in control item)
		body.writeC(0x01); // TODO : Find me
		body.writeD(item.getMana());
		body.writeD(item.getTime());
		body.writeC(0x01); // GOD Item enabled = 1 disabled (red) = 0
		if (containsMask(mask, ItemListType.AUGMENT_BONUS)) {
			writeItemAugment(body, item);
		}
		if (containsMask(mask, ItemListType.ELEMENTAL_ATTRIBUTE)) {
			writeItemElemental(body, item);
		}
		if (containsMask(mask, ItemListType.ENCHANT_EFFECT)) {
			writeItemEnchantEffect(body, item);
		}
		if (containsMask(mask, ItemListType.VISUAL_ID)) {
			body.writeD(item.getVisualId()); // Item remodel visual ID
		}
		if (containsMask(mask, ItemListType.SOUL_CRYSTAL)) {
			writeItemEnsoulOptions(body, item);
		}
	}

	protected static int calculateMask(ItemInfo item) {
		int mask = 0;
		if (item.getAugmentation() != null) {
			mask |= ItemListType.AUGMENT_BONUS.getMask();
		}

		if ((item.getAttackElementType() >= 0) || (item.getAttributeDefence(AttributeType.FIRE) > 0) || (item.getAttributeDefence(AttributeType.WATER) > 0) || (item.getAttributeDefence(AttributeType.WIND) > 0) || (item.getAttributeDefence(AttributeType.EARTH) > 0) || (item.getAttributeDefence(AttributeType.HOLY) > 0) || (item.getAttributeDefence(AttributeType.DARK) > 0)) {
			mask |= ItemListType.ELEMENTAL_ATTRIBUTE.getMask();
		}

		if (item.getEnchantOptions() != null) {
			for (int id : item.getEnchantOptions()) {
				if (id > 0) {
					mask |= ItemListType.ENCHANT_EFFECT.getMask();
					break;
				}
			}
		}

		if (item.getVisualId() > 0) {
			mask |= ItemListType.VISUAL_ID.getMask();
		}

		if (((item.getSoulCrystalOptions() != null) && !item.getSoulCrystalOptions().isEmpty()) || ((item.getSoulCrystalSpecialOptions() != null) && !item.getSoulCrystalSpecialOptions().isEmpty())) {
			mask |= ItemListType.SOUL_CRYSTAL.getMask();
		}

		return mask;
	}

	protected void writeItemAugment(PacketBody body, ItemInfo item) {
		if ((item != null) && (item.getAugmentation() != null)) {
			body.writeD(item.getAugmentation().getOption1Id());
			body.writeD(item.getAugmentation().getOption2Id());
		} else {
			body.writeD(0);
			body.writeD(0);
		}
	}

	protected void writeItemElementalAndEnchant(PacketBody body, ItemInfo item) {
		writeItemElemental(body, item);
		writeItemEnchantEffect(body, item);
	}

	protected void writeItemElemental(PacketBody body, ItemInfo item) {
		if (item != null) {
			body.writeH(item.getAttackElementType());
			body.writeH(item.getAttackElementPower());
			body.writeH(item.getAttributeDefence(AttributeType.FIRE));
			body.writeH(item.getAttributeDefence(AttributeType.WATER));
			body.writeH(item.getAttributeDefence(AttributeType.WIND));
			body.writeH(item.getAttributeDefence(AttributeType.EARTH));
			body.writeH(item.getAttributeDefence(AttributeType.HOLY));
			body.writeH(item.getAttributeDefence(AttributeType.DARK));
		} else {
			body.writeH(0);
			body.writeH(0);
			body.writeH(0);
			body.writeH(0);
			body.writeH(0);
			body.writeH(0);
			body.writeH(0);
			body.writeH(0);
		}
	}

	protected void writeItemEnchantEffect(PacketBody body, ItemInfo item) {
		// Enchant Effects
		for (int op : item.getEnchantOptions()) {
			body.writeD(op);
		}
	}

	protected void writeItemEnsoulOptions(PacketBody body, ItemInfo item) {
		if (item != null) {
			body.writeC(item.getSoulCrystalOptions().size()); // Size of regular soul crystal options.
			for (EnsoulOption option : item.getSoulCrystalOptions()) {
				body.writeD(option.getId()); // Regular Soul Crystal Ability ID.
			}

			body.writeC(item.getSoulCrystalSpecialOptions().size()); // Size of special soul crystal options.
			for (EnsoulOption option : item.getSoulCrystalSpecialOptions()) {
				body.writeD(option.getId()); // Special Soul Crystal Ability ID.
			}
		} else {
			body.writeC(0); // Size of regular soul crystal options.
			body.writeC(0); // Size of special soul crystal options.
		}
	}

	protected void writeInventoryBlock(PacketBody body, PcInventory inventory) {
		if (inventory.hasInventoryBlock()) {
			body.writeH(inventory.getBlockItems().size());
			body.writeC(inventory.getBlockMode().getClientId());
			for (int id : inventory.getBlockItems()) {
				body.writeD(id);
			}
		} else {
			body.writeH(0x00);
		}
	}
}
