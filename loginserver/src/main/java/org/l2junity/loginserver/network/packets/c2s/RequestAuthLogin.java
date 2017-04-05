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
package org.l2junity.loginserver.network.packets.c2s;

import org.l2junity.loginserver.manager.LoginManager;
import org.l2junity.loginserver.network.LoginClient;
import org.l2junity.loginserver.network.LoginClientState;
import org.l2junity.loginserver.network.packets.LoginClientPacket;
import org.l2junity.loginserver.network.packets.s2c.LoginFail;

import javax.crypto.Cipher;
import java.security.GeneralSecurityException;

/**
 * @author NosBit
 */
public class RequestAuthLogin extends LoginClientPacket {
	private byte[] _raw;
	private byte[] _raw2;
	private int _connectionId;
	private byte[] _gameGuard;

	private boolean _newAuth;

	@Override
	protected void readImpl() {
		if (getAvailableBytes() >= (128 + 128 + 4 + 16)) {
			_raw = readB(128);
			_raw2 = readB(128);
			_connectionId = readD();
			_gameGuard = readB(16);
			_newAuth = true;
		} else if (getAvailableBytes() >= (128 + 4 + 16)) {
			_raw = readB(128);
			_connectionId = readD();
			_gameGuard = readB(16);
			_newAuth = false;
		}
	}

	@Override
	protected void runImpl() {
		LoginClient client = getClient();
		if (client != null) {
			if (client.compareAndSetState(LoginClientState.AUTHED_GG, LoginClientState.AUTHING)) {
				if (_connectionId != client.getConnectionId()) {
					client.close(LoginFail.ACCESS_FAILED_PLEASE_TRY_AGAIN_LATER);
					return;
				}

				if (!client.checkGameGuardData(_gameGuard)) {
					client.close(LoginFail.ACCESS_FAILED_PLEASE_TRY_AGAIN_LATER);
					return;
				}

				byte[] decrypted = new byte[_newAuth ? 256 : 128];
				try {
					final Cipher rsaCipher = Cipher.getInstance("RSA/ECB/NoPadding");
					rsaCipher.init(Cipher.DECRYPT_MODE, client.getScrambledRSAKeyPair().getPrivateKey());
					rsaCipher.doFinal(_raw, 0, 128, decrypted, 0);
					if (_newAuth) {
						rsaCipher.doFinal(_raw2, 0, 128, decrypted, 128);
					}
				} catch (GeneralSecurityException e) {
					client.close(LoginFail.ACCESS_FAILED_PLEASE_TRY_AGAIN_LATER);
					return;
				}

				final String name;
				final String password;
				final int ncotp;
				if (_newAuth) {
					name = new String(decrypted, 0x4E, 50).trim() + new String(decrypted, 0xCE, 14).trim();
					password = new String(decrypted, 0xDC, 16).trim();
					ncotp = (decrypted[0xFC] & 0xFF) | ((decrypted[0xFD] & 0xFF) << 8) | ((decrypted[0xFE] & 0xFF) << 16) | ((decrypted[0xFF] & 0xFF) << 24);
				} else {
					name = new String(decrypted, 0x5E, 14).trim();
					password = new String(decrypted, 0x6C, 16).trim();
					ncotp = (decrypted[0x7C] & 0xFF) | ((decrypted[0x7D] & 0xFF) << 8) | ((decrypted[0x7E] & 0xFF) << 16) | ((decrypted[0x7F] & 0xFF) << 24);
				}

				LoginManager.getInstance().tryAuthLogin(client, name, password);
			}
		}
	}
}
