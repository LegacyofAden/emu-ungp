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

public class ExRegenMax extends GameServerPacket {
	private final int _time;
	private final int _tickInterval;
	private final double _amountPerTick;

	public ExRegenMax(int time, int tickInterval, double amountPerTick) {
		_time = time;
		_tickInterval = tickInterval;
		_amountPerTick = amountPerTick;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_REGEN_MAX.writeId(body);

		body.writeD(1);
		body.writeD(_time);
		body.writeD(_tickInterval);
		body.writeF(_amountPerTick);
	}
}