package org.l2junity.gameserver.network.packets;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.network.IClientFactory;
import org.l2junity.commons.network.IPacketHandler;
import org.l2junity.commons.network.NetworkThread;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.GameClientPacketHandler;

/**
 * @author ANZO
 * @since 06.04.2017
 */
@Slf4j
@StartupComponent("Network")
public class GameNetworkThread extends NetworkThread<GameClient> {
	@Getter(lazy = true)
	private static final GameNetworkThread instance = new GameNetworkThread();

	@Override
	public IClientFactory<GameClient> getClientFactory() {
		return GameClientFactory.getInstance();
	}

	@Override
	public IPacketHandler<GameClient> getPacketHandler() {
		return GameClientPacketHandler.getInstance();
	}
}