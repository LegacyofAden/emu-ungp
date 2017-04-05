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
package org.l2junity.loginserver.network.packets.c2s;

import org.l2junity.loginserver.manager.LoginManager;
import org.l2junity.loginserver.network.LoginClient;
import org.l2junity.loginserver.network.LoginClientState;
import org.l2junity.loginserver.network.packets.LoginClientPacket;
import org.l2junity.loginserver.network.packets.s2c.LoginFail;

/**
 * @author ANZO
 */
public class RequestServerLogin extends LoginClientPacket {
	private long loginKey;
	private short serverId;

	@Override
	protected void readImpl() {
		loginKey = readQ();
		serverId = (short) readC();
	}

	@Override
	protected void runImpl() {
		LoginClient client = getClient();
		if (client != null) {
			if (client.getState() == LoginClientState.AUTHED_SERVER_LIST) {
				if (client.getSessionInfo().getLoginKey() == loginKey) {
					LoginManager.getInstance().tryServerLogin(client, serverId);
					return;
				}
			}
			client.close(LoginFail.ACCESS_FAILED_PLEASE_TRY_AGAIN_LATER);
		}
	}
}