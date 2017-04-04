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
import org.l2junity.commons.model.SessionInfo;
import org.l2junity.gameserver.network.client.L2GameClient;
import org.l2junity.gameserver.network.client.send.LoginFail;
import org.l2junity.gameserver.service.GameServerRMI;
import org.l2junity.network.PacketReader;

@Slf4j
public final class AuthLogin implements IClientIncomingPacket {
	private String accountName;

	private int playKey;
	private long loginKey;

	@Override
	public boolean read(L2GameClient client, PacketReader packet) {
		accountName = packet.readS().toLowerCase();
		packet.readD();
		playKey = packet.readD();
		loginKey = packet.readQ();
		return true;
	}

	@Override
	public void run(L2GameClient client) {
		if (accountName.isEmpty() || !client.isProtocolOk()) {
			client.closeNow();
			return;
		}

		if (client.getAccountName() == null) {
			SessionInfo sessionInfo = new SessionInfo(accountName, loginKey, playKey);
			if (!GameServerRMI.getInstance().tryAddGameClient(client, sessionInfo)) {
				log.warn("Session key isn't correct. Closing connection for account {}", accountName);
				client.close(new LoginFail(LoginFail.SYSTEM_ERROR_LOGIN_LATER));
			}
		}
	}
}
