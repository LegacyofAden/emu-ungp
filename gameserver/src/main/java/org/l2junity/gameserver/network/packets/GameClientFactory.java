package org.l2junity.gameserver.network.packets;

import lombok.Getter;
import org.l2junity.commons.network.Connection;
import org.l2junity.commons.network.IClientFactory;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.network.GameClient;

/**
 * @author ANZO
 * @since 06.04.2017
 */
@StartupComponent("Network")
public class GameClientFactory implements IClientFactory<GameClient> {
	@Getter(lazy = true)
	private final static GameClientFactory instance = new GameClientFactory();

	@Override
	public GameClient createClient(Connection<GameClient> connection) {
		return new GameClient(connection);
	}
}