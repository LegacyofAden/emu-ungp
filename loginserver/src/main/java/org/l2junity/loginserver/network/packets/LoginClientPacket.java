package org.l2junity.loginserver.network.packets;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.network.ReceivablePacket;
import org.l2junity.loginserver.network.LoginClient;

import java.nio.BufferUnderflowException;

/**
 * @author ANZO
 * @since 24.03.2017
 */
@Slf4j
public abstract class LoginClientPacket extends ReceivablePacket<LoginClient> {
	protected abstract void readImpl();

	protected abstract void runImpl();

	@Override
	protected final boolean read() {
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

	protected final void sendPacket(LoginServerPacket packet) {
		getClient().sendPacket(packet);
	}
}