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
package org.l2junity.gameserver.network.client.send;

import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.buylist.Product;
import org.l2junity.gameserver.model.buylist.ProductList;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.network.PacketWriter;

import java.util.Collection;

public final class BuyList extends AbstractItemPacket {
	private final int _listId;
	private final Collection<Product> _list;
	private final long _money;
	private final int _inventorySlots;
	private final double _castleTaxRate;

	public BuyList(ProductList list, Player player, double castleTaxRate) {
		_listId = list.getListId();
		_list = list.getProducts();
		_money = player.getAdena();
		_inventorySlots = player.getInventory().getItems((item) -> !item.isQuestItem()).size();
		_castleTaxRate = castleTaxRate;
	}

	@Override
	public boolean write(PacketWriter packet) {
		OutgoingPackets.EX_BUY_SELL_LIST.writeId(packet);

		packet.writeD(0x00); // Type BUY
		packet.writeQ(_money); // current money
		packet.writeD(_listId);
		packet.writeD(_inventorySlots);
		packet.writeH(_list.size());
		for (Product product : _list) {
			if ((product.getCount() > 0) || !product.hasLimitedStock()) {
				writeItem(packet, product);
				packet.writeQ((long) (product.getPrice() * (1.0 + _castleTaxRate + product.getBaseTaxRate())));
			}
		}
		return true;
	}
}
