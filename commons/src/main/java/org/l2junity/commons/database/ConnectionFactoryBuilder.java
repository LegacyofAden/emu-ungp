package org.l2junity.commons.database;

import java.lang.reflect.Constructor;

/**
 * @author PointerRage
 *
 */
public class ConnectionFactoryBuilder {
    public static ConnectionFactoryBuilder builder() {
        return new ConnectionFactoryBuilder();
    }

    private Class<? extends AbstractConnectionFactory> poolType;
    private int maxConnections;
    private String databaseDriver;
    private String databaseUrl;
    private String databaseLogin;
    private String databasePassword;

    public ConnectionFactoryBuilder() {

    }

    public ConnectionFactoryBuilder setPoolType(String poolType) {
        Class<?> clazz;
        try {
            clazz = Class.forName(poolType);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Pool type not found " + poolType, e);
        }
        this.poolType = clazz.asSubclass(AbstractConnectionFactory.class);
        return this;
    }

    public ConnectionFactoryBuilder setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
        return this;
    }

    public ConnectionFactoryBuilder setDatabaseDriver(String databaseDriver) {
        this.databaseDriver = databaseDriver;
        return this;
    }

    public ConnectionFactoryBuilder setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
        return this;
    }

    public ConnectionFactoryBuilder setDatabaseLogin(String databaseLogin) {
        this.databaseLogin = databaseLogin;
        return this;
    }

    public ConnectionFactoryBuilder setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
        return this;
    }

    public AbstractConnectionFactory build() {
        AbstractConnectionFactory factory;
        try {
            Constructor<? extends AbstractConnectionFactory> ctor = poolType.getConstructor();
            ctor.setAccessible(true);
            factory = ctor.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed create pool", e);
        }
        factory.setMaxConnections(maxConnections);
        factory.setDatabaseDriver(databaseDriver);
        factory.setDatabaseUrl(databaseUrl);
        factory.setDatabaseLogin(databaseLogin);
        factory.setDatabasePassword(databasePassword);
        factory.init();
        return factory;
    }
}
