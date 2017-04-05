/*
 * Copyright (C) 2004-2015 L2J Unity
 *
 * This file is part of L2J Unity.
 *
 * L2J Unity is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * L2J Unity is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.l2junity.loginserver.network.crypt;

import org.l2junity.commons.network.ICipher;
import org.l2junity.commons.util.Rnd;

import javax.crypto.SecretKey;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author ANZO
 */
public class LoginCrypt implements ICipher {
	private static final byte[] STATIC_BLOWFISH_KEY =
			{
					(byte) 0x6b,
					(byte) 0x60,
					(byte) 0xcb,
					(byte) 0x5b,
					(byte) 0x82,
					(byte) 0xce,
					(byte) 0x90,
					(byte) 0xb1,
					(byte) 0xcc,
					(byte) 0x2b,
					(byte) 0x6c,
					(byte) 0x55,
					(byte) 0x6c,
					(byte) 0x6c,
					(byte) 0x6c,
					(byte) 0x6c
			};

	private static final BlowfishEngine STATIC_BLOWFISH_ENGINE = new BlowfishEngine();

	static {
		STATIC_BLOWFISH_ENGINE.init(STATIC_BLOWFISH_KEY);
	}

	private final BlowfishEngine _blowfishEngine = new BlowfishEngine();
	private boolean isStaticCrypt = true;

	public LoginCrypt(SecretKey blowfishKey) {
		_blowfishEngine.init(blowfishKey.getEncoded());
	}

	@Override
	public boolean encrypt(ByteBuffer buf, int position, int size) {
		// TODO: Pleazzze, rework this to configurable in SendHandler
		int addSize = (isStaticCrypt ? 8 : 4) + (8 - (buf.remaining() % 8));
		ByteBuffer byteBuffer = ByteBuffer.allocate(size + addSize);
		byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

		byteBuffer.position(addSize);
		byteBuffer.put(buf);
		buf = byteBuffer;
		buf.position(position + addSize);

		if (isStaticCrypt) {
			isStaticCrypt = false;

			int key = Rnd.nextInt();
			skipBytes(buf, 4); // The first 4 bytes are ignored
			for (int pos = buf.position(); pos < buf.remaining() - 8; pos += 4) {
				int data = buf.getInt();
				key += data;
				data ^= key;
				buf.putInt(buf.position() - 4, data);
			}
			buf.putInt(buf.position(), key);

			buf.position(position);

			final byte[] block = new byte[8];
			while (buf.remaining() >= 8) {
				buf.get(block);
				STATIC_BLOWFISH_ENGINE.encryptBlock(block, 0);
				buf.position(buf.position() - block.length);
				buf.put(block);
			}
		} else {
			// Calculating checksum
			int checksum = 0;
			while (buf.remaining() >= 8) {
				checksum ^= buf.getInt();
			}

			// Writing checksum
			buf.rewind();
			buf.putInt(buf.position(), checksum);

			// Encrypting body
			final byte[] block = new byte[8];
			while (buf.remaining() >= 8) {
				buf.get(block);
				_blowfishEngine.encryptBlock(block, 0);
				buf.position(buf.position() - block.length);
				buf.put(block);
			}
		}
		return true;
	}

	@Override
	public boolean decrypt(ByteBuffer buf, int position, int size) {
		// Packet size must be multiple of 8
		if ((buf.remaining() % 8) != 0) {
			buf.clear();
			return false;
		}

		final byte[] block = new byte[8];
		while (buf.remaining() >= 8) {
			buf.get(block);
			_blowfishEngine.decryptBlock(block, 0);
			buf.position(buf.position() - block.length);
			buf.put(block);
		}
		// TODO: Verify checksum
		return true;
	}

	private void skipBytes(ByteBuffer buf, int size) {
		byte[] bytes = new byte[size];
		buf.get(bytes);
	}
}
