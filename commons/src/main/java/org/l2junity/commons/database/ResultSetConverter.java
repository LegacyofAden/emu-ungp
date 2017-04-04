package org.l2junity.commons.database;

import org.l2junity.commons.collections.ConcurrentMultiValueSet;
import org.l2junity.commons.collections.MultiValueSet;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author n3k0nation
 *
 */
public class ResultSetConverter {
    private ResultSetConverter() {
        throw new RuntimeException();
    }

    public static void copyToMap(ResultSet rset, Map<String, Object> map) throws SQLException {
        final ResultSetMetaData meta = rset.getMetaData();
        for(int i = 1; i <= meta.getColumnCount(); i++) {
            String name = meta.getColumnName(i);
            Object value = rset.getObject(i);

            map.put(name, value);
        }
    }

    public static MultiValueSet<String> toMultiValueSet(ResultSet rset) throws SQLException {
        final MultiValueSet<String> collection = new MultiValueSet<>();
        copyToMap(rset, collection);
        return collection;
    }

    public static ConcurrentMultiValueSet<String> toConcurrentMultiValueSet(ResultSet rset) throws SQLException {
        ConcurrentMultiValueSet<String> collection = new ConcurrentMultiValueSet<>();
        copyToMap(rset, collection);
        return collection;
    }
}
