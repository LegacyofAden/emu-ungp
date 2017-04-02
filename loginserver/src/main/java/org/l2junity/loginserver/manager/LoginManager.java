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
package org.l2junity.loginserver.manager;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.sql.DatabaseFactory;
import org.l2junity.core.configs.LoginServerConfig;
import org.l2junity.loginserver.db.AccountBansDAO;
import org.l2junity.loginserver.db.AccountLoginsDAO;
import org.l2junity.loginserver.db.AccountsDAO;
import org.l2junity.loginserver.db.dto.Account;
import org.l2junity.loginserver.network.client.ClientHandler;
import org.l2junity.loginserver.network.client.ConnectionState;
import org.l2junity.loginserver.network.client.send.*;
import org.skife.jdbi.v2.exceptions.DBIException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author NosBit
 */
@Slf4j
public class LoginManager {
	@Getter(lazy = true)
	private static final LoginManager instance = new LoginManager();

	private MessageDigest _passwordHashCrypt;
	private final AtomicInteger _connectionId = new AtomicInteger();

	private LoginManager() {
		try {
			_passwordHashCrypt = MessageDigest.getInstance("SHA");
		} catch (Exception e) {
			log.error("Failed initializing:", e);
		}
	}

	@SuppressWarnings("unused")
	public void tryAuthLogin(ClientHandler client, String name, String password, int otp) {
		try (AccountsDAO accountsDAO = DatabaseFactory.getInstance().getDAO(AccountsDAO.class)) {
			final String passwordHashBase64 = Base64.getEncoder().encodeToString(_passwordHashCrypt.digest(password.getBytes(StandardCharsets.UTF_8)));
			Account account = accountsDAO.findByName(name);
			if ((account == null) && LoginServerConfig.AUTO_CREATE_ACCOUNTS) {
				long accountId = accountsDAO.insert(name, passwordHashBase64);
				account = accountsDAO.findById(accountId);
				log.info("Auto created account [{}]", name);
			}

			if ((account == null) || !account.getPassword().equals(passwordHashBase64)) {
				client.close(LoginFail2.THE_USERNAME_AND_PASSWORD_DO_NOT_MATCH_PLEASE_CHECK_YOUR_ACCOUNT_INFORMATION_AND_TRY_LOGGING_IN_AGAIN);
				return;
			}

			// TODO: check OTP
			if (false) {
				client.close(new LoginOtpFail());
				return;
			}

			try (AccountBansDAO accountBansDAO = DatabaseFactory.getInstance().getDAO(AccountBansDAO.class)) {
				if (!accountBansDAO.findActiveByAccountId(account).isEmpty()) {
					client.close(BlockedAccount.YOUR_ACCOUNT_HAS_BEEN_RESTRICTED_IN_ACCORDANCE_WITH_OUR_TERMS_OF_SERVICE_DUE_TO_YOUR_CONFIRMED_ABUSE_OF_IN_GAME_SYSTEMS_RESULTING_IN_ABNORMAL_GAMEPLAY_FOR_MORE_DETAILS_PLEASE_VISIT_THE_LINEAGE_II_SUPPORT_WEBSITE_HTTPS_SUPPORT_LINEAGE2_COM);
					return;
				}
			}

			// TODO: check if account is already logged in
			if (false) {
				// TODO: kick account
				client.close(LoginFail2.ACCOUNT_IS_ALREADY_IN_USE);
				return;
			}

			try (AccountLoginsDAO accountLoginsDAO = DatabaseFactory.getInstance().getDAO(AccountLoginsDAO.class)) {
				client.setAccountLoginsId(accountLoginsDAO.insert(account, client.getInetAddress().getHostAddress()));
			}

			client.setAccount(account);
			if (LoginServerConfig.SHOW_LICENCE) {
				client.setConnectionState(ConnectionState.AUTHED_LICENCE);
				client.sendPacket(new LoginOk(client.getLoginSessionId()));
			} else {
				client.setConnectionState(ConnectionState.AUTHED_SERVER_LIST);
				client.sendPacket(new ServerList(client));
			}
		} catch (DBIException e) {
			log.warn("There was an error while logging in Name: {}", name);
			client.close(LoginFail2.SYSTEM_ERROR);
		}
	}

	/**
	 * @param serverId
	 * @param client
	 */
	public void tryServerLogin(ClientHandler client, short serverId) {
		final Account account = client.getAccount();
		client.getAccount().setLastServerId(serverId);
		try (AccountsDAO accountsDAO = DatabaseFactory.getInstance().getDAO(AccountsDAO.class)) {
			accountsDAO.updateLastServerId(account);
		}

		try (AccountLoginsDAO accountLoginsDAO = DatabaseFactory.getInstance().getDAO(AccountLoginsDAO.class)) {
			accountLoginsDAO.updateServerId(client.getAccountLoginsId(), (short) (serverId & 0xFF));
		}
	}

	public int getNextConnectionId() {
		return _connectionId.getAndIncrement();
	}
}