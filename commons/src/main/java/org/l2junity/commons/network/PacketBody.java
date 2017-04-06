package org.l2junity.commons.network;

import java.nio.ByteBuffer;

/**
 * @author ANZO
 * @since 06.04.2017
 */
public class PacketBody<TClient extends Client> {
	private final TClient _client;
	private final ByteBuffer _buffer;

	public PacketBody(TClient client, ByteBuffer buffer) {
		_client = client;
		_buffer = buffer;
	}

	public TClient getClient() {
		return _client;
	}

	public void writeC(boolean val) {
		writeC(val ? 0x01 : 0x00);
	}

	public void writeC(int val) {
		_buffer.put((byte) val);
	}

	public void writeF(double val) {
		_buffer.putDouble(val);
	}

	public void writeH(int val) {
		_buffer.putShort((short) val);
	}

	public void writeD(int val) {
		_buffer.putInt(val);
	}

	public void writeQ(long val) {
		_buffer.putLong(val);
	}

	public void writeB(byte[] val) {
		_buffer.put(val);
	}

	public void writeS(CharSequence charSequence) {
		if (charSequence != null) {
			final int length = charSequence.length();
			for (int i = 0; i < length; i++) {
				_buffer.putChar(charSequence.charAt(i));
			}
		}
		_buffer.putChar('\000');
	}

	/**
	 * Writes a string with fixed length specified as [short length, char[length] data].
	 *
	 * @param value the string
	 */
	public void writeString(String value) {
		if (value != null) {
			_buffer.putShort(Short.reverseBytes((short)value.length()));
			for (int i = 0; i < value.length(); i++) {
				_buffer.putChar(Character.reverseBytes(value.charAt(i)));
			}
		} else {
			_buffer.putShort((short)0);
		}
	}

	/**
	 * Writes a float.
	 *
	 * @param value the float
	 */
	public void writeE(float value) {
		_buffer.putFloat(value);
	}

	public void writeOptionalD(int value) {
		if (value >= Short.MAX_VALUE) {
			writeH(Short.MAX_VALUE);
			writeD(value);
		} else {
			writeH(value);
		}
	}
}