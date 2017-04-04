package org.l2junity.core.configs;

import org.l2junity.commons.config.annotation.ConfigComments;
import org.l2junity.commons.config.annotation.ConfigFile;
import org.l2junity.commons.config.annotation.ConfigProperty;

import java.sql.Driver;

/**
 * @author ANZO
 */
@ConfigFile(name = "configs/database.properties")
public class DatabaseConfig {
	@ConfigProperty(name = "URL", value = "jdbc:mysql://localhost/l2jgs?useUnicode=true&characterEncoding=utf-8")
	@ConfigComments(comment = {
			"Database URL",
			"URL = jdbc:mysql://localhost/l2jgs?useUnicode=true&characterEncoding=utf-8"})
	public static String DATABASE_URL;

	@ConfigProperty(name = "Login", value = "root")
	@ConfigComments(comment = "Database user info (default is \"root\" but it's not recommended).")
	public static String DATABASE_LOGIN;

	@ConfigProperty(name = "Driver", value = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource")
	@ConfigComments(comment = {"SQL Driver", "Default: com.mysql.jdbc.jdbc2.optional.MysqlDataSource"})
	public static String DRIVER;

	@ConfigProperty(name = "Password", value = "")
	@ConfigComments(comment = "Database connection password.")
	public static String DATABASE_PASSWORD;

	@ConfigProperty(name = "MaximumDbConnections", value = "100")
	public static int DATABASE_MAX_CONNECTIONS;

	@ConfigProperty(name = "DatabasePoolType", value = "org.l2junity.commons.database.HikariDatabaseFactory")
	public static String DATABASE_POOL_TYPE;
}
