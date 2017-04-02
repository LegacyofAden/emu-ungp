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

import org.l2junity.gameserver.model.PremiumItem;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.network.PacketWriter;

import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Gnacik
 */
public class ExGetPremiumItemList implements IClientOutgoingPacket {
	private final PlayerInstance _activeChar;

	private final Map<Integer, PremiumItem> _map;

	public ExGetPremiumItemList(PlayerInstance activeChar) {
		_activeChar = activeChar;
		_map = _activeChar.getPremiumItemList();
	}

	@Override
	public boolean write(PacketWriter packet) {
		OutgoingPackets.EX_GET_PREMIUM_ITEM_LIST.writeId(packet);

		packet.writeD(_map.size());
		for (Entry<Integer, PremiumItem> entry : _map.entrySet()) {
			PremiumItem item = entry.getValue();
			packet.writeQ(entry.getKey());
			packet.writeD(item.getItemId());
			packet.writeQ(item.getCount());
			packet.writeD(0x00); // ?
			packet.writeS(item.getSender());
		}
		return true;
	}
}
