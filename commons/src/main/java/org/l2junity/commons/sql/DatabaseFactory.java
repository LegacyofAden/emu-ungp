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

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.threading.ThreadPool;
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

	private ComboPooledDataSource _source;
	private final DBI _dbi;

	protected DatabaseFactory() {
		try {
			_source = new ComboPooledDataSource();
			_source.setAutoCommitOnClose(true);

			_source.setInitialPoolSize(10);
			_source.setMinPoolSize(10);
			_source.setMaxPoolSize(Math.max(10, DatabaseConfig.DATABASE_MAX_CONNECTIONS));

			_source.setAcquireRetryAttempts(0); // try to obtain connections indefinitely (0 = never quit)
			_source.setAcquireRetryDelay(500); // 500 milliseconds wait before try to acquire connection again
			_source.setCheckoutTimeout(0); // 0 = wait indefinitely for new connection
			// if pool is exhausted
			_source.setAcquireIncrement(5); // if pool is exhausted, get 5 more connections at a time
			// cause there is a "long" delay on acquire connection
			// so taking more than one connection at once will make connection pooling
			// more effective.

			_source.setIdleConnectionTestPeriod(3600); // test idle connection every 60 sec
			_source.setMaxIdleTime(DatabaseConfig.DATABASE_MAX_IDLE_TIME); // 0 = idle connections never expire
			// *THANKS* to connection testing configured above
			// but I prefer to disconnect all connections not used
			// for more than 1 hour

			// enables statement caching, there is a "semi-bug" in c3p0 0.9.0 but in 0.9.0.2 and later it's fixed
			_source.setMaxStatementsPerConnection(100);

			_source.setBreakAfterAcquireFailure(false); // never fail if any way possible
			// setting this to true will make
			// c3p0 "crash" and refuse to work
			// till restart thus making acquire
			// errors "FATAL" ... we don't want that
			// it should be possible to recover
			_source.setJdbcUrl(DatabaseConfig.DATABASE_URL);
			_source.setUser(DatabaseConfig.DATABASE_LOGIN);
			_source.setPassword(DatabaseConfig.DATABASE_PASSWORD);

			/* Test the connection */
			_source.getConnection().close();
		} catch (Exception e) {
			log.error("Could not init DB connection", e);
		} finally {
			_dbi = new DBI(_source);
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
				handle = _dbi.open();
			} catch (UnableToObtainConnectionException e) {
				log.warn("getHandle() failed, trying again", e);
			}
		}
		return handle;
	}

	public <R> R withHandle(HandleCallback<R> callback) throws DBIException {
		return _dbi.withHandle(callback);
	}

	public <R> R getDAO(Class<R> clazz) throws DBIException {
		return _dbi.open(clazz);
	}

	private void closeSource() {
		if (_source != null) {
			_source.close();
		}

		// Is this really necessary?
		_source = null;
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
				con = _source.getConnection();

				ThreadPool.getInstance().scheduleGeneral(new ConnectionCloser(con, new RuntimeException()), DatabaseConfig.CONNECTION_CLOSE_TIME, TimeUnit.MILLISECONDS);
			} catch (SQLException e) {
				log.warn("getConnection() failed, trying again", e);
			}
		}
		return con;
	}

	/**
	 * The Class ConnectionCloser.
	 */
	private static final class ConnectionCloser implements Runnable {
		/**
		 * The connection.
		 */
		private final Connection c;

		/**
		 * The exception.
		 */
		private final RuntimeException exp;

		/**
		 * Instantiates a new connection closer.
		 *
		 * @param con the con
		 * @param e   the e
		 */
		public ConnectionCloser(Connection con, RuntimeException e) {
			c = con;
			exp = e;
		}

		@Override
		public void run() {
			try {
				if (!c.isClosed()) {
					log.warn("Unclosed connection! Trace: {}", exp.getStackTrace()[1], exp);
				}
			} catch (SQLException e) {
				log.warn("", e);
			}
		}
	}

	/**
	 * Gets the busy connection count.
	 *
	 * @return the busy connection count
	 * @throws SQLException the SQL exception
	 */
	public int getBusyConnectionCount() throws SQLException {
		return _source.getNumBusyConnectionsDefaultUser();
	}

	/**
	 * Gets the idle connection count.
	 *
	 * @return the idle connection count
	 * @throws SQLException the SQL exception
	 */
	public int getIdleConnectionCount() throws SQLException {
		return _source.getNumIdleConnectionsDefaultUser();
	}
}
