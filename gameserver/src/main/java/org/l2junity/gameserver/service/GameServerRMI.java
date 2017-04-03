package org.l2junity.gameserver.service;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.model.GameServerInfo;
import org.l2junity.commons.model.enums.AgeLimit;
import org.l2junity.commons.model.enums.ServerStatus;
import org.l2junity.commons.rmi.IGameServerRMI;
import org.l2junity.commons.rmi.ILoginServerRMI;
import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.core.configs.GameserverConfig;
import org.l2junity.core.configs.GeoDataConfig;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.GameServer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author ANZO
 * @since 03.04.2017
 */
@Slf4j
@StartupComponent("Network")
public class GameServerRMI extends UnicastRemoteObject implements IGameServerRMI {
	private ILoginServerRMI connection;
	private GameServerInfo gameServerInfo;
	private transient ScheduledFuture<?> reconnectTask;

	protected GameServerRMI() throws RemoteException {
		gameServerInfo = new GameServerInfo(GameserverConfig.SERVER_ID);
	}

	private void connectToGeoDataServer() {
		try {
			Registry registry = LocateRegistry.getRegistry(GameserverConfig.GAME_SERVER_LOGIN_HOST, GameserverConfig.GAME_SERVER_LOGIN_PORT);
			connection = (ILoginServerRMI) registry.lookup("loginServer");
			connection.registerGameServer(this, gameServerInfo);
			reconnectTask = null;
		} catch (Exception e) {
			log.error("Connection to login server failed", e);
			onConnectionLost();
		}
		log.info("Connected to login server successfully.");
	}

	private void sendUpdate() {
		try {
			connection.updateGameServer(this, gameServerInfo);
		}
		catch (Exception e) {
			log.error("Error while updating game server status");
			onConnectionLost();
		}
	}

	private void onConnectionLost() {
		if (reconnectTask != null)
			return;

		log.info("Connection with login server lost.");
		connection = null;

		reconnectTask = ThreadPool.getInstance().scheduleGeneral(() -> {
			log.info("Reconecting to login server...");
			connectToGeoDataServer();
		}, 2, TimeUnit.SECONDS);
	}

	@Override
	public boolean testConnection() throws RemoteException {
		return false;
	}

	public void setServerAge(AgeLimit ageLimit) {
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

	public void setCurrentOnline(int currentOnline) {
		gameServerInfo.setCurrentOnline(currentOnline);
		sendUpdate();
	}

	public void setMaxOnline(int maxOnline) {
		gameServerInfo.setMaxOnline(maxOnline);
		sendUpdate();
	}

	public static GameServerRMI getInstance() {
		return SingletonHolder.instance.get();
	}

	private static class SingletonHolder {
		protected static final ThreadLocal<GameServerRMI> instance = ThreadLocal.withInitial(() -> {
			try {
				return new GameServerRMI();
			} catch (RemoteException e) {
				log.error("Error while initializing GameServerRMI server", e);
			}
			return null;
		});
	}
}
