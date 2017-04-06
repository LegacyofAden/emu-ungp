package org.l2junity.gameserver.service;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.model.AccountInfo;
import org.l2junity.commons.model.GameServerInfo;
import org.l2junity.commons.model.SessionInfo;
import org.l2junity.commons.model.enums.AgeLimit;
import org.l2junity.commons.model.enums.RegisterResult;
import org.l2junity.commons.model.enums.ServerStatus;
import org.l2junity.commons.rmi.IGameServerRMI;
import org.l2junity.commons.rmi.ILoginServerRMI;
import org.l2junity.commons.sql.DatabaseFactory;
import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.core.configs.GameserverConfig;
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.core.configs.NetworkConfig;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.GameClientState;
import org.l2junity.gameserver.network.packets.s2c.CharSelectionInfo;
import org.l2junity.gameserver.network.packets.s2c.LoginFail;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author ANZO
 * @since 03.04.2017
 */
@Slf4j
public class GameServerRMI extends UnicastRemoteObject implements IGameServerRMI {
	private static final Logger logAccounting = LoggerFactory.getLogger("accounting");

	private ILoginServerRMI connection;
	private GameServerInfo gameServerInfo;
	private transient ScheduledFuture<?> reconnectTask;

	private final Map<String, GameClient> accountsInGameServer = new ConcurrentHashMap<>();

	public GameServerRMI() throws RemoteException {
		gameServerInfo = new GameServerInfo(GameserverConfig.SERVER_ID, NetworkConfig.HOST, NetworkConfig.PORT);
		gameServerInfo.setMaxOnline(GameserverConfig.MAXIMUM_ONLINE_USERS);
		gameServerInfo.setStatus(ServerStatus.AUTO);
		gameServerInfo.setAgeLimit(AgeLimit.valueOf(GeneralConfig.SERVER_LIST_AGE));
		gameServerInfo.setServerType(GeneralConfig.SERVER_LIST_TYPE);
		connectToLoginServer();
		ThreadPool.getInstance().scheduleGeneralAtFixedRate(new LoginServerStatusWatcher(), 0, 1, TimeUnit.SECONDS);
	}

	private void connectToLoginServer() {
		try {
			Registry registry = LocateRegistry.getRegistry(GameserverConfig.GAME_SERVER_LOGIN_HOST, GameserverConfig.GAME_SERVER_LOGIN_PORT);
			connection = (ILoginServerRMI) registry.lookup("loginServer");
			RegisterResult registerResult = connection.registerGameServer(this, gameServerInfo);
			switch (registerResult) {
				case SUCCESS:
					log.info("Connected to login server successfully.");
					break;
				default:
					log.warn("Connection to login server failed. Reason: {}", registerResult.toString());
					break;
			}
		} catch (ConnectException e) {
			log.warn("Loginserver isn't available. Make sure it's up and running.");
		} catch (Exception e) {
			log.error("Connection to login server failed", e);
		} finally {
			reconnectTask = null;
		}
	}

