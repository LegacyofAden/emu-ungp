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

import org.l2junity.gameserver.enums.ItemLocation;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.itemcontainer.Inventory;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.packets.GameClientPacket;

import java.util.ArrayList;
import java.util.List;

/**
 * Format:(ch) d[dd]
 *
 * @author -Wooden-
 */
public final class RequestSaveInventoryOrder extends GameClientPacket {
	private List<InventoryOrder> _order;

	/**
	 * client limit
	 */
	private static final int LIMIT = 125;

	@Override
	public void readImpl() {
		int sz = readD();
		sz = Math.min(sz, LIMIT);
		_order = new ArrayList<>(sz);
		for (int i = 0; i < sz; i++) {
			int objectId = readD();
			int order = readD();
			_order.add(new InventoryOrder(objectId, order));
		}
	}

	@Override
	public void runImpl() {
		Player player = getClient().getActiveChar();
		if (player != null) {
			Inventory inventory = player.getInventory();
			for (InventoryOrder order : _order) {
				ItemInstance item = inventory.getItemByObjectId(order.objectID);
				if ((item != null) && (item.getItemLocation() == ItemLocation.INVENTORY)) {
					item.setItemLocation(ItemLocation.INVENTORY, order.order);
				}
			}
		}
	}

	private static class InventoryOrder {
		int order;

		int objectID;

		public InventoryOrder(int id, int ord) {
			objectID = id;
			order = ord;
		}
	}
}
