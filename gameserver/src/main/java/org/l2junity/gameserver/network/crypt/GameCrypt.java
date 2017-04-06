package org.l2junity.gameserver.network.crypt;

import org.l2junity.commons.network.ICipher;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.server.OnPacketReceived;
import org.l2junity.gameserver.model.events.impl.server.OnPacketSent;
import org.l2junity.gameserver.network.GameClient;

import java.nio.ByteBuffer;

/**
 * @author ANZO
 */
public class GameCrypt implements ICipher {
	private final GameClient client;
	private final byte[] inKey = new byte[16];
	private final byte[] outKey = new byte[16];
	private boolean isEnabled;

	public GameCrypt(GameClient client) {
		this.client = client;
	}

	@Override
	public void setKey(byte[] key) {
		System.arraycopy(key, 0, inKey, 0, 16);
		System.arraycopy(key, 0, outKey, 0, 16);
	}

	@Override
	public boolean encrypt(ByteBuffer buf, int position, int size) {
		if (!isEnabled) {
			isEnabled = true;
			onPacketSent(buf);
			return true;
		}

		onPacketSent(buf);

		int a = 0;
		while (buf.hasRemaining()) {
			final int b = buf.get() & 0xFF;
			a = b ^ outKey[(buf.position() - 1) & 15] ^ a;
			buf.put(buf.position() - 1, (byte)a);
		}

		shiftKey(outKey, buf.position());
		return true;
	}

	@Override
	public boolean decrypt(ByteBuffer buf, int position, int size) {
		if (!isEnabled) {
			onPacketReceive(buf);
			return true;
		}

		int a = 0;
		while (buf.hasRemaining()) {
			final int b = buf.get() & 0xFF;
			buf.put(buf.position() - 1, (byte)(b ^ inKey[(buf.position() - 1) & 15] ^ a));
			a = b;
		}

		shiftKey(inKey, buf.position());

		onPacketReceive(buf);
		return true;
	}

	private void onPacketSent(ByteBuffer buf) {
		byte[] data = new byte[buf.position()];
		buf.get(data, 0, buf.position());
		EventDispatcher.getInstance().notifyEvent(new OnPacketSent(client, data));
	}

	private void onPacketReceive(ByteBuffer buf) {
		byte[] data = new byte[buf.position()];
		buf.get(data, 0, buf.position());
		EventDispatcher.getInstance().notifyEvent(new OnPacketReceived(client, data));
	}

	private void shiftKey(byte[] key, int size) {
		int old = key[8] & 0xff;
		old |= (key[9] << 8) & 0xff00;
		old |= (key[10] << 0x10) & 0xff0000;
		old |= (key[11] << 0x18) & 0xff000000;

		old += size;

		key[8] = (byte) (old & 0xff);
		key[9] = (byte) ((old >> 0x08) & 0xff);
		key[10] = (byte) ((old >> 0x10) & 0xff);
		key[11] = (byte) ((old >> 0x18) & 0xff);
	}
}
