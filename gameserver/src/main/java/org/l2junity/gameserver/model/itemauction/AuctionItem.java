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
package org.l2junity.gameserver.model.itemauction;

import org.l2junity.gameserver.datatables.ItemTable;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.items.ItemTemplate;
import org.l2junity.gameserver.model.items.instance.ItemInstance;

/**
 * @author Forsaiken
 */
public final class AuctionItem {
	private final int _auctionItemId;
	private final int _auctionLength;
	private final long _auctionInitBid;

	private final int _itemId;
	private final long _itemCount;

	public AuctionItem(final int auctionItemId, final int auctionLength, final long auctionInitBid, final int itemId, final long itemCount, final StatsSet itemExtra) {
		_auctionItemId = auctionItemId;
		_auctionLength = auctionLength;
		_auctionInitBid = auctionInitBid;

		_itemId = itemId;
		_itemCount = itemCount;
	}

	public final boolean checkItemExists() {
		final ItemTemplate item = ItemTable.getInstance().getTemplate(_itemId);
		if (item == null) {
			return false;
		}
		return true;
	}

	public final int getAuctionItemId() {
		return _auctionItemId;
	}

	public final int getAuctionLength() {
		return _auctionLength;
	}

	public final long getAuctionInitBid() {
		return _auctionInitBid;
	}

	public final int getItemId() {
		return _itemId;
	}

	public final long getItemCount() {
		return _itemCount;
	}

	public final ItemInstance createNewItemInstance() {
		final ItemInstance item = ItemTable.getInstance().createItem("ItemAuction", _itemId, _itemCount, null, null);

		item.setEnchantLevel(item.getItem().getDefaultEnchantLevel());

		return item;
	}
}