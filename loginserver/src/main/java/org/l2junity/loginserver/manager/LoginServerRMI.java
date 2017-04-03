package org.l2junity.loginserver.manager;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.model.GameServerInfo;
import org.l2junity.commons.model.enums.AgeLimit;
import org.l2junity.commons.model.enums.RegisterResult;
import org.l2junity.commons.model.enums.ServerStatus;
import org.l2junity.commons.model.enums.ServerType;
import org.l2junity.commons.rmi.IGameServerRMI;
import org.l2junity.commons.rmi.ILoginServerRMI;
import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.commons.util.IXmlReader;
import org.l2junity.commons.util.XmlReaderException;
import org.l2junity.core.startup.StartupComponent;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
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
			ThreadPool.getInstance().scheduleGeneralAtFixedRate(new GameServersStatusWatcher(), 0, 1, TimeUnit.SECONDS);
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
				return RegisterResult.DISABLED_SERVER;
			}
			else if (gameservers.values().stream().anyMatch(server -> server.getId() == registeringServer.getId() && server.getConnection() != null)) {
				return RegisterResult.ALREADY_REGISTERED;
			}
			else {
				gameServerInfo.update(connection, registeringServer);
				return RegisterResult.SUCCESS;
			}
		}
		else {
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

	public Collection<GameServerInfo> getGameServers() {
		return gameservers.values().stream().filter(GameServerInfo::isShowing).collect(Collectors.toList());
	}

	private class GameServersStatusWatcher implements Runnable {
		@Override
		public void run() {
			for (Map.Entry<Short, GameServerInfo> entry : gameservers.entrySet()) {
				GameServerInfo gameServerInfo = entry.getValue();
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