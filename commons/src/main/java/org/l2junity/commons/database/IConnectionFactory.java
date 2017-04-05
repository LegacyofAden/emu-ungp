package org.l2junity.commons.database;

import java.sql.Connection;

/**
 * @author PointerRage
 *
 */
public interface IConnectionFactory {
    Connection getConnection();
}
