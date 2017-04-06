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
import org.l2junity.core.configs.GameserverConfig;
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

public final class KeyPacket extends GameServerPacket {
	private final byte[] _key;
	private final int _result;

	public KeyPacket(byte[] key, int result) {
		_key = key;
		_result = result;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.VERSION_CHECK.writeId(body);

		body.writeC(_result); // 0 - wrong protocol, 1 - protocol ok
		for (int i = 0; i < 8; i++) {
			body.writeC(_key[i]); // key
		}
		body.writeD(0x01);
		body.writeD(GameserverConfig.SERVER_ID); // server id
		body.writeC(0x01);
		body.writeD(0x00); // obfuscation key
		body.writeC((GeneralConfig.SERVER_LIST_TYPE & 0x400) == 0x400 ? 0x01 : 0x00); // isClassic
	}
}