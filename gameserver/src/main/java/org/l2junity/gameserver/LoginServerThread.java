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
package org.l2junity.gameserver;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.sql.DatabaseFactory;
import org.l2junity.commons.util.HexUtil;
import org.l2junity.core.configs.GameserverConfig;
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.core.configs.NetworkConfig;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.model.World;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.network.client.ConnectionState;
import org.l2junity.gameserver.network.client.L2GameClient;
import org.l2junity.gameserver.network.client.send.CharSelectionInfo;
import org.l2junity.gameserver.network.client.send.LoginFail;
import org.l2junity.gameserver.network.client.send.SystemMessage;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;
import org.l2junity.gameserver.network.gameserverpackets.*;
import org.l2junity.gameserver.network.loginserverpackets.*;
import org.l2junity.util.crypt.NewCrypt;
import org.l2junity.util.network.BaseSendablePacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAKeyGenParameterSpec;
import java.security.spec.RSAPublicKeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
// TODO: Rework to RMI
public class LoginServerThread extends Thread {
	@Getter(lazy = true)
	private static final LoginServerThread instance = new LoginServerThread();
	private final String _hostname;
	private final int _port;
	private Socket _loginSocket;
	private OutputStream _out;

	/**
	 * The BlowFish engine used to encrypt packets<br>
	 * It is first initialized with a unified key:<br>
	 * "_;v.]05-31!|+-%xT!^[$\00"<br>
	 * <br>
	 * and then after handshake, with a new key sent by<br>
	 * login server during the handshake. This new key is stored<br>
	 * in blowfishKey
	 */
	private NewCrypt _blowfish;
	private final Set<WaitingClient> _waitingClients = ConcurrentHashMap.newKeySet();

	private int _serverId;

	/**
	 * Instantiates a new login server thread.
	 */
	protected LoginServerThread() {
		super("LoginServerThread");
		_hostname = GameserverConfig.GAME_SERVER_LOGIN_HOST;
		_port = GameserverConfig.GAME_SERVER_LOGIN_PORT;
	}

	@Override
	public void run() {
		while (!isInterrupted()) {
			int lengthHi = 0;
			int lengthLo = 0;
			int length = 0;
			boolean checksumOk = false;
			try {
				// Connection
				log.info("Connecting to login on {}:{}", _hostname, _port);
				_loginSocket = new Socket(_hostname, _port);
				InputStream in = _loginSocket.getInputStream();
				_out = new BufferedOutputStream(_loginSocket.getOutputStream());

				// init Blowfish
				final byte[] blowfishKey = HexUtil.generateHex(40);
				_blowfish = new NewCrypt("_;v.]05-31!|+-%xT!^[$\00");
				while (!isInterrupted()) {
					lengthLo = in.read();
					lengthHi = in.read();
					length = (lengthHi * 256) + lengthLo;

					if (lengthHi < 0) {
						log.debug("Login terminated the connection.");
						break;
					}

					byte[] incoming = new byte[length - 2];

					int receivedBytes = 0;
					int newBytes = 0;
					int left = length - 2;
					while ((newBytes != -1) && (receivedBytes < (length - 2))) {
						newBytes = in.read(incoming, receivedBytes, left);
						receivedBytes = receivedBytes + newBytes;
						left -= newBytes;
					}

					if (receivedBytes != (length - 2)) {
						log.warn("Incomplete Packet is sent to the server, closing connection.(LS)");
						break;
					}

					// decrypt if we have a key
					_blowfish.decrypt(incoming, 0, incoming.length);
					checksumOk = NewCrypt.verifyChecksum(incoming);

					if (!checksumOk) {
						log.warn("Incorrect packet checksum, ignoring packet (LS)");
						break;
					}

					int packetType = incoming[0] & 0xff;
					switch (packetType) {
						case 0x03:
							PlayerAuthResponse par = new PlayerAuthResponse(incoming);
							String account = par.getAccount();
							WaitingClient wcToRemove = null;
							synchronized (_waitingClients) {
								for (WaitingClient wc : _waitingClients) {
									if (wc.account.equals(account)) {
										wcToRemove = wc;
									}
								}
							}
							if (wcToRemove != null) {
								// TODO: RMI
								if (par.isAuthed()) {
									PlayerInGame pig = new PlayerInGame(par.getAccount());
									sendPacket(pig);
									wcToRemove.gameClient.setConnectionState(ConnectionState.AUTHENTICATED);
									wcToRemove.gameClient.setSessionId(wcToRemove.session);
									wcToRemove.gameClient.sendPacket(LoginFail.LOGIN_SUCCESS);
									final CharSelectionInfo cl = new CharSelectionInfo(wcToRemove.account, wcToRemove.gameClient.getSessionId().playOkID1);
									wcToRemove.gameClient.sendPacket(cl);
									wcToRemove.gameClient.setCharSelection(cl.getCharInfo());
								} else {
									log.warn("Session key is not correct. Closing connection for account {}", wcToRemove.account);
									// wcToRemove.gameClient.getConnection().sendPacket(new LoginFail(LoginFail.SYSTEM_ERROR_LOGIN_LATER));
									wcToRemove.gameClient.close(new LoginFail(LoginFail.SYSTEM_ERROR_LOGIN_LATER));
									//_accountsInGameServer.remove(wcToRemove.account);
								}
								_waitingClients.remove(wcToRemove);
							}
							break;
					}
				}
			} catch (UnknownHostException e) {
				log.warn("", e);
			} catch (SocketException e) {
				log.warn("LoginServer not avaible, trying to reconnect...");
			} catch (IOException e) {
				log.warn("Disconnected from Login, Trying to reconnect: ", e);
			} finally {
				try {
					_loginSocket.close();
					if (isInterrupted()) {
						return;
					}
				} catch (Exception e) {
				}
			}

			try {
				Thread.sleep(5000); // 5 seconds tempo.
			} catch (InterruptedException e) {
				return; // never swallow an interrupt!
			}
		}
	}

