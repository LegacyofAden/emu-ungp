package org.l2junity.loginserver.network.packets.s2c;

import org.l2junity.loginserver.network.LoginClient;
import org.l2junity.loginserver.network.packets.LoginServerPacket;

/**
 * @author ANZO
 * @since 05.04.2017
 */
public class Init extends LoginServerPacket {
	private final int connectionId;
	private final byte[] publicKey;
	private final byte[] blowfishKey;

	public Init(LoginClient client) {
		this.connectionId = client.getConnectionId();
		this.publicKey = client.getScrambledRSAKeyPair().getScrambledModulus();
		this.blowfishKey = client.getBlowfishKey().getEncoded();
	}

	@Override
	protected void writeImpl(PacketBody body) {
		body.writeC(0x00);
		body.writeD(connectionId); // connection id
		body.writeD(0x0000C621); // protocol revision
		body.writeB(publicKey); // RSA Public Key
		body.writeD(0x29DD954E); // UNKNOWN GAMEGUARD
		body.writeD(0x77C39CFC); // UNKNOWN GAMEGUARD
		body.writeD(0x97ADB620); // UNKNOWN GAMEGUARD
		body.writeD(0x07BDE0F7); // UNKNOWN GAMEGUARD
		body.writeB(blowfishKey); // String (BlowFishkey)
		body.writeC(0x00); // String (NULL termination)
	}
}