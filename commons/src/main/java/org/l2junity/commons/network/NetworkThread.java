package org.l2junity.commons.network;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.l2junity.commons.util.AIOUtils;
import org.l2junity.core.configs.NetworkConfig;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketOption;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class NetworkThread<TClient extends Client> {
	private Executor _packetExecutor;

	private InetSocketAddress bindAddress;
	private Pair<SocketOption, Object>[] clientSocketOptions;
	private Pair<SocketOption, Object>[] serverSocketOptions;

	private final AcceptHandler<TClient> acceptHandler;
	private final AlwaysAcceptFilter ACCEPT_FILTER = new AlwaysAcceptFilter();

	private AsynchronousChannelGroup channelGroup;
	private AsynchronousServerSocketChannel serverSocketChannel;
	private volatile boolean isShutdown = false;

	public NetworkThread() {
		acceptHandler = new AcceptHandler<>(this);
		switch (NetworkConfig.PACKET_EXEC_MODE) {
			case DIRECT:
				_packetExecutor = new DirectExecutor();
				;
				break;
			case OFFLOAD:
				_packetExecutor = IOExecutorService.getInstance();
				break;
			case QUEUED:
				_packetExecutor = new PacketExecQueue(IOExecutorService.getInstance());
				break;
		}
	}

	public abstract IClientFactory<TClient> getClientFactory();

	public abstract IPacketHandler<TClient> getPacketHandler();

	public Executor getPacketExecutor() {
		return _packetExecutor;
	}

	public Pair<SocketOption, Object>[] getClientSocketOptions() {
		if (clientSocketOptions == null) {
			clientSocketOptions = AIOUtils.parseSocketOptions(NetworkConfig.CLIENT_SOCKET_OPTIONS);
		}
		return clientSocketOptions;
	}

	public Pair<SocketOption, Object>[] getServerSocketOptions() {
		if (serverSocketOptions == null) {
			serverSocketOptions = AIOUtils.parseSocketOptions(NetworkConfig.SERVER_SOCKET_OPTIONS);
		}
		return serverSocketOptions;
	}

	public AsynchronousChannelGroup getChannelGroup() {
		if (channelGroup == null) {
			try {
				switch (NetworkConfig.IO_EXECUTION_MODE) {
					case FIXED:
						channelGroup = AsynchronousChannelGroup.withFixedThreadPool(NetworkConfig.IO_EXECUTION_THREAD_NUM, Executors.defaultThreadFactory());
					case POOLED: {
						channelGroup = AsynchronousChannelGroup.withThreadPool(IOExecutorService.getInstance());
					}
				}
			} catch (Exception e) {
				log.error("Error while creating AsynchronousChannelGroup", e);
			}
		}
		return channelGroup;
	}

	public InetSocketAddress getBindAddress() {
		if (bindAddress == null) {
			if (NetworkConfig.HOST.isEmpty() || NetworkConfig.HOST.equals("*")) {
				bindAddress = new InetSocketAddress(NetworkConfig.PORT);
			}
			bindAddress = new InetSocketAddress(NetworkConfig.HOST, NetworkConfig.PORT);
		}
		return bindAddress;
	}

	public IAcceptFilter getAcceptFilter() {
		return ACCEPT_FILTER;
	}

	/**
	 * Startup this server.
	 *
	 * @throws IOException
	 */
	public void startup() throws IOException {
		AsynchronousServerSocketChannel serverSocketChannel = getChannelGroup().provider().openAsynchronousServerSocketChannel(channelGroup);
		serverSocketChannel = AIOUtils.applySocketOptions(serverSocketChannel, getServerSocketOptions());

		serverSocketChannel = serverSocketChannel.bind(getBindAddress(), NetworkConfig.SERVER_SOCKET_BACKLOG);
		InetSocketAddress serverSocketAddress = (InetSocketAddress) serverSocketChannel.getLocalAddress();

		this.serverSocketChannel = serverSocketChannel;
		isShutdown = false;

		acceptHandler.submit();

		log.info("Waiting for connections on {}:{}", serverSocketAddress.getHostName(), serverSocketAddress.getPort());
	}

	public void shutdown() throws IOException {
		serverSocketChannel.close();

		try {
			IOExecutorService.getInstance().awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException ignored) {
		}
	}

	public boolean isShutdown() {
		return isShutdown;
	}

	protected AsynchronousServerSocketChannel getServerSocketChannel() {
		return serverSocketChannel;
	}

	@Override
	public String toString() {
		try {
			InetSocketAddress serverSocketAddress = (InetSocketAddress) getServerSocketChannel().getLocalAddress();
			return "{NetworkThread listening at " + serverSocketAddress.getHostString() + ":" + serverSocketAddress.getPort() + "}";
		} catch (Exception ignored) {
		}
		return "{NetworkThread}";
	}
}
