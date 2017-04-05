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
import org.l2junity.commons.model.SessionInfo;
import org.l2junity.commons.sql.DatabaseFactory;
import org.l2junity.core.configs.LoginServerConfig;
import org.l2junity.loginserver.LoginServer;
import org.l2junity.loginserver.db.AccountBansDAO;
import org.l2junity.loginserver.db.AccountsDAO;
import org.l2junity.loginserver.db.dto.Account;
import org.l2junity.loginserver.network.LoginClient;
import org.l2junity.loginserver.network.LoginClientState;
import org.l2junity.loginserver.network.packets.s2c.BlockedAccount;
import org.l2junity.loginserver.network.packets.s2c.LoginFail;
import org.l2junity.loginserver.network.packets.s2c.LoginOk;
import org.l2junity.loginserver.network.packets.s2c.PlayOk;
import org.mindrot.jbcrypt.BCrypt;
import org.skife.jdbi.v2.exceptions.DBIException;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * @author NosBit
 */
@Slf4j
public class LoginManager {
	@Getter(lazy = true)
	private static final LoginManager instance = new LoginManager();

	public void tryAuthLogin(LoginClient client, String name, String password) {
		try (AccountsDAO accountsDAO = DatabaseFactory.getInstance().getDAO(AccountsDAO.class)) {
			Account account = accountsDAO.findByName(name);
			if (account == null) {
				if (LoginServerConfig.AUTO_CREATE_ACCOUNTS) {
					long accountId = accountsDAO.insert(name, BCrypt.hashpw(password, BCrypt.gensalt()));
					account = accountsDAO.findById(accountId);
					log.info("Auto created account [{}]", name);
				} else {
					client.close(LoginFail.SYSTEM_ERROR);
					return;
				}
			} else {
				if (!BCrypt.checkpw(password, account.getPassword())) {
					client.close(LoginFail.THE_USERNAME_AND_PASSWORD_DO_NOT_MATCH_PLEASE_CHECK_YOUR_ACCOUNT_INFORMATION_AND_TRY_LOGGING_IN_AGAIN);
					return;
				}

				try (AccountBansDAO accountBansDAO = DatabaseFactory.getInstance().getDAO(AccountBansDAO.class)) {
					if (!accountBansDAO.findActiveByAccountId(account).isEmpty()) {
						client.close(BlockedAccount.YOUR_ACCOUNT_HAS_BEEN_RESTRICTED_IN_ACCORDANCE_WITH_OUR_TERMS_OF_SERVICE_DUE_TO_YOUR_CONFIRMED_ABUSE_OF_IN_GAME_SYSTEMS_RESULTING_IN_ABNORMAL_GAMEPLAY_FOR_MORE_DETAILS_PLEASE_VISIT_THE_LINEAGE_II_SUPPORT_WEBSITE_HTTPS_SUPPORT_LINEAGE2_COM);
						return;
					}
				}

				if (LoginServer.getInstance().getRmi().isAccountOnServer(name)) {
					LoginServer.getInstance().getRmi().kickPlayerByAccount(name);
					client.close(LoginFail.ACCOUNT_IS_ALREADY_IN_USE);
					return;
				}
			}

			accountsDAO.updateLastIp(account, client.getHostAddress());
			accountsDAO.updateLastTimeAccess(account, new Timestamp(Instant.now().toEpochMilli()));

			client.setAccount(account);

			SessionInfo sessionInfo = SessionManager.getInstance().openSession(name);
			client.setSessionInfo(sessionInfo);

			if (client.compareAndSetState(LoginClientState.AUTHING, LoginClientState.AUTHED_LICENCE)) {
				client.sendPacket(new LoginOk(client.getSessionInfo()));
			}
		} catch (DBIException e) {
			log.warn("There was an error while logging in Name: {}", name, e);
			client.close(LoginFail.SYSTEM_ERROR);
		}
	}

	/**
	 * @param client
	 * @param serverId
	 */
	public void tryServerLogin(LoginClient client, short serverId) {
		final Account account = client.getAccount();
		client.getAccount().setLastServerId(serverId);
		try (AccountsDAO accountsDAO = DatabaseFactory.getInstance().getDAO(AccountsDAO.class)) {
			accountsDAO.updateLastServerId(account);
			client.sendPacket(new PlayOk(client.getSessionInfo().getPlayKey()));
		}
	}
}