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
package org.l2junity.gameserver.security;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.gameserver.data.xml.impl.SecondaryAuthData;
import org.l2junity.gameserver.model.variables.AccountVariables;
import org.l2junity.gameserver.network.Disconnection;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.s2c.Ex2ndPasswordAck;
import org.l2junity.gameserver.network.packets.s2c.Ex2ndPasswordCheck;
import org.l2junity.gameserver.network.packets.s2c.Ex2ndPasswordVerify;
import org.l2junity.gameserver.util.Util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author mrTJO, UnAfraid
 */
@Slf4j
public class SecondaryPasswordAuth {
	private static final String VAR_PWD = "secauth_pwd";
	private static final String VAR_WTE = "secauth_wte";

	private final GameClient gameClient;
	private final AccountVariables vars;
	private boolean isAuthed;

	public SecondaryPasswordAuth(GameClient gameClient) {
		this.gameClient = gameClient;
		vars = new AccountVariables(gameClient.getAccountName());
	}

	public boolean savePassword(String password) {
		if (passwordExist()) {
			log.warn("{} forced savePassword", gameClient.getAccountName());
			Disconnection.of(gameClient).defaultSequence(false);
			return false;
		}

		if (!validatePassword(password)) {
			gameClient.sendPacket(new Ex2ndPasswordAck(0, Ex2ndPasswordAck.WRONG_PATTERN));
			return false;
		}

		password = cryptPassword(password);

		vars.set(VAR_PWD, password);
		vars.storeMe();
		return true;
	}

	public boolean insertWrongAttempt(int attempts) {
		vars.set(VAR_WTE, attempts);
		vars.storeMe();
		return true;
	}

	public boolean changePassword(String oldPassword, String newPassword) {
		if (!passwordExist()) {
			log.warn("{} forced changePassword", gameClient.getAccountName());
			Disconnection.of(gameClient).defaultSequence(false);
			return false;
		}

		if (!checkPassword(oldPassword, true)) {
			return false;
		}

		if (!validatePassword(newPassword)) {
			gameClient.sendPacket(new Ex2ndPasswordAck(2, Ex2ndPasswordAck.WRONG_PATTERN));
			return false;
		}

		newPassword = cryptPassword(newPassword);

		vars.set(VAR_PWD, newPassword);
		vars.storeMe();
		isAuthed = false;
		return true;
	}

	public boolean checkPassword(String password, boolean skipAuth) {
		password = cryptPassword(password);

		final String currentPassword = vars.getString(VAR_PWD, null);
		int wrongAttempts = vars.getInt(VAR_WTE, 0);
		if (!password.equals(currentPassword)) {
			wrongAttempts++;
			if (wrongAttempts < SecondaryAuthData.getInstance().getMaxAttempts()) {
				gameClient.sendPacket(new Ex2ndPasswordVerify(Ex2ndPasswordVerify.PASSWORD_WRONG, wrongAttempts));
				insertWrongAttempt(wrongAttempts);
			} else {
				// TODO: RMI
				//LoginServerThread.getInstance().sendTempBan(_activeClient.getAccountName(), _activeClient.getIP(), SecondaryAuthData.getInstance().getBanTime());
				//LoginServerThread.getInstance().sendMail(_activeClient.getAccountName(), "SATempBan", _activeClient.getIP(), Integer.toString(SecondaryAuthData.getInstance().getMaxAttempts()), Long.toString(SecondaryAuthData.getInstance().getBanTime()), SecondaryAuthData.getInstance().getRecoveryLink());
				log.warn("{} - ({}) has inputted the wrong password {} times in row.", gameClient.getAccountName(), gameClient.getIP(), wrongAttempts);
				insertWrongAttempt(0);
				gameClient.close(new Ex2ndPasswordVerify(Ex2ndPasswordVerify.PASSWORD_BAN, SecondaryAuthData.getInstance().getMaxAttempts()));
			}
			return false;
		}
		if (!skipAuth) {
			isAuthed = true;
			gameClient.sendPacket(new Ex2ndPasswordVerify(Ex2ndPasswordVerify.PASSWORD_OK, wrongAttempts));
		}
		insertWrongAttempt(0);
		return true;
	}

	public boolean passwordExist() {
		final String password = vars.getString(VAR_PWD, null);
		return (password != null) && !password.isEmpty();
	}

	public void openDialog() {
		if (passwordExist()) {
			gameClient.sendPacket(new Ex2ndPasswordCheck(Ex2ndPasswordCheck.PASSWORD_PROMPT));
		} else {
			gameClient.sendPacket(new Ex2ndPasswordCheck(Ex2ndPasswordCheck.PASSWORD_NEW));
		}
	}

	public boolean isAuthed() {
		return isAuthed;
	}

	private String cryptPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			byte[] raw = password.getBytes("UTF-8");
			byte[] hash = md.digest(raw);
			return Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException e) {
			log.error("Unsupported Algorythm", e);
		} catch (UnsupportedEncodingException e) {
			log.error("Unsupported Encoding", e);
		}
		return null;
	}

	private boolean validatePassword(String password) {
		if (!Util.isDigit(password)) {
			return false;
		}

		if ((password.length() < 6) || (password.length() > 8)) {
			return false;
		}

		return !SecondaryAuthData.getInstance().isForbiddenPassword(password);
	}
}