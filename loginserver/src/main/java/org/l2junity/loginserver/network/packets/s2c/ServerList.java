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
package org.l2junity.loginserver.network.packets.s2c;

import org.l2junity.commons.model.AccountInfo;
import org.l2junity.commons.model.GameServerInfo;
import org.l2junity.commons.model.enums.ServerStatus;
import org.l2junity.commons.network.PacketBody;
import org.l2junity.loginserver.LoginServer;
import org.l2junity.loginserver.network.LoginClient;
import org.l2junity.loginserver.network.packets.LoginServerPacket;

import java.util.Collection;

/**
 * @author ANZO
 * @since 24.03.2017
 */
public class ServerList extends LoginServerPacket {
	private final LoginClient client;

	public ServerList(LoginClient client) {
		this.client = client;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		body.writeC(0x04);

		Collection<GameServerInfo> gameservers = LoginServer.getInstance().getRmi().getGameServers();

		body.writeC(gameservers.size());

		if (!gameservers.isEmpty()) {
			boolean isLastServerOn = gameservers.stream().anyMatch(server -> server.getStatus() != ServerStatus.DOWN
					&& server.getId() == client.getAccount().getLastServerId());
			body.writeC(isLastServerOn ? client.getAccount().getLastServerId() : 0);

			for (GameServerInfo gameServerInfo : gameservers) {
				body.writeC(gameServerInfo.getId());

				if (gameServerInfo.getAddress() != null) {
					byte[] raw = gameServerInfo.getAddress().getAddress();
					body.writeC(raw[0] & 0xff);
					body.writeC(raw[1] & 0xff);
					body.writeC(raw[2] & 0xff);
					body.writeC(raw[3] & 0xff);
					body.writeD(gameServerInfo.getPort());
				} else {
					body.writeC(127);
					body.writeC(0);
					body.writeC(0);
					body.writeC(1);
					body.writeD(7777);
				}

				body.writeC(gameServerInfo.getAgeLimit().getAge());
				body.writeC(0); // isPvp - unused
				body.writeH(gameServerInfo.getCurrentOnline());
				body.writeH(gameServerInfo.getMaxOnline());
				body.writeC(gameServerInfo.getStatus() == ServerStatus.DOWN ? 0 : 1);
				body.writeD(gameServerInfo.getServerTypesMask());
				body.writeC(0); // Puts [NULL] in front of name due to missing file in NA client
			}

			body.writeH(0); // Unused by client

			body.writeC(gameservers.size());
			for (GameServerInfo server : gameservers) {
				body.writeC(server.getId());
				try {
					AccountInfo accountInfo = server.getConnection().getAccountInfo(client.getAccount().getName());
					body.writeC(accountInfo.getCharCount());
					body.writeC(accountInfo.getDeleteTimeInfo().size());
					for (Long deleteTimeInfo : accountInfo.getDeleteTimeInfo()) {
						body.writeC((int) (deleteTimeInfo - System.currentTimeMillis() / 1000L));
					}
				} catch (Exception e) {
					body.writeC(0);
					body.writeC(0);
				}
			}
		}
	}
}
