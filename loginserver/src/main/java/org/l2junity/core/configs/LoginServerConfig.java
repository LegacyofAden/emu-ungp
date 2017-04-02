package org.l2junity.core.configs;

import org.l2junity.commons.config.annotation.ConfigComments;
import org.l2junity.commons.config.annotation.ConfigFile;
import org.l2junity.commons.config.annotation.ConfigProperty;

/**
 * @author ANZO
 * @since 02.04.2017
 */
@ConfigFile(name = "configs/loginserver.properties")
public class LoginServerConfig {
	@ConfigProperty(name = "GameServerLoginHostname", value = "127.0.0.1")
	@ConfigComments(comment = {
			"Hostname on which login server will accept connections from other game servers.",
			"Default: 127.0.0.1"
	})
	public static String GAME_SERVER_LOGIN_HOST;

	@ConfigProperty(name = "GameServerLoginPort", value = "9014")
	@ConfigComments(comment = {
			"Port on which login server will accept connections from other game servers.",
			"Default: 9014"
	})
	public static int GAME_SERVER_LOGIN_PORT;

	@ConfigProperty(name = "AutoCreateAccounts", value = "false")
	@ConfigComments(comment = {
			"Creating automatically an account if not exists when user attempts to login.",
			"Default: false"
	})
	public static boolean AUTO_CREATE_ACCOUNTS;

	@ConfigProperty(name = "ShowLicense", value = "true")
	@ConfigComments(comment = {
			"Showing License agreement.",
			"Default: true"
	})
	public static boolean SHOW_LICENCE;
}