package org.l2junity.commons.network;

import org.apache.commons.lang3.builder.Builder;
import org.apache.commons.lang3.tuple.Pair;

import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;

/**
 * Client network configuration
 *
 * @author izen
 */
public interface IClientSettings<TClient extends Client> extends INetworkSettings {
	/**
	 * Gets required accepted client socket options
	 *
	 * @return accepted client socket options
	 */
	Pair<SocketOption, Object>[] getClientSocketOptions();

	/**
	 * Gets network clients factory
	 *
	 * @return network clients factory
	 */
	IClientFactory<TClient> getClientFactory();

	Builder<ByteBuffer> getRecvBufferBuilder();

	Builder<ByteBuffer> getSendBufferBuilder();

	IPacketHandler<TClient> getPacketHandler();

	Executor getPacketExecutor();
}