	/**
	 * Adds the waiting client and send request.
	 *
	 * @param acc    the account
	 * @param client the game client
	 * @param key    the session key
	 */
	public void addWaitingClientAndSendRequest(String acc, L2GameClient client, SessionKey key) {
		WaitingClient wc = new WaitingClient(acc, client, key);
		synchronized (_waitingClients) {
			_waitingClients.add(wc);
		}
		PlayerAuthRequest par = new PlayerAuthRequest(acc, key);
		try {
			sendPacket(par);
		} catch (IOException e) {
			log.warn("Error while sending player auth request");
		}
	}

	/**
	 * Removes the waiting client.
	 *
	 * @param client the client
	 */
	public void removeWaitingClient(L2GameClient client) {
		WaitingClient toRemove = null;
		synchronized (_waitingClients) {
			for (WaitingClient c : _waitingClients) {
				if (c.gameClient == client) {
					toRemove = c;
				}
			}
			if (toRemove != null) {
				_waitingClients.remove(toRemove);
			}
		}
	}

	/**
	 * Send packet.
	 *
	 * @param sl the sendable packet
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void sendPacket(BaseSendablePacket sl) throws IOException {
		byte[] data = sl.getContent();
		NewCrypt.appendChecksum(data);
		_blowfish.crypt(data, 0, data.length);

		int len = data.length + 2;
		synchronized (_out) // avoids tow threads writing in the mean time
		{
			_out.write(len & 0xff);
			_out.write((len >> 8) & 0xff);
			_out.write(data);
			_out.flush();
		}
	}

	public static class SessionKey {
		public int playOkID1;
		public int playOkID2;
		public int loginOkID1;
		public int loginOkID2;

		/**
		 * Instantiates a new session key.
		 *
		 * @param loginOK1 the login o k1
		 * @param loginOK2 the login o k2
		 * @param playOK1  the play o k1
		 * @param playOK2  the play o k2
		 */
		public SessionKey(int loginOK1, int loginOK2, int playOK1, int playOK2) {
			playOkID1 = playOK1;
			playOkID2 = playOK2;
			loginOkID1 = loginOK1;
			loginOkID2 = loginOK2;
		}

		@Override
		public String toString() {
			return "PlayOk: " + playOkID1 + " " + playOkID2 + " LoginOk:" + loginOkID1 + " " + loginOkID2;
		}
	}

	private static class WaitingClient {
		public String account;
		public L2GameClient gameClient;
		public SessionKey session;

		/**
		 * Instantiates a new waiting client.
		 *
		 * @param acc    the acc
		 * @param client the client
		 * @param key    the key
		 */
		public WaitingClient(String acc, L2GameClient client, SessionKey key) {
			account = acc;
			gameClient = client;
			session = key;
		}
	}
}
