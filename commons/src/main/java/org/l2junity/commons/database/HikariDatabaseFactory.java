package org.l2junity.commons.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

/**
 * @author PointerRage
 *
 */
@Slf4j
public class HikariDatabaseFactory extends AbstractConnectionFactory {
    private final ThreadLocal<ConnectionWrapper> localConnection = new ThreadLocal<>();
    private HikariDataSource source;

    @Override
    public void init() {
        try {
            if (maxConnections < 2) {
                maxConnections = 2;
                log.warn("at least {} db connections are required.", maxConnections);
            }

            HikariConfig config = new HikariConfig();

            config.setDataSourceClassName(databaseDriver);
            config.addDataSourceProperty("url", databaseUrl);
            config.addDataSourceProperty("user", databaseLogin);
            config.addDataSourceProperty("password", databasePassword);
            config.addDataSourceProperty("CacheCallableStatements", false);
            config.addDataSourceProperty("CachePreparedStatements", false);
            config.addDataSourceProperty("RetainStatementAfterResultSetClose", true);
//			config.addDataSourceProperty("CallableStatementCacheSize", 0);
            config.setMinimumIdle(maxConnections / 2);
            config.setMaximumPoolSize(maxConnections);
            config.setAutoCommit(true);
            config.setConnectionTimeout(5_000);
            config.setIdleTimeout(30_000);
            config.setMaxLifetime(TimeUnit.MINUTES.toMillis(5));

            source = new HikariDataSource(config);

            log.info ("DatabaseFactory: Connected to database server");
        } catch (Exception e) {
            log.error("DatabaseFactory: Failed to init database connections", e);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        ConnectionWrapper con = localConnection.get();
        if(con == null) {
            con = new ConnectionWrapper(e -> localConnection.remove(), source.getConnection());
        } else {
            con.use();
        }

        localConnection.set(con);
        return con;
    }

    @Override
    public void shutdown() {
        source.close();
    }
}
