package org.l2junity.commons.network;

import java.nio.ByteBuffer;

/**
 * The base class for all sendable packets
 *
 * @author izen
 */
public abstract class SendablePacket<TClient extends Client> {
	protected abstract boolean write(TClient client, ByteBuffer buffer);

	protected class PacketBody {
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
	}
}
