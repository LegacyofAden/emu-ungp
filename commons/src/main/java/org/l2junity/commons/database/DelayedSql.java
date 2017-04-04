package org.l2junity.commons.database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author PointerRage
 *
 */
public interface DelayedSql {
    void runQuery(Connection con) throws SQLException;
}
