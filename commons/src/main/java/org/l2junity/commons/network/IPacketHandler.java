package org.l2junity.commons.network;

import java.nio.ByteBuffer;

/**
 * @author izen
 */
public interface IPacketHandler<TClient extends Client> {
	ReceivablePacket<TClient> handleReceivedPacket(ByteBuffer buffer, TClient client);
}