package org.automation.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class QueryHelper {
    private QueryHelper() {}

    public static void closeResultSet(ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
    }

    public static void closeStatement(Statement stmt) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
    }

    public static void closeResultSetAndStatement(ResultSet rs, Statement stmt) throws SQLException {
        try {
            closeResultSet(rs);
        } finally {
            closeStatement(stmt);
        }
    }

}
