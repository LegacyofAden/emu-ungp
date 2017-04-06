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

public class Dice extends GameServerPacket {
	private final int _charObjId;
	private final int _itemId;
	private final int _number;
	private final int _x;
	private final int _y;
	private final int _z;

	/**
	 * @param charObjId
	 * @param itemId
	 * @param number
	 * @param x
	 * @param y
	 * @param z
	 */
	public Dice(int charObjId, int itemId, int number, double x, double y, double z) {
		_charObjId = charObjId;
		_itemId = itemId;
		_number = number;
		_x = (int) x;
		_y = (int) y;
		_z = (int) z;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.DICE.writeId(body);

		body.writeD(_charObjId); // object id of player
		body.writeD(_itemId); // item id of dice (spade) 4625,4626,4627,4628
		body.writeD(_number); // number rolled
		body.writeD(_x); // x
		body.writeD(_y); // y
		body.writeD(_z); // z
	}
}
