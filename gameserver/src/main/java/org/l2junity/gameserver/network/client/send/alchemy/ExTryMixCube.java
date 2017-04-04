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
package org.l2junity.gameserver.network.client.send.alchemy;

import org.l2junity.gameserver.enums.TryMixCubeType;
import org.l2junity.gameserver.model.holders.AlchemyResult;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.gameserver.network.client.send.IClientOutgoingPacket;
import org.l2junity.network.PacketWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sdw
 */
public class ExTryMixCube implements IClientOutgoingPacket {
	private final TryMixCubeType _type;
	private final List<AlchemyResult> _items = new ArrayList<>();

	public ExTryMixCube(TryMixCubeType type) {
		_type = type;
	}

	public void addItem(AlchemyResult item) {
		_items.add(item);
	}

	@Override
	public boolean write(PacketWriter packet) {
		OutgoingPackets.EX_TRY_MIX_CUBE.writeId(packet);

		packet.writeC(_type.ordinal());
		packet.writeD(_items.size());
		for (AlchemyResult holder : _items) {
			packet.writeC(holder.getType().ordinal());
			packet.writeD(holder.getId());
			packet.writeQ(holder.getCount());
		}
		return true;
	}
}