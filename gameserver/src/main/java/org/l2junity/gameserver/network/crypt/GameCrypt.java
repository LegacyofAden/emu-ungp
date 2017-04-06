package org.l2junity.gameserver.network.crypt;

import org.l2junity.commons.network.ICipher;
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
	public boolean encrypt(ByteBuffer buffer, int position, int size) {
		if (!isEnabled) {
			isEnabled = true;
			return true;
		}

		int temp = 0;
		for (int i = 0; i < size; i++) {
			int temp2 = buffer.get(position + i) & 0xFF;
			temp = temp2 ^ outKey[i & 15] ^ temp;
			buffer.put(position + i, (byte) temp);
		}

		int old = outKey[8] & 0xff;
		old |= outKey[9] << 8 & 0xff00;
		old |= outKey[10] << 0x10 & 0xff0000;
		old |= outKey[11] << 0x18 & 0xff000000;

		old += size;

		outKey[8] = (byte) (old & 0xff);
		outKey[9] = (byte) (old >> 0x08 & 0xff);
		outKey[10] = (byte) (old >> 0x10 & 0xff);
		outKey[11] = (byte) (old >> 0x18 & 0xff);
		return true;
	}

	@Override
	public boolean decrypt(ByteBuffer buffer, int position, int size) {
		if (!isEnabled) {
			return true;
		}

		int temp = 0;
		for (int i = 0; i < size; i++) {
			int temp2 = buffer.get(position + i) & 0xFF;
			buffer.put(position + i, (byte) (temp2 ^ inKey[i & 15] ^ temp));
			temp = temp2;
		}

		int old = inKey[8] & 0xff;
		old |= inKey[9] << 8 & 0xff00;
		old |= inKey[10] << 0x10 & 0xff0000;
		old |= inKey[11] << 0x18 & 0xff000000;

		old += size;

		inKey[8] = (byte) (old & 0xff);
		inKey[9] = (byte) (old >> 0x08 & 0xff);
		inKey[10] = (byte) (old >> 0x10 & 0xff);
		inKey[11] = (byte) (old >> 0x18 & 0xff);
		return true;
	}
}
