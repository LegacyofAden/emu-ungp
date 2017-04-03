package org.l2junity.loginserver.manager;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.model.GameServerInfo;
import org.l2junity.commons.model.enums.AgeLimit;
import org.l2junity.commons.model.enums.RegisterResult;
import org.l2junity.commons.model.enums.ServerStatus;
import org.l2junity.commons.model.enums.ServerType;
import org.l2junity.commons.rmi.IGameServerRMI;
import org.l2junity.commons.rmi.ILoginServerRMI;
import org.l2junity.commons.rmi.SocketFactory;
import org.l2junity.commons.sql.DatabaseFactory;
import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.commons.util.IXmlReader;
import org.l2junity.commons.util.XmlReaderException;
import org.l2junity.core.configs.LoginServerConfig;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.loginserver.db.AccountsDAO;
import org.l2junity.loginserver.db.dto.Account;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author ANZO
 * @since 03.04.2017
 */
@Slf4j
@StartupComponent("Network")
public class LoginServerRMI extends UnicastRemoteObject implements ILoginServerRMI, IXmlReader {
	private final Map<Short, GameServerInfo> gameservers = new HashMap<>();

	private LoginServerRMI() throws RemoteException {
		try {
			parseFile(Paths.get("configs/xml/GameServers.xml"));
		}
		catch (XmlReaderException | IOException e) {
			log.error("Error loading game servers.", e);
		}
		finally {
			log.info("Loaded {} active game servers.", gameservers.values().stream().filter(GameServerInfo::isShowing).count());
			try {
				Registry registry = LocateRegistry.createRegistry(LoginServerConfig.GAME_SERVER_LOGIN_PORT, new SocketFactory(), new SocketFactory());
				registry.bind("loginServer", this);
				log.info("Login server listening for gameserver's connections at port {}", LoginServerConfig.GAME_SERVER_LOGIN_PORT);
			} catch (Exception e) {
				log.error("Error while initialization RMI server!", e);
			} finally {
				ThreadPool.getInstance().scheduleGeneralAtFixedRate(new GameServersStatusWatcher(), 0, 1, TimeUnit.SECONDS);
			}
		}
	}

	@Override
	public boolean testConnection() {
		return true;
	}

	@Override
	public RegisterResult registerGameServer(IGameServerRMI connection, GameServerInfo registeringServer) {
		if (gameservers.containsKey(registeringServer.getId())) {
			GameServerInfo gameServerInfo = gameservers.get(registeringServer.getId());
			if (!gameServerInfo.isShowing()) {
				log.warn("Connection attempt from disabled by config gameserver (id {}).", registeringServer.getId());
				return RegisterResult.DISABLED_SERVER;
			}
			else if (gameservers.values().stream().anyMatch(server -> server.getId() == registeringServer.getId() && server.getConnection() != null)) {
				log.warn("Gameserver with id {} already registered.", registeringServer.getId());
				return RegisterResult.ALREADY_REGISTERED;
			}
			else {
				gameServerInfo.update(connection, registeringServer);
				log.info("Gameserver with id {} registered successfully.", registeringServer.getId());
				return RegisterResult.SUCCESS;
			}
		}
		else {
			log.warn("Unknown gameserver with id {} trying to connect.", registeringServer.getId());
			return RegisterResult.UNKNOWN_SERVER;
		}
	}

	@Override
	public void updateGameServer(IGameServerRMI connection, GameServerInfo gameServerInfo) {
		if (gameservers.values().stream().anyMatch(server -> server.getId() == gameServerInfo.getId() && server.getConnection() != null)) {
			GameServerInfo gameServerInfoToUpdate = gameservers.get(gameServerInfo.getId());
			if (gameServerInfoToUpdate != null) {
				gameServerInfo.update(connection, gameServerInfo);
			}
		}
	}

	@Override
	public void changePassword(String accountName, String oldPass, String newPass) throws RemoteException {
		try(AccountsDAO dao = DatabaseFactory.getInstance().getDAO(AccountsDAO.class)) {
			Account account = dao.findByName(accountName);
			account.setPassword(newPass);
			dao.updatePassword(account);
		}
	}

