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
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.ArrayList;
import java.util.List;

public final class ItemList extends AbstractItemPacket {
	private final Player _activeChar;
	private final List<ItemInstance> _items;
	private final boolean _showWindow;

	public ItemList(Player activeChar, boolean showWindow) {
		_activeChar = activeChar;
		_showWindow = showWindow;
		_items = new ArrayList<>(activeChar.getInventory().getItems(item -> !item.isQuestItem()));
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.ITEM_LIST.writeId(body);

		body.writeH(_showWindow ? 0x01 : 0x00);
		body.writeH(_items.size());
		for (ItemInstance item : _items) {
			writeItem(body, item);
		}
		writeInventoryBlock(body, _activeChar.getInventory());
	}
}
