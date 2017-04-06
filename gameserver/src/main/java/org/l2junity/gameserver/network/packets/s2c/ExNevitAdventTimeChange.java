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
 * @author mochitto
 */
public class ExNevitAdventTimeChange extends GameServerPacket {
	private final boolean _paused;
	private final int _time;

	public ExNevitAdventTimeChange(int time) {
		_time = time > 240000 ? 240000 : time;
		_paused = _time < 1;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_GET_CRYSTALIZING_ESTIMATION.writeId(body);

		// state 0 - pause 1 - started
		body.writeC(_paused ? 0x00 : 0x01);
		// left time in ms max is 16000 its 4m and state is automatically changed to quit
		body.writeD(_time);
	}
}