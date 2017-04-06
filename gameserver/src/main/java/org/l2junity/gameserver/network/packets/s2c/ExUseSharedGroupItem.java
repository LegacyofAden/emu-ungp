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
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author KenM
 */
public class ExUseSharedGroupItem extends GameServerPacket {
	private final int _itemId;
	private final int _grpId;
	private final int _remainingTime;
	private final int _totalTime;

	public ExUseSharedGroupItem(int itemId, int grpId, long remainingTime, int totalTime) {
		_itemId = itemId;
		_grpId = grpId;
		_remainingTime = (int) (remainingTime / 1000);
		_totalTime = totalTime / 1000;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_USE_SHARED_GROUP_ITEM.writeId(body);

		body.writeD(_itemId);
		body.writeD(_grpId);
		body.writeD(_remainingTime);
		body.writeD(_totalTime);
	}
}