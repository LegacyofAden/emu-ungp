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
import org.l2junity.gameserver.model.actor.instance.PetInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.ArrayList;
import java.util.List;

public class GMViewItemList extends AbstractItemPacket {
	private final List<ItemInstance> _items = new ArrayList<>();
	private final int _limit;
	private final String _playerName;

	public GMViewItemList(Player cha) {
		_playerName = cha.getName();
		_limit = cha.getInventoryLimit();
		_items.addAll(cha.getInventory().getItems());
	}

	public GMViewItemList(PetInstance cha) {
		_playerName = cha.getName();
		_limit = cha.getInventoryLimit();
		_items.addAll(cha.getInventory().getItems());
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.GM_VIEW_ITEM_LIST.writeId(body);

		body.writeS(_playerName);
		body.writeD(_limit); // inventory limit
		body.writeH(0x01); // show window ??
		body.writeH(_items.size());
		for (ItemInstance item : _items) {
			writeItem(body, item);
		}
	}
}
