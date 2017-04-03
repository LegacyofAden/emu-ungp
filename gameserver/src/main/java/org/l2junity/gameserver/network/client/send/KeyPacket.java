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

import org.l2junity.core.configs.GameserverConfig;
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.network.PacketWriter;

public final class KeyPacket implements IClientOutgoingPacket {
	private final byte[] _key;
	private final int _result;

	public KeyPacket(byte[] key, int result) {
		_key = key;
		_result = result;
	}

	@Override
	public boolean write(PacketWriter packet) {
		OutgoingPackets.VERSION_CHECK.writeId(packet);

		packet.writeC(_result); // 0 - wrong protocol, 1 - protocol ok
		for (int i = 0; i < 8; i++) {
			packet.writeC(_key[i]); // key
		}
		packet.writeD(0x01);
		packet.writeD(GameserverConfig.SERVER_ID); // server id
		packet.writeC(0x01);
		packet.writeD(0x00); // obfuscation key
		packet.writeC((GeneralConfig.SERVER_LIST_TYPE & 0x400) == 0x400 ? 0x01 : 0x00); // isClassic
		return true;
	}
}
