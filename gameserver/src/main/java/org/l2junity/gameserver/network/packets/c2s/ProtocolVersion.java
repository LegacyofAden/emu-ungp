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
package org.l2junity.gameserver.network.packets.c2s;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.core.configs.GameserverConfig;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.KeyPacket;


@Slf4j(topic = "accounting")
public final class ProtocolVersion extends GameClientPacket {
	private int _version;

	@Override
	public void readImpl() {
		_version = readD();
	}

	@Override
	public void runImpl() {
		// this packet is never encrypted
		if (_version == -2) {
			// this is just a ping attempt from the new C2 client
			getClient().closeForce();
		} else if (!GameserverConfig.PROTOCOL_LIST.contains(_version)) {
			log.warn("Wrong protocol version {}, {}", _version, getClient());
			getClient().setProtocolOk(false);
			getClient().close(new KeyPacket(getClient().enableCrypt(), 0));
		} else {
			getClient().sendPacket(new KeyPacket(getClient().enableCrypt(), 1));
			getClient().setProtocolOk(true);
		}
	}
}
