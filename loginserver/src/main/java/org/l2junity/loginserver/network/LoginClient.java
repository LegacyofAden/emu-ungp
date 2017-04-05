package org.l2junity.loginserver.network;

import lombok.Getter;
import lombok.Setter;
import org.l2junity.commons.model.SessionInfo;
import org.l2junity.commons.network.Client;
import org.l2junity.commons.network.Connection;
import org.l2junity.loginserver.db.dto.Account;
import org.l2junity.loginserver.manager.KeyManager;
import org.l2junity.loginserver.network.crypt.LoginCrypt;
import org.l2junity.loginserver.network.crypt.ScrambledRSAKeyPair;
import org.l2junity.loginserver.network.packets.LoginServerPacket;
import org.l2junity.loginserver.network.packets.s2c.Init;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author ANZO
 * @since 05.04.2017
 */
public class LoginClient extends Client<LoginClient> {
	private final AtomicReference<LoginClientState> state = new AtomicReference<>(LoginClientState.CONNECTED);

	@Getter
	private final SecretKey blowfishKey;
	@Getter
	private final ScrambledRSAKeyPair scrambledRSAKeyPair;

	@Getter
	@Setter
	private Account account;

	@Getter
	@Setter
	private SessionInfo sessionInfo;

	private byte[] gameGuardData;

	public LoginClient(Connection<LoginClient> connection) {
		super(connection);
		blowfishKey = KeyManager.getInstance().generateBlowfishKey();
		scrambledRSAKeyPair = KeyManager.getInstance().getRandomScrambledRSAKeyPair();
		getConnection().setCipher(new LoginCrypt(blowfishKey));
	}

	public boolean compareAndSetState(LoginClientState compare, LoginClientState set) {
		return state.compareAndSet(compare, set);
	}

	public LoginClientState getState() {
		return state.get();
	}

	@Override
	protected void onOpen() {
		sendPacket(new Init(this));
	}

	public boolean checkGameGuardData(byte[] data) {
		return Arrays.equals(gameGuardData, data);
	}

	public void setGameGuardData(byte[] data) {
		gameGuardData = data;
	}

	public void sendPacket(LoginServerPacket packet) {
		if (!isConnected() || packet == null) {
			return;
		}
		getConnection().sendPackets(new LoginServerPacket[]{packet});
	}

	public String getHostAddress() {
		if (isConnected()) {
			return getConnection().getSocketAddress().getAddress().getHostAddress();
		}
		return "not connected";
	}

	public void closeForce() {
		state.set(LoginClientState.CLOSED);
		getConnection().sendAndClose(null);
	}

	public void close(LoginServerPacket packet) {
		if (!isConnected()) {
			return;
		}
		state.set(LoginClientState.CLOSED);
		getConnection().sendAndClose(new LoginServerPacket[]{packet});
	}
}
