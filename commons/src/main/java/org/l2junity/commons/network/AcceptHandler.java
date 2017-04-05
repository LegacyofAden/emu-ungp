package org.l2junity.commons.network;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.util.AIOUtils;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.CompletionHandler;

/**
 * @author izen
 */
@Slf4j
class AcceptHandler<TClient extends Client> implements CompletionHandler<AsynchronousSocketChannel, Void> {
	private final NetworkThread<TClient> _server;

	AcceptHandler(NetworkThread<TClient> server) {
		_server = server;
	}

	/**
	 * Submit this accept handler for server.
	 */
	public void submit() {
		if (!_server.isShutdown()) {
			_server.getServerSocketChannel().accept(null, this);
		}
	}

	@Override
	public void completed(AsynchronousSocketChannel socketChannel, Void attachment) {
		if (_server.isShutdown()) {
			AIOUtils.closeAsyncChannelSilent(socketChannel);
			return;
		}

		// Resubmit for new connections
		submit();

		try {
			IAcceptFilter acceptFilter = _server.getAcceptFilter();
			InetSocketAddress socketAddress = (InetSocketAddress) socketChannel.getRemoteAddress();

			// Is connection allowed from this address
			if (acceptFilter.isAllowedAddress(socketAddress)) {
				Connection<TClient> connection = new Connection<>(_server, socketChannel, socketAddress);
				connection.init();
			} else {
				AIOUtils.closeAsyncChannelSilent(socketChannel); // Forbidden address. Close.
			}
		} catch (Exception ex) {
			log.error("Exception thrown while processing accepted connection", ex);
			AIOUtils.closeAsyncChannelSilent(socketChannel);
		}
	}

	@Override
	public void failed(Throwable reason, Void attachment) {
		if (_server.isShutdown()) {
			return;
		}
		if (reason instanceof ClosedChannelException) // Throws sometime for a closed connection.
		{
			return;
		}

		// Resubmit for new connections
		submit();

		log.error("Exception thrown while accepting connection", reason);
	}
}
