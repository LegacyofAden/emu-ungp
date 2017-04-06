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
import org.l2junity.gameserver.model.TradeItem;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.Collection;

public class PrivateStoreManageListSell extends AbstractItemPacket {
	private final int _objId;
	private final long _playerAdena;
	private final boolean _packageSale;
	private final Collection<TradeItem> _itemList;
	private final TradeItem[] _sellList;

	public PrivateStoreManageListSell(Player player, boolean isPackageSale) {
		_objId = player.getObjectId();
		_playerAdena = player.getAdena();
		player.getSellList().updateItems();
		_packageSale = isPackageSale;
		_itemList = player.getInventory().getAvailableItems(player.getSellList());
		_sellList = player.getSellList().getItems();
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.PRIVATE_STORE_MANAGE_LIST.writeId(body);

		body.writeD(_objId);
		body.writeD(_packageSale ? 1 : 0); // Package sell
		body.writeQ(_playerAdena);

		body.writeD(_itemList.size()); // for potential sells
		for (TradeItem item : _itemList) {
			writeItem(body, item);
			body.writeQ(item.getItem().getReferencePrice() * 2);
		}

		body.writeD(_sellList.length); // count for any items already added for sell
		for (TradeItem item : _sellList) {
			writeItem(body, item);
			body.writeQ(item.getPrice());
			body.writeQ(item.getItem().getReferencePrice() * 2);
		}
	}
}