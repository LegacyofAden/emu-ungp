package org.l2junity.commons.network;

import org.apache.commons.lang3.tuple.Pair;

import java.net.InetSocketAddress;
import java.net.SocketOption;

/**
 * NetworkThread network configuration
 *
 * @author izen
 */
public interface IServerSettings<TClient extends Client> extends INetworkSettings {
	/**
	 * Gets the socket address a network server configured with this settings must bind to
	 *
	 * @return Socket address to bind.
	 */
	InetSocketAddress getBindAddress();

	/**
	 * Gets listening server socket backlog size
	 *
	 * @return Bind backlog size
	 */
	int getBindBacklog();

	/**
	 * Gets the connections acceptance filer.
	 *
	 * @return Acceptance filter.
	 */
	IAcceptFilter getAcceptFilter();

	/**
	 * Get the server socket options. Those options will be used during server socket startup.
	 *
	 * @return NetworkThread socket options.
	 */
	Pair<SocketOption, Object>[] getServerSocketOptions();
}
