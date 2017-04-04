package org.l2junity.commons.network;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.idfactory.AbstractIdFactory;
import org.l2junity.commons.idfactory.DefaultIdFactory;
import org.l2junity.commons.util.AIOUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author izen
 */
@Slf4j
public class Connection<TClient extends Client> {
	private enum EConnectionState {
		OPEN,
		PENDING,
		CLOSED
	}

	private int _connectionId;
	private AsynchronousSocketChannel _socketChannel;
	private InetSocketAddress _socketAddress;
	private ReceiveHandler _receiveHandler;
	private SendHandler<TClient> _sendHandler;
	private TClient _client;
	private NetworkThread<TClient> _server;
	private AtomicReference<EConnectionState> _connectionState;

	private ICipher _cipher;

	Connection(NetworkThread<TClient> server, AsynchronousSocketChannel socketChannel, InetSocketAddress socketAddress) {
		try {
			_connectionId = DefaultIdFactory.getInstance().getNextId();
			_socketChannel = AIOUtils.applySocketOptions(socketChannel, server.getClientSocketOptions());
			_socketAddress = socketAddress;
			_receiveHandler = new ReceiveHandler<>(this);
			_sendHandler = new SendHandler<>(this);
			_connectionState = new AtomicReference<>(EConnectionState.OPEN);
			_server = server;
			_client = _server.getClientFactory().createClient(this);
		} catch (IOException e) {
			log.error("Error while creating Connection", e);
		}
	}

	public InetSocketAddress getSocketAddress() {
		return _socketAddress;
	}

	public TClient getClient() {
		return _client;
	}

	public NetworkThread<TClient> getServer() {
		return _server;
	}

	public ICipher getCipher() {
		return _cipher;
	}

	public void setCipher(ICipher cipher) {
		_cipher = cipher;
	}

	public void sendPackets(final SendablePacket<TClient>[] packets) {
		_sendHandler.sendPackets(packets);
	}

	AsynchronousSocketChannel getSocketChannel() {
		return _socketChannel;
	}

	ReceiveHandler<TClient> getReceiveHandler() {
		return _receiveHandler;
	}

	void init() {
		getReceiveHandler().submit();
		_client.onOpen();
	}

	void runReceivedPacket(ReceivablePacket<TClient> receivedPacket) {
		_server.getPacketExecutor().execute(receivedPacket);
	}

	protected void close() {
		doClose();
	}

	void doClose() {
		if (_connectionState.compareAndSet(EConnectionState.PENDING, EConnectionState.CLOSED) ||
				_connectionState.compareAndSet(EConnectionState.OPEN, EConnectionState.CLOSED)) {
			try {
				_client.onClose();
			} finally {
				AIOUtils.closeAsyncChannelSilent(getSocketChannel());
				DefaultIdFactory.getInstance().releaseId(_connectionId);
			}
		}
	}

	public void sendAndClose(final SendablePacket<TClient>[] packets) {
		if (packets == null || packets.length == 0) {
			close();
		} else {
			if (_connectionState.compareAndSet(EConnectionState.OPEN, EConnectionState.PENDING)) {
				_sendHandler.sendPackets(packets);
			}
		}
	}

	boolean isPendingClose() {
		return _connectionState.get() == EConnectionState.PENDING;
	}

	public boolean isClose() {
		switch (_connectionState.get()) {
			case PENDING:
			case CLOSED:
				return true;
		}
		return false;
	}

	public int getConnectionId() {
		return _connectionId;
	}

	protected boolean isClosed() {
		return _connectionState.get() == EConnectionState.CLOSED;
	}

	@Override
	public String toString() {
		return "{Connection from " + _socketAddress.getHostString() + ":" + _socketAddress.getPort() + "}";
	}
}
