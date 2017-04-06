package org.l2junity.gameserver.network.packets;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.network.ReceivablePacket;
import org.l2junity.gameserver.network.GameClient;

import java.nio.BufferUnderflowException;

/**
 * @author ANZO
 * @since 06.04.2017
 */
@Slf4j
public abstract class GameClientPacket extends ReceivablePacket<GameClient> {
	protected abstract void readImpl();

	protected abstract void runImpl();

	@Override
	public final boolean read() {
		try {
			readImpl();
		} catch (BufferUnderflowException e) {
			return false;
		} catch (Exception ex) {
			log.error("Error while reading client packet", ex);
			return false;
		}
		return true;
	}

	@Override
	public final void run() {
		try {
			runImpl();
		} catch (Exception e) {
			log.error("Receive {} from {} failed", getClass().getSimpleName(), getClient(), e);
		}
	}

	protected final void sendPacket(GameServerPacket packet) {
		getClient().sendPacket(packet);
	}
}