	@Override
	public void changeAccessLevel(String accountName, int level) throws RemoteException {
		try(AccountsDAO dao = DatabaseFactory.getInstance().getDAO(AccountsDAO.class)) {
			Account account = dao.findByName(accountName);
			account.setAccessLevel(level);
			dao.updateAccessLevel(account);
		}
	}

	@Override
	public void sendMail(String account, String mailId, String... args) throws RemoteException {
		// TODO
	}

	@Override
	public void sendTempBan(String account, String ip, long time) throws RemoteException {
		// TODO
	}

	public boolean isAccountOnServer(String account) {
		for(GameServerInfo gameServerInfo : getGameServers()) {
			try {
				if (gameServerInfo.getConnection().isAccountOnServer(account)) {
					return true;
				}
			}
			catch (Exception e) {
				log.error("Error while calling isAccountOnServer on serverId={}", gameServerInfo.getId());
			}
		}
		return false;
	}

	public void kickPlayerByAccount(String account) {
		for(GameServerInfo gameServerInfo : getGameServers()) {
			try {
				gameServerInfo.getConnection().kickPlayerByAccount(account);
			}
			catch (Exception e) {
				log.error("Error while calling kickPlayerByAccount on serverId={}", gameServerInfo.getId());
			}
		}
	}

	public Collection<GameServerInfo> getGameServers() {
		return gameservers.values().stream().filter(GameServerInfo::isShowing).collect(Collectors.toList());
	}

	private class GameServersStatusWatcher implements Runnable {
		@Override
		public void run() {
			for (Map.Entry<Short, GameServerInfo> entry : gameservers.entrySet()) {
				GameServerInfo gameServerInfo = entry.getValue();
				if (gameServerInfo.getConnection() != null) {
					try {
						gameServerInfo.getConnection().testConnection();
					} catch (RemoteException e) {
						gameServerInfo.setConnection(null);
						gameServerInfo.setStatus(ServerStatus.DOWN);
						log.info("Gameserver with id=[{}] disconnected.", entry.getKey());
					}
				}
			}
		}
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		for (Node gameServersNode = doc.getFirstChild(); gameServersNode != null; gameServersNode = gameServersNode.getNextSibling()) {
			if (gameServersNode.getNodeName().equals("gameServers")) {
				for (Node gameServerNode = gameServersNode.getFirstChild(); gameServerNode != null; gameServerNode = gameServerNode.getNextSibling()) {
					if (gameServerNode.getNodeName().equals("gameServer")) {
						final NamedNodeMap attributes = gameServerNode.getAttributes();
						final short id = parseShort(attributes, "id");
						final String name = parseString(attributes, "name", "");
						final boolean showing = parseBoolean(attributes, "showing", false);
						final AgeLimit ageLimit = parseEnum(attributes, AgeLimit.class, "ageLimit", AgeLimit.NONE);

						Set<ServerType> serverTypes = null;
						for (Node serverTypesNode = gameServerNode.getFirstChild(); serverTypesNode != null; serverTypesNode = serverTypesNode.getNextSibling()) {
							if (serverTypesNode.getNodeName().equals("serverTypes")) {
								for (Node serverTypeNode = serverTypesNode.getFirstChild(); serverTypeNode != null; serverTypeNode = serverTypeNode.getNextSibling()) {
									if (serverTypeNode.getNodeName().equals("serverType")) {
										if (serverTypes == null) {
											serverTypes = new HashSet<>();
										}
										serverTypes.add(parseEnum(serverTypeNode.getFirstChild(), ServerType.class));
									}
								}
							}
						}
						gameservers.put(id, new GameServerInfo(id, name, showing, ageLimit, serverTypes != null ? serverTypes : Collections.emptySet()));
					}
				}
			}
		}
	}

	public static LoginServerRMI getInstance() {
		return SingletonHolder.instance.get();
	}

	private static class SingletonHolder {
		protected static final ThreadLocal<LoginServerRMI> instance = ThreadLocal.withInitial(() -> {
			try {
				return new LoginServerRMI();
			} catch (RemoteException e) {
				log.error("Error while initializing LoginServerRMI server", e);
			}
			return null;
		});
	}
}