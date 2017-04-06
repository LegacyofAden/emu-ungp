package org.l2junity.loginserver.network;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.network.IPacketHandler;
import org.l2junity.commons.network.ReceivablePacket;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.loginserver.network.packets.LoginClientPacket;
import org.l2junity.loginserver.network.packets.c2s.RequestAuthLogin;
import org.l2junity.loginserver.network.packets.c2s.RequestGGAuth;
import org.l2junity.loginserver.network.packets.c2s.RequestServerList;
import org.l2junity.loginserver.network.packets.c2s.RequestServerLogin;
import org.l2junity.loginserver.network.packets.s2c.LoginFail;

import java.nio.ByteBuffer;

/**
 * @author ANZO
 * @since 24.03.2017
 */
@Slf4j
@StartupComponent("Network")
public class LoginClientPacketHandler implements IPacketHandler<LoginClient> {
	@Getter(lazy = true)
	private final static LoginClientPacketHandler instance = new LoginClientPacketHandler();

	@Override
	public ReceivablePacket<LoginClient> handleReceivedPacket(ByteBuffer buffer, LoginClient client) {
		LoginClientState state = client.getState();
		int opcode = buffer.get() & 0xFF;
		LoginClientPacket packet = null;

		switch (state) {
			case CONNECTED:
				switch (opcode) {
					case 0x07:
						packet = new RequestGGAuth();
						break;
					default:
						debugOpcode(opcode, state);
						client.close(LoginFail.ACCESS_FAILED);
						break;
				}
				break;
			case AUTHED_GG:
				switch (opcode) {
					case 0x00:
						packet = new RequestAuthLogin();
						break;
					default:
						debugOpcode(opcode, state);
						client.close(LoginFail.ACCESS_FAILED);
						break;
				}
				break;
			case AUTHED_LICENCE:
				switch (opcode) {
					case 0x05:
						packet = new RequestServerList();
						break;
					default:
						debugOpcode(opcode, state);
						client.close(LoginFail.ACCESS_FAILED);
						break;
				}
				break;
			case AUTHED_SERVER_LIST:
				switch (opcode) {
					case 0x02:
						packet = new RequestServerLogin();
						break;
					default:
						debugOpcode(opcode, state);
						client.close(LoginFail.ACCESS_FAILED);
						break;
				}
				break;
		}
		return packet;
	}

	private void debugOpcode(int opcode, LoginClientState state) {
		log.warn("Unknown Opcode: {} for state: {}", opcode, state.name());
	}
}