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
package org.l2junity.gameserver.network.packets.s2c.appearance;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author UnAfraid
 */
public class ExPutShapeShiftingTargetItemResult extends GameServerPacket {
	public static int RESULT_FAILED = 0x00;
	public static int RESULT_SUCCESS = 0x01;

	public static ExPutShapeShiftingTargetItemResult FAILED = new ExPutShapeShiftingTargetItemResult(RESULT_FAILED, 0L);

	private final int _resultId;
	private final long _price;

	public ExPutShapeShiftingTargetItemResult(int resultId, long price) {
		_resultId = resultId;
		_price = price;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_PUT_SHAPE_SHIFTING_TARGET_ITEM_RESULT.writeId(body);

		body.writeD(_resultId);
		body.writeQ(_price);
	}
}