package org.l2junity.loginserver.network;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.network.IClientFactory;
import org.l2junity.commons.network.IPacketHandler;
import org.l2junity.commons.network.NetworkThread;
import org.l2junity.core.startup.StartupComponent;

/**
 * @author ANZO
 * @since 05.04.2017
 */
@Slf4j
@StartupComponent("Network")
public class LoginNetworkThread extends NetworkThread<LoginClient> {
	@Getter(lazy = true)
	private static final LoginNetworkThread instance = new LoginNetworkThread();

	@Override
	public IClientFactory<LoginClient> getClientFactory() {
		return LoginClientFactory.getInstance();
	}

	@Override
	public IPacketHandler<LoginClient> getPacketHandler() {
		return LoginPacketHandler.getInstance();
	}
}
