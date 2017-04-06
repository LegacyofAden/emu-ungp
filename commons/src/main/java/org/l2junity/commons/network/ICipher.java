package org.l2junity.commons.network;

import java.nio.ByteBuffer;

/**
 * A packet body cipher
 *
 * @author izen
 */
public interface ICipher {
	/**
	 * Encrypts packets body in the buffer.
	 * After encryption the buffer position and limit must be set to actual packet data begin and size.
	 *
	 * @param buffer   a buffer, containing an packet body to encrypt
	 * @param position position, at which packet body begins
	 * @param size     size of the packet body
	 * @return true, if encrypted successfully.
	 */
	boolean encrypt(ByteBuffer buffer, int position, int size);

	/**
	 * Decrypts packets body in the buffer.
	 * After decryption the buffer position and limit must be set to actual packet data begin and size.
	 *
	 * @param buffer   a buffer, containing an encrypted packet body
	 * @param position position, at which encrypted body begins
	 * @param size     size of the encrypted body
	 * @return true, if decrypted successfully.
	 */
	boolean decrypt(ByteBuffer buffer, int position, int size);

	void setKey(byte[] key);
}
