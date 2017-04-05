package org.l2junity.gameserver;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.core.startup.StartupLevel;
import org.l2junity.core.startup.StartupManager;
import org.l2junity.gameserver.service.GameServerRMI;

import java.lang.management.ManagementFactory;

/**
 * @author ANZO
 */
@Slf4j
public final class GameServer {
	@Getter
	private static GameServer instance;

	@Getter
	private GameServerRMI rmi;

	private GameServer() throws Exception {
		StartupManager.getInstance().startup(StartupLevel.class);
		rmi = new GameServerRMI();
	}

	public long getStartedTime() {
		return ManagementFactory.getRuntimeMXBean().getStartTime();
	}

	public static void main(String[] args) throws Exception {
		try {
			instance = new GameServer();
		} catch (Exception e) {
			log.error("Error while starting GameServerInfo", e);
		}
	}
}