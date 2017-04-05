package org.l2junity.loginserver.network.packets.s2c;

import org.l2junity.loginserver.network.packets.LoginServerPacket;

/**
 * @author ANZO
 * @since 25.03.2017
 */
public class GGAuth extends LoginServerPacket {
	private int response;

	public GGAuth(int response) {
		this.response = response;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		body.writeC(0x0b);
		body.writeD(response);
		body.writeD(0x00);
		body.writeD(0x00);
		body.writeD(0x00);
		body.writeD(0x00);
	}
}