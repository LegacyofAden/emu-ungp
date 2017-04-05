package org.l2junity.loginserver.network.packets.c2s;

import org.l2junity.loginserver.network.LoginClient;
import org.l2junity.loginserver.network.LoginClientState;
import org.l2junity.loginserver.network.packets.LoginClientPacket;
import org.l2junity.loginserver.network.packets.s2c.GGAuth;
import org.l2junity.loginserver.network.packets.s2c.LoginFail;

/**
 * @author ANZO
 * @since 05.04.2017
 */
public class RequestGGAuth extends LoginClientPacket {
	private int connectionId;
	private byte[] gameGuardData;

	@Override
	protected void readImpl() {
		if (getAvailableBytes() >= 20) {
			connectionId = readD();
			gameGuardData = readB(16);
		}
	}

	@Override
	protected void runImpl() {
		LoginClient client = getClient();
		if (client != null) {
			int connectionId = client.getConnectionId();
			if (this.connectionId == client.getConnectionId()) {
				if (client.compareAndSetState(LoginClientState.CONNECTED, LoginClientState.AUTHED_GG)) {
					client.setGameGuardData(gameGuardData);
					client.sendPacket(new GGAuth(connectionId));
					return;
				}
			}

			client.close(LoginFail.ACCESS_FAILED);
		}
	}
}