package org.l2junity.loginserver.network.packets;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.network.PacketBody;
import org.l2junity.commons.network.SendablePacket;
import org.l2junity.loginserver.network.LoginClient;

import java.nio.ByteBuffer;

/**
 * @author ANZO
 * @since 05.04.2017
 */
@Slf4j
public abstract class LoginServerPacket extends SendablePacket<LoginClient> {
	protected abstract void writeImpl(PacketBody body);

	@Override
	protected boolean write(LoginClient client, ByteBuffer buffer) {
		try {
			PacketBody body = new PacketBody<>(client, buffer);
			writeImpl(body);
		} catch (Exception e) {
			log.error("Sending {} to {} failed", getClass().getSimpleName(), client, e);
			return false;
		}
		return true;
	}
}