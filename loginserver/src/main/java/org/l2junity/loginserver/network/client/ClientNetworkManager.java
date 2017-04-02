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
package org.l2junity.loginserver.network.client;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.core.configs.NetworkConfig;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.loginserver.network.EventLoopGroupManager;
import org.l2junity.network.NetworkManager;

/**
 * @author NosBit
 */
@Slf4j
@StartupComponent("Network")
public class ClientNetworkManager extends NetworkManager {
	@Getter(lazy = true)
	private static final ClientNetworkManager instance = new ClientNetworkManager();

	private ClientNetworkManager() {
		super(EventLoopGroupManager.getInstance().getBossGroup(), EventLoopGroupManager.getInstance().getWorkerGroup(), new ClientInitializer(), NetworkConfig.HOST, NetworkConfig.PORT);
		try {
			start();
			getChannelFuture().channel().closeFuture().sync();
		} catch (InterruptedException e) {
			log.error("Error while starting ClientNetworkManager", e);
		}
	}
}