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
import org.l2junity.gameserver.datatables.ItemTable;
import org.l2junity.gameserver.model.ItemInfo;
import org.l2junity.gameserver.model.holders.ItemChanceHolder;
import org.l2junity.gameserver.model.holders.ItemHolder;
import org.l2junity.gameserver.model.holders.MultisellEntryHolder;
import org.l2junity.gameserver.model.holders.PreparedMultisellListHolder;
import org.l2junity.gameserver.model.items.ItemTemplate;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import static org.l2junity.gameserver.data.xml.impl.MultisellData.PAGE_SIZE;

public final class MultiSellList extends AbstractItemPacket {
	private int _size, _index;
	private final PreparedMultisellListHolder _list;
	private final boolean _finished;

	public MultiSellList(PreparedMultisellListHolder list, int index) {
		_list = list;
		_index = index;
		_size = list.getEntries().size() - index;
		if (_size > PAGE_SIZE) {
			_finished = false;
			_size = PAGE_SIZE;
		} else {
			_finished = true;
		}
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.MULTI_SELL_LIST.writeId(body);

		body.writeC(0x00);
		body.writeD(_list.getId()); // list id
		body.writeC(0x00); // GOD Unknown
		body.writeD(1 + (_index / PAGE_SIZE)); // page started from 1
		body.writeD(_finished ? 0x01 : 0x00); // finished
		body.writeD(PAGE_SIZE); // size of pages
		body.writeD(_size); // list length
		body.writeC(_list.isChanceMultisell() ? 0x01 : 0x00); // new multisell window
		body.writeD(0x20); // Always 32 oO

		while (_size-- > 0) {
			final ItemInfo itemEnchantment = _list.getItemEnchantment(_index);
			final MultisellEntryHolder entry = _list.getEntries().get(_index++);

			body.writeD(_index); // Entry ID. Start from 1.
			body.writeC(entry.isStackable() ? 1 : 0);

			// Those values will be passed down to MultiSellChoose packet.
			body.writeH(itemEnchantment != null ? itemEnchantment.getEnchantLevel() : 0); // enchant level
			writeItemAugment(body, itemEnchantment);
			writeItemElemental(body, itemEnchantment);
			writeItemEnsoulOptions(body, itemEnchantment);

			body.writeH(entry.getProducts().size());
			body.writeH(entry.getIngredients().size());

			for (ItemChanceHolder product : entry.getProducts()) {
				final ItemTemplate template = ItemTable.getInstance().getTemplate(product.getId());
				final ItemInfo displayItemEnchantment = (_list.isMaintainEnchantment() && (itemEnchantment != null) && (template != null) && template.getClass().equals(itemEnchantment.getItem().getClass())) ? itemEnchantment : null;

				body.writeD(product.getId());
				if (template != null) {
					body.writeQ(template.getBodyPart());
					body.writeH(template.getType2());
				} else {
					body.writeQ(0);
					body.writeH(65535);
				}
				body.writeQ(_list.getProductCount(product));
				body.writeH(displayItemEnchantment != null ? displayItemEnchantment.getEnchantLevel() : 0); // enchant level
				body.writeD((int) Math.ceil(product.getChance())); // chance
				writeItemAugment(body, displayItemEnchantment);
				writeItemElemental(body, displayItemEnchantment);
				writeItemEnsoulOptions(body, displayItemEnchantment);
			}

			for (ItemHolder ingredient : entry.getIngredients()) {
				final ItemTemplate template = ItemTable.getInstance().getTemplate(ingredient.getId());
				final ItemInfo displayItemEnchantment = ((itemEnchantment != null) && (itemEnchantment.getItem().getId() == ingredient.getId())) ? itemEnchantment : null;

				body.writeD(ingredient.getId());
				body.writeH(template != null ? template.getType2() : 65535);
				body.writeQ(_list.getIngredientCount(ingredient));
				body.writeH(displayItemEnchantment != null ? displayItemEnchantment.getEnchantLevel() : 0); // enchant level
				writeItemAugment(body, displayItemEnchantment);
				writeItemElemental(body, displayItemEnchantment);
				writeItemEnsoulOptions(body, displayItemEnchantment);
			}
		}
	}
}