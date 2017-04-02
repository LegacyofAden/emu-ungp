package org.l2junity.core.configs;

import org.l2junity.commons.config.annotation.ConfigComments;
import org.l2junity.commons.config.annotation.ConfigFile;
import org.l2junity.commons.config.annotation.ConfigProperty;

/**
 * @author ANZO
 */
@ConfigFile(name = "configs/database.properties")
public class DatabaseConfig {
	@ConfigProperty(name = "URL", value = "jdbc:mysql://localhost/l2jgs?useSSL=false&serverTimezone=Europe/Paris")
	@ConfigComments(comment = {
			"Database URL",
			"URL = jdbc:mysql://localhost/l2jgs?useSSL=false&serverTimezone=Europe/Paris (default)",
			"URL = jdbc:hsqldb:hsql://localhost/l2jgs",
			"URL = jdbc:sqlserver://localhost/database = l2jgs/user = sa/password = "})
	public static String DATABASE_URL;

	@ConfigProperty(name = "Login", value = "root")
	@ConfigComments(comment = "Database user info (default is \"root\" but it's not recommended).")
	public static String DATABASE_LOGIN;

	@ConfigProperty(name = "Password", value = "")
	@ConfigComments(comment = "Database connection password.")
	public static String DATABASE_PASSWORD;

	@ConfigProperty(name = "MaximumDbConnections", value = "100")
	public static int DATABASE_MAX_CONNECTIONS;

	@ConfigProperty(name = "MaximumDbIdleTime", value = "0")
	public static int DATABASE_MAX_IDLE_TIME;

	@ConfigProperty(name = "ConnectionCloseTime", value = "60000")
	@ConfigComments(comment = "Connection close time.")
	public static long CONNECTION_CLOSE_TIME;
}
