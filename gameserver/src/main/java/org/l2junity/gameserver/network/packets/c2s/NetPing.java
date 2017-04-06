package org.l2junity.gameserver.network.packets.c2s;

import org.l2junity.gameserver.network.packets.GameClientPacket;

/**
 * @author ANZO
 * @since 06.04.2017
 */
public class NetPing extends GameClientPacket {
	private long ping = System.currentTimeMillis();

	@Override
	protected void readImpl() {
		readD(); //object id
		readD(); //fps
		readD(); //mtu
	}

	@Override
	protected void runImpl() {
		getClient().setPing(ping);
		getClient().setLastPingSendTime(ping);
	}
}
