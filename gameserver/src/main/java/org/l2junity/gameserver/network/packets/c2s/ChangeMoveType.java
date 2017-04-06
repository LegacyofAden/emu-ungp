package org.l2junity.gameserver.network.packets.c2s;

import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameClientPacket;

/**
 * @author ANZO
 * @since 06.04.2017
 */
public class ChangeMoveType extends GameClientPacket {
	private boolean isRunning;

	@Override
	protected void readImpl() {
		isRunning = readD() == 1;
	}

	@Override
	protected void runImpl() {
		Player player = getClient().getActiveChar();
		if (player == null)
			return;
		if (isRunning) {
			player.setRunning();
		} else {
			player.setWalking();
		}
	}
}
