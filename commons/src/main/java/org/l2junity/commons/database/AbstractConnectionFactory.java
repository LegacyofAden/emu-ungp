package org.l2junity.commons.database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author PointerRage
 *
 */
public abstract class AbstractConnectionFactory {
    protected int maxConnections;
    protected String databaseDriver;
    protected String databaseUrl;
    protected String databaseLogin;
    protected String databasePassword;

    protected AbstractConnectionFactory() {

    }

    protected int getMaxConnections() {
        return maxConnections;
    }

    protected void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    protected String getDatabaseDriver() {
        return databaseDriver;
    }

    protected void setDatabaseDriver(String databaseDriver) {
        this.databaseDriver = databaseDriver;
    }

    protected String getDatabaseUrl() {
        return databaseUrl;
    }

    protected void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    protected String getDatabaseLogin() {
        return databaseLogin;
    }

    protected void setDatabaseLogin(String databaseLogin) {
        this.databaseLogin = databaseLogin;
    }

    protected String getDatabasePassword() {
        return databasePassword;
    }

    protected void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }

    protected abstract void init();
    public abstract Connection getConnection() throws SQLException;
    public abstract void shutdown();
}
