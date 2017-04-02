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
package org.l2junity.commons.sql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.core.configs.DatabaseConfig;
import org.l2junity.core.startup.StartupComponent;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.exceptions.DBIException;
import org.skife.jdbi.v2.exceptions.UnableToObtainConnectionException;
import org.skife.jdbi.v2.tweak.HandleCallback;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

@Slf4j
@StartupComponent("Database")
public final class DatabaseFactory {
	@Getter(lazy = true)
	private final static DatabaseFactory instance = new DatabaseFactory();

	private HikariDataSource source;
	private final DBI dbi;

	protected DatabaseFactory() {
		try {
			HikariConfig config = new HikariConfig();

			config.setDataSourceClassName(DatabaseConfig.DRIVER);
			config.addDataSourceProperty("url", DatabaseConfig.DATABASE_URL);
			config.addDataSourceProperty("user", DatabaseConfig.DATABASE_LOGIN);
			config.addDataSourceProperty("password", DatabaseConfig.DATABASE_PASSWORD);
			config.setMaximumPoolSize(DatabaseConfig.DATABASE_MAX_CONNECTIONS);
			config.setAutoCommit(true);
			config.setConnectionTestQuery("SELECT version()");
			config.setIdleTimeout(30000);
			config.setMaxLifetime(TimeUnit.MINUTES.toMillis(5));
			source = new HikariDataSource(config);
		} catch (Exception e) {
			log.error("Could not init DB connection", e);
		} finally {
			dbi = new DBI(source);
		}

		log.info("Initialized DB '{}' as user '{}'.", DatabaseConfig.DATABASE_URL, DatabaseConfig.DATABASE_LOGIN);
	}

	/**
	 * Gets the handle.
	 *
	 * @return the handle
	 * @throws DBIException
	 */
	public Handle getHandle() throws DBIException {
		Handle handle = null;
		while (handle == null) {
			try {
				handle = dbi.open();
			} catch (UnableToObtainConnectionException e) {
				log.warn("getHandle() failed, trying again", e);
			}
		}
		return handle;
	}

	public <R> R withHandle(HandleCallback<R> callback) throws DBIException {
		return dbi.withHandle(callback);
	}

	public <R> R getDAO(Class<R> clazz) throws DBIException {
		return dbi.open(clazz);
	}

	private void closeSource() {
		if (source != null) {
			source.close();
		}

		// Is this really necessary?
		source = null;
	}

	public static void shutdown() {
		log.info("Shutting down.");

		try {
			DatabaseFactory.getInstance().closeSource();
		} catch (Exception e) {
			log.warn("", e);
		}
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection() {
		Connection con = null;
		while (con == null) {
			try {
				con = source.getConnection();
			} catch (SQLException e) {
				log.warn("getConnection() failed, trying again", e);
			}
		}
		return con;
	}
}
