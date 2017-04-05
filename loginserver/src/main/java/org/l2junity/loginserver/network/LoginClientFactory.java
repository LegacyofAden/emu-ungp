package org.l2junity.loginserver.network;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.network.Connection;
import org.l2junity.commons.network.IClientFactory;
import org.l2junity.core.startup.StartupComponent;

/**
 * @author ANZO
 * @since 24.03.2017
 */
@Slf4j
@StartupComponent("Network")
public class LoginClientFactory implements IClientFactory<LoginClient> {
	@Getter(lazy = true)
	private final static LoginClientFactory instance = new LoginClientFactory();

	@Override
	public LoginClient createClient(Connection<LoginClient> connection) {
		return new LoginClient(connection);
	}
}
