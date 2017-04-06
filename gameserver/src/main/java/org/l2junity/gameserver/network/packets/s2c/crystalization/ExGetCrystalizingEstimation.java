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
package org.l2junity.gameserver.network.packets.s2c.crystalization;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.model.holders.ItemChanceHolder;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.List;

/**
 * @author UnAfraid
 */
public class ExGetCrystalizingEstimation extends GameServerPacket {
	private final List<ItemChanceHolder> _items;

	public ExGetCrystalizingEstimation(List<ItemChanceHolder> items) {
		_items = items;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_GET_CRYSTALIZING_ESTIMATION.writeId(body);

		body.writeD(_items.size());
		for (ItemChanceHolder holder : _items) {
			body.writeD(holder.getId());
			body.writeQ(holder.getCount());
			body.writeF(holder.getChance());
		}
	}
}