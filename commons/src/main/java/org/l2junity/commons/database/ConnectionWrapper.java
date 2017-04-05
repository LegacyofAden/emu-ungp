package org.l2junity.commons.database;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Consumer;

/**
 * @author PointerRage
 *
 */
@RequiredArgsConstructor
public class ConnectionWrapper implements Connection {
    private interface Close {
        void close() throws SQLException;
    }

    private final Consumer<ConnectionWrapper> closeCallback;

    @Delegate(excludes=Close.class)
    private final Connection connection;
    private int counter = 1;

    public void use() {
        counter++;
    }

    @Override
    public void close() throws SQLException {
        if(counter <= 0) {
            throw new SQLException("Connect already closed!");
        }

        if(--counter < 1) {
            closeCallback.accept(this);
            connection.close();
        }
    }

}
