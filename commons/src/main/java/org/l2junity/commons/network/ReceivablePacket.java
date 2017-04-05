package org.l2junity.commons.network;

import java.nio.ByteBuffer;

/**
 * The base class for all receivable packets
 *
 * @author izen
 */
public abstract class ReceivablePacket<TClient extends Client> implements Runnable {
	private TClient _client;
	private ByteBuffer _buffer;

	public TClient getClient() {
		return _client;
	}

	public void setClient(TClient client) {
		_client = client;
	}

	public ByteBuffer getBuffer() {
		return _buffer;
	}

	public void setBuffer(ByteBuffer buffer) {
		_buffer = buffer;
	}

	/**
	 * Read this packet
	 *
	 * @return true if receive succeed and run required
	 */
	protected abstract boolean read();

	/**
	 * Runs this packet after it was successfully receive
	 */
	@Override
	public abstract void run();

	protected int getAvailableBytes() {
		return getBuffer().remaining();
	}

	protected void readB(byte[] dst) {
		getBuffer().get(dst);
	}

	protected byte[] readB(int len) {
		byte[] result = new byte[len];
		getBuffer().get(result);
		return result;
	}

	protected void readB(byte[] dst, int offset, int len) {
		getBuffer().get(dst, offset, len);
	}

	protected int readC() {
		return getBuffer().get() & 0xFF;
	}

	protected int readH() {
		return getBuffer().getShort() & 0xFFFF;
	}

	protected int readD() {
		return getBuffer().getInt();
	}

	protected long readQ() {
		return getBuffer().getLong();
	}

	protected double readF() {
		return getBuffer().getDouble();
	}

	protected String readS() {
		StringBuilder sb = new StringBuilder();
		char ch;
		while ((ch = getBuffer().getChar()) != 0) {
			sb.append(ch);
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		return "{" + getClass().getSimpleName() + " from " + getClient() + "}";
	}
}
