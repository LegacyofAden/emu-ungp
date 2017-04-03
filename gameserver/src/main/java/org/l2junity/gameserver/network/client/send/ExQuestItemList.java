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
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.network.PacketWriter;

import java.util.Collection;

/**
 * @author JIV
 */
public class ExQuestItemList extends AbstractItemPacket {
	private final Player _activeChar;
	private final Collection<ItemInstance> _items;

	public ExQuestItemList(Player activeChar) {
		_activeChar = activeChar;
		_items = activeChar.getInventory().getItems(ItemInstance::isQuestItem);
	}

	@Override
	public boolean write(PacketWriter packet) {
		OutgoingPackets.EX_QUEST_ITEM_LIST.writeId(packet);

		packet.writeH(_items.size());
		for (ItemInstance item : _items) {
			writeItem(packet, item);
		}
		writeInventoryBlock(packet, _activeChar.getInventory());
		return true;
	}
}
