package org.l2junity.commons.network;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.network.enums.ENetBufferType;
import org.l2junity.core.configs.NetworkConfig;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.CompletionHandler;

/**
 * Handles incoming data.
 *
 * @author izen
 */
@Slf4j
class ReceiveHandler<TClient extends Client> implements CompletionHandler<Integer, Void> {
	private final Connection<TClient> _connection;
	private final ByteBuffer _buffer;

	ReceiveHandler(Connection<TClient> connection) {
		_connection = connection;

		if (NetworkConfig.RECV_BUFFER_TYPE == ENetBufferType.DIRECT) {
			_buffer = ByteBuffer.allocateDirect(NetworkConfig.RECV_BUFFER_SIZE);
		} else {
			_buffer = ByteBuffer.wrap(new byte[NetworkConfig.RECV_BUFFER_SIZE]);
		}
		_buffer.order(ByteOrder.LITTLE_ENDIAN);
	}

	private int getPacketSize() {
		return 0x7fff & _buffer.getShort(_buffer.position());
	}

	protected void submit() {
		_connection.getSocketChannel().read(_buffer, null, this);
	}

	private void readPacket(final int packetSize) {
		assert packetSize >= NetworkConfig.INCOME_PACKET_HEADER_SIZE;

		if (packetSize > NetworkConfig.MAX_INCOME_PACKET_SIZE) {
			log.warn("Received packet size exceeds the limit (size: {} limit: {})", packetSize, NetworkConfig.MAX_INCOME_PACKET_SIZE);
			_connection.close();
			return;
		}

		final int packetHeaderPos = _buffer.position(); // Header position
		final int origBuffLimit = _buffer.limit(); // Buffer's limit for future restore
		final int packetBodyPos = packetHeaderPos + NetworkConfig.INCOME_PACKET_HEADER_SIZE; // Packet body position
		final int packetBodySize = packetSize - NetworkConfig.INCOME_PACKET_HEADER_SIZE; // Packet body size

		if (packetSize == NetworkConfig.INCOME_PACKET_HEADER_SIZE) {
			log.warn("Received packet with empty body");
			_buffer.position(packetHeaderPos + NetworkConfig.INCOME_PACKET_HEADER_SIZE);
			return;
		}

		try {
			final TClient client = _connection.getClient();
			final IPacketHandler<TClient> packetHandler = _connection.getServer().getPacketHandler();
			_buffer.limit(packetBodyPos + packetBodySize); // Limits to the packet end
			_buffer.position(packetBodyPos); // To packet body

			boolean createPacket = true;
			final ICipher cipher = _connection.getCipher();
			if (cipher != null) {
				createPacket = cipher.decrypt(_buffer, packetBodyPos, packetBodySize); // Decrypt whole body
			}

			if (createPacket) {
				_buffer.limit(packetBodyPos + packetBodySize); // Limit for packet read
				_buffer.position(packetBodyPos); // Restore for packet read

				ReceivablePacket<TClient> receivedPacket = packetHandler.handleReceivedPacket(_buffer, client);
				if (receivedPacket != null) {
					receivedPacket.setClient(client);
					receivedPacket.setBuffer(_buffer);

					boolean readSuccess = receivedPacket.read();

					receivedPacket.setClient(client);
					receivedPacket.setBuffer(null);

					if (readSuccess) {
						_connection.runReceivedPacket(receivedPacket);
					} else {
						log.warn("Created packet has not been read.");
					}
				}
			}
		} catch (Exception ex) {
			log.error("Packed read failed: connectionIsClosed={} packetBodyPos={} packetBodySize={} _bufferPosition={} _bufferLimit={} _bufferOrigLimit={}",
					_connection.isClosed(), packetBodyPos, packetBodySize, _buffer.position(), _buffer.limit(), origBuffLimit, ex);
			_connection.close();
		} finally {
			if (!_connection.isClosed()) {
				_buffer.limit(origBuffLimit); // Restore original limit
				_buffer.position(packetBodyPos + packetBodySize); // Prepare for the next packet
			}
		}
	}

	@Override
	public void completed(Integer result, Void attachment) {
		final int received = result;
		try {
			// -1 means that the associated channel is closed.
			if (received < 0) {
				_connection.close();
				return;
			}

			_buffer.flip(); // Prepare for reading packets. The first packet be at position 0
			for (int packetSize, remaining = _buffer.remaining();
				 remaining > NetworkConfig.INCOME_PACKET_HEADER_SIZE &&  // Is enough space for the packet header
						 remaining >= (packetSize = getPacketSize()); // and body
				 remaining = _buffer.remaining()) {
				readPacket(packetSize);
			}

			// Not all packets read e.g only part of packets received.
			if (_buffer.hasRemaining()) {
				// Either move received packet part to beginning
				_buffer.compact();
			} else {
				// or clear if all packets read fully.
				_buffer.clear();
			}

			if (!_connection.isClose()) {
				// and receive next data.
				submit();
			}
		} catch (Exception ex) {
			log.error("Exception while processing received data", ex);
			_connection.close();
		}
	}

	@Override
	public void failed(Throwable exc, Void attachment) {
		_connection.close();
	}

	@Override
	public String toString() {
		return "{ReceiveHandler for " + _connection + "}";
	}
}
