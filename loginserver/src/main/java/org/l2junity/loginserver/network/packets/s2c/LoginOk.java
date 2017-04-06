package org.l2junity.loginserver.network.packets.s2c;

import org.l2junity.commons.model.SessionInfo;
import org.l2junity.commons.network.PacketBody;
import org.l2junity.loginserver.network.packets.LoginServerPacket;

/**
 * @author ANZO
 * @since 25.03.2017
 */
public class LoginOk extends LoginServerPacket {
	private final SessionInfo sessionInfo;

	public LoginOk(SessionInfo sessionInfo) {
		this.sessionInfo = sessionInfo;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		body.writeC(0x03);
		body.writeQ(sessionInfo.getLoginKey());
		body.writeD(0);
		body.writeD(0);
		body.writeD(0);
		body.writeD(0);
		body.writeD(0);
		body.writeD(0);

		// forbidden server makes name red
		// its a mask
		// 0x01 is server id 1
		// 0x02 is server id 2
		// 0x04 is server id 3
		// etc
		body.writeD(1);
		body.writeD(0xFFFFFFFF);
		body.writeD(0xFFFFFFFF);
		body.writeD(0xFFFFFFFF);
	}
}
