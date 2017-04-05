package org.l2junity.commons.network;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.network.enums.ENetBufferType;
import org.l2junity.core.configs.NetworkConfig;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.CompletionHandler;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Handles outgoing data.
 *
 * @author izen
 */
@Slf4j
class SendHandler<TClient extends Client> implements CompletionHandler<Integer, Void> {
	// TODO [izen] pending writes counter

	private enum EWriteState {
		IDLE, // Idle state. Nothing to do.
		WRITE, // Packet write in progress.
		PENDING, // Packet send in progress.
		COMPLETION // Send competed.
	}

	private final Connection<TClient> _connection;
	private final ByteBuffer _buffer;
	private final Queue<SendablePacket<TClient>> _sendQueue;
	private final AtomicReference<EWriteState> _state;

	public SendHandler(Connection<TClient> connection) {
		_connection = connection;
		_sendQueue = new ArrayDeque<>();
		_state = new AtomicReference<>(EWriteState.IDLE);

		if (NetworkConfig.SEND_BUFFER_TYPE == ENetBufferType.DIRECT) {
			_buffer = ByteBuffer.allocateDirect(NetworkConfig.SEND_BUFFER_SIZE);
		} else {
			_buffer = ByteBuffer.wrap(new byte[NetworkConfig.SEND_BUFFER_SIZE]);
		}
		_buffer.order(ByteOrder.LITTLE_ENDIAN);
	}

	private Queue<SendablePacket<TClient>> getSendQueue() {
		return _sendQueue;
	}

	private boolean writePacket(SendablePacket<TClient> packet) {
		final ICipher cipher = _connection.getCipher();
		final TClient client = _connection.getClient();
		final int origBuffLimit = _buffer.limit(); // Buffer's limit for future restore
		final int writeHeaderPos = _buffer.position(); // Here will be a packet header
		final int writeBodyPos = writeHeaderPos + NetworkConfig.OUTCOME_PACKET_HEADER_SIZE; // Here will be a packet body

		_buffer.position(writeBodyPos); // Prepare body write
		_buffer.limit(_buffer.capacity()); // Limit write to the buffers end.

		boolean succeed = false;
		try {
			succeed = packet.write(client, _buffer); // Write packet
		} catch (Exception ex) {
			log.warn("Cant write packet", ex);
		}

		if (!succeed) {
			if (NetworkConfig.LOG_FAILED_WRITE_ATTEMPT) {
				log.warn("Packet write failed.");
			}
			_buffer.position(writeHeaderPos);
			_buffer.limit(origBuffLimit);
			return false;
		}

		int writeBodyEnd = _buffer.position();
		int packetBodySize = writeBodyEnd - writeBodyPos;

		if (cipher != null) {
			_buffer.position(writeBodyPos);
			_buffer.limit(writeBodyEnd);
			succeed = cipher.encrypt(_buffer, writeBodyPos, packetBodySize);
			if (!succeed) {
				_buffer.position(writeHeaderPos);
				log.warn("Packet encrypt failed.");
			} else {
				_buffer.position(writeBodyEnd); // XXX [izen] is it necessary?
			}
		}

		// Write size
		// TODO [izen] block cipher support???
		_buffer.putShort(writeHeaderPos, (short) (packetBodySize + NetworkConfig.OUTCOME_PACKET_HEADER_SIZE));

		_buffer.limit(origBuffLimit);
		return succeed;
	}

	void sendBuffer() {
		_buffer.flip(); // Will try to write up to last packet end.

		if (_state.compareAndSet(EWriteState.WRITE, EWriteState.PENDING)) {
			submit(); // And write.
		} else {
			throw new IllegalStateException("Attempt to send not in WRITE state");
		}
	}

	private void processEnqueuedPackets(boolean sendBuffer) {
		_buffer.limit(_buffer.capacity());

		SendablePacket<TClient> enqueuedPacket;

		packetWriteLoop:
		while (_buffer.remaining() > NetworkConfig.MAX_OUTCOME_PACKET_SIZE) {
			synchronized (_sendQueue) {
				enqueuedPacket = _sendQueue.peek();
			}

			if (enqueuedPacket == null) {
				break packetWriteLoop;
			}

			if (!writePacket(enqueuedPacket)) {
				if (NetworkConfig.LOG_FAILED_WRITE_ATTEMPT) {
					log.warn("Packet {} wont be send.", enqueuedPacket);
				}
			}

			synchronized (_sendQueue) {
				if (!_connection.isClosed() && enqueuedPacket != _sendQueue.remove()) {
					throw new RuntimeException("Send queue head != dequeued packet");
				}
			}
		}

		if (sendBuffer) {
			sendBuffer();
		}
	}

	public void sendPackets(final SendablePacket<TClient>[] packets) {
		for (SendablePacket<TClient> packet : packets) {
			synchronized (_sendQueue) {
				_sendQueue.offer(packet);
			}
		}
		if (_state.compareAndSet(EWriteState.IDLE, EWriteState.WRITE)) {
			try {
				processEnqueuedPackets(true);
			} catch (Exception ex) {
				log.error("Exception while writing packets", ex);
				_connection.close();
			}
		}
	}

	protected void submit() {
		_connection.getSocketChannel().write(_buffer, null, this);
	}

	@Override
	public void completed(Integer result, Void attachment) {
		if (_connection.isClosed()) {
			return;
		}
		if (result < 0) // -1 means that the associated channel is closed.
		{
			_connection.close();
			return;
		}

		try {
			if (_state.compareAndSet(EWriteState.PENDING, EWriteState.COMPLETION)) {
				boolean haveMoreData = false;
				boolean haveMorePackets = false;

				if (_buffer.hasRemaining()) // Is data was not fully send.
				{
					_buffer.compact(); // Yep, move not send data to begin
					haveMoreData = true;
				} else {
					_buffer.clear(); // No, fully. Whe can clear the buffer
					haveMoreData = false;
				}

				synchronized (_sendQueue) {
					haveMorePackets = !_sendQueue.isEmpty();
				}

				if (haveMorePackets) {
					if (_state.compareAndSet(EWriteState.COMPLETION, EWriteState.WRITE)) {
						processEnqueuedPackets(true);
					} else {
						throw new IllegalStateException("Attempt to send packets not in COMPLETION state");
					}
				} else if (haveMoreData) {
					if (_state.compareAndSet(EWriteState.COMPLETION, EWriteState.WRITE)) {
						sendBuffer();
					} else {
						throw new IllegalStateException("Attempt to send data not in COMPLETION state");
					}
				} else {
					// And close if required
					if (_connection.isPendingClose()) {
						_state.set(EWriteState.IDLE);
						_connection.doClose();
						return;
					}
					if (!_state.compareAndSet(EWriteState.COMPLETION, EWriteState.IDLE)) {
						throw new IllegalStateException("Attempt to idle not in COMPLETION state");
					}
				}

			} else {
				throw new IllegalStateException("Attempt to completion not in PENDING state");
			}
		} catch (Exception ex) {
			log.error("Exception in send completion", ex);
			_connection.close();
		}
	}

	@Override
	public void failed(Throwable exc, Void attachment) {
		_connection.doClose();
	}

	@Override
	public String toString() {
		return "{SendHandler for " + _connection + "}";
	}
}