	@Override
	public AccountInfo getAccountInfo(String account) {
		int chars = 0;
		List<Long> charToDel = new ArrayList<>();
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps = con.prepareStatement("SELECT deletetime FROM characters WHERE account_name=?")) {
			ps.setString(1, account);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					chars++;
					long delTime = rs.getLong("deletetime");
					if (delTime != 0) {
						charToDel.add(delTime);
					}
				}
			}
		} catch (SQLException e) {
			log.warn("Exception: getCharsOnServer: " + e.getMessage(), e);
		}

		return new AccountInfo(account, chars, charToDel);
	}

	@Override
	public boolean isAccountOnServer(String account) throws RemoteException {
		return accountsInGameServer.containsKey(account);
	}

	@Override
	public void kickPlayerByAccount(String account) throws RemoteException {
		if (accountsInGameServer.containsKey(account)) {
			GameClient client = accountsInGameServer.get(account);
			client.close(SystemMessage.getSystemMessage(SystemMessageId.YOU_ARE_LOGGED_IN_TO_TWO_PLACES_IF_YOU_SUSPECT_ACCOUNT_THEFT_WE_RECOMMEND_CHANGING_YOUR_PASSWORD_SCANNING_YOUR_COMPUTER_FOR_VIRUSES_AND_USING_AN_ANTI_VIRUS_SOFTWARE));
			logAccounting.info("Kicked by login, {}", client);
			removeAccountInGame(account);
		}
	}

	public boolean tryAddGameClient(GameClient client, SessionInfo sessionInfo) {
		if (checkSession(sessionInfo)) {
			client.setAccountName(sessionInfo.getAccountName());
			if (addAccountInGame(sessionInfo.getAccountName(), client)) {
				if (client.compareAndSetState(GameClientState.CONNECTED, GameClientState.AUTHENTICATED)) {
					client.setSessionInfo(sessionInfo);
					client.sendPacket(LoginFail.LOGIN_SUCCESS);

					final CharSelectionInfo charSelectionInfo = new CharSelectionInfo(sessionInfo);
					client.sendPacket(charSelectionInfo);
					client.setCharSelectionInfo(charSelectionInfo.getCharInfo());
				}
				return true;
			}
		}
		return false;
	}

	public boolean addAccountInGame(String account, GameClient client) {
		return accountsInGameServer.putIfAbsent(account, client) == null;
	}

	public void removeAccountInGame(String account) {
		accountsInGameServer.remove(account);
	}

	public void changePassword(Player player, String oldPass, String newPass) {
		try {
			connection.changePassword(player.getAccountName(), oldPass, newPass);
		} catch (RemoteException e) {
			log.error("Error while changePassword", e);
			onConnectionLost();
		}
	}

	public void changeAccessLevel(String accountName, int accessLevel) {
		try {
			connection.changeAccessLevel(accountName, accessLevel);
		} catch (RemoteException e) {
			log.error("Error while changeAccessLevel", e);
			onConnectionLost();
		}
	}

	public boolean checkSession(SessionInfo sessionInfo) {
		try {
			return connection.checkSession(sessionInfo);
		} catch (RemoteException e) {
			log.error("Error while checkSession", e);
			onConnectionLost();
		}
		return false;
	}

	private void sendUpdate() {
		try {
			connection.updateGameServer(this, gameServerInfo);
		} catch (RemoteException e) {
			log.error("Error while updating game server status", e);
			onConnectionLost();
		}
	}

	@Override
	public boolean testConnection() throws RemoteException {
		return false;
	}

	public GameClient getClient(String account) {
		return accountsInGameServer.get(account);
	}

	public void setServerAgeLimit(AgeLimit ageLimit) {
		gameServerInfo.setAgeLimit(ageLimit);
	}

	public void setServerType(int serverType) {
		gameServerInfo.setServerType(serverType);
		sendUpdate();
	}

	public void setServerStatus(ServerStatus status) {
		gameServerInfo.setStatus(status);
		sendUpdate();
	}

	public ServerStatus getServerStatus() {
		return gameServerInfo.getStatus();
	}

	public void setCurrentOnline(int currentOnline) {
		gameServerInfo.setCurrentOnline(currentOnline);
		sendUpdate();
	}

	public void setMaxOnline(int maxOnline) {
		gameServerInfo.setMaxOnline(maxOnline);
		sendUpdate();
	}

	public int getMaxOnline() {
		return gameServerInfo.getMaxOnline();
	}

	private void onConnectionLost() {
		if (reconnectTask != null)
			return;

		log.info("Connection with login server lost.");
		connection = null;

		reconnectTask = ThreadPool.getInstance().scheduleGeneral(() -> {
			log.info("Reconnecting to login server...");
			connectToLoginServer();
		}, 2, TimeUnit.SECONDS);
	}

	private class LoginServerStatusWatcher implements Runnable {
		@Override
		public void run() {
			try {
				connection.testConnection();
			} catch (Exception e) {
				if (reconnectTask == null) {
					onConnectionLost();
				}
			}
		}
	}
}