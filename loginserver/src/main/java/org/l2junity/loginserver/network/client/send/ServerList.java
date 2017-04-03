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
package org.l2junity.loginserver.network.client.send;

import org.l2junity.commons.model.GameServerInfo;
import org.l2junity.commons.model.enums.ServerStatus;
import org.l2junity.loginserver.manager.LoginServerRMI;
import org.l2junity.loginserver.network.client.ClientHandler;
import org.l2junity.network.IOutgoingPacket;
import org.l2junity.network.PacketWriter;

import java.util.Collection;

/**
 * @author NosBit
 */
public class ServerList implements IOutgoingPacket {
	private final ClientHandler client;

	public ServerList(ClientHandler client) {
		this.client = client;
	}

	@Override
	public boolean write(PacketWriter packet) {
		packet.writeC(0x04);

		Collection<GameServerInfo> gameservers = LoginServerRMI.getInstance().getGameServers();

		packet.writeC(gameservers.size());

		boolean isLastServerOn = gameservers.stream().anyMatch(server -> server.getStatus() != ServerStatus.DOWN
				&& server.getId() == client.getAccount().getLastServerId());
		packet.writeC(isLastServerOn ? client.getAccount().getLastServerId() : 0);

		for (GameServerInfo gameServerInfo : gameservers) {
			packet.writeC(gameServerInfo.getId());

			byte[] raw = gameServerInfo.getAddress().getAddress();
			packet.writeC(raw[0] & 0xff);
			packet.writeC(raw[1] & 0xff);
			packet.writeC(raw[2] & 0xff);
			packet.writeC(raw[3] & 0xff);
			packet.writeD(gameServerInfo.getPort());

			packet.writeC(gameServerInfo.getAgeLimit().getAge());
			packet.writeC(0); // isPvp - unused
			packet.writeH(gameServerInfo.getCurrentOnline());
			packet.writeH(gameServerInfo.getMaxOnline());
			packet.writeC(gameServerInfo.getStatus() == ServerStatus.DOWN ? 0 : 1);
			packet.writeD(gameServerInfo.getServerTypesMask());
			packet.writeC(0); // Puts [NULL] in front of name due to missing file in NA client
		}

		packet.writeH(0); // Unused by client

		packet.writeC(gameservers.size());
		for (GameServerInfo server : gameservers) {
			packet.writeC(server.getId());
			packet.writeC(0); // Players on account
			packet.writeC(0); // Players marked to delete
			// packet.writeC((int) (t - System.currentTimeMillis() / 1000L)) // t - delete time
		}

		return true;
	}
}
