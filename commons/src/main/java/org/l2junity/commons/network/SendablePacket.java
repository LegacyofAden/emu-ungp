package org.l2junity.commons.network;

import java.nio.ByteBuffer;

/**
 * The base class for all sendable packets
 *
 * @author izen
 */
public abstract class SendablePacket<TClient extends Client> {
	protected abstract boolean write(TClient client, ByteBuffer buffer);
}
