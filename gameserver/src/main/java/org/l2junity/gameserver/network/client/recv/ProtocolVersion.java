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
package org.l2junity.gameserver.network.client.recv;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.core.configs.GameserverConfig;
import org.l2junity.gameserver.network.client.L2GameClient;
import org.l2junity.gameserver.network.client.send.KeyPacket;
import org.l2junity.network.PacketReader;

@Slf4j(topic = "accounting")
public final class ProtocolVersion implements IClientIncomingPacket {
	private int _version;

	@Override
	public boolean read(L2GameClient client, PacketReader packet) {
		_version = packet.readD();
		return true;
	}

	@Override
	public void run(L2GameClient client) {
		// this packet is never encrypted
		if (_version == -2) {
			// this is just a ping attempt from the new C2 client
			client.closeNow();
		} else if (!GameserverConfig.PROTOCOL_LIST.contains(_version)) {
			log.warn("Wrong protocol version {}, {}", _version, client);
			client.setProtocolOk(false);
			client.close(new KeyPacket(client.enableCrypt(), 0));
		} else {
			client.sendPacket(new KeyPacket(client.enableCrypt(), 1));
			client.setProtocolOk(true);
		}
	}
}
