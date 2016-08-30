


import com.google.common.collect.ArrayTable;
import com.google.common.collect.Table;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class SqlHelper {
    public static Query<Date> getQueryForDate(String sql, Map<String, String> paramMap, Connection connection) {
        return new Query<Date>(sql, paramMap, connection) {
            private Date result;

            @Override
            protected void processResultSet(ResultSet rs) throws SQLException {
                if (rs.next()) {
                    result = rs.getTimestamp(1);
                }
            }

            @Override
            public Date query() throws SQLException {
                this.execute();
                return result;
            }
        };
    }

    public static Query<Set<String>> getQueryForSet(String sql, Map<String, String> paramMap, Connection connection) {
        return new Query<Set<String>>(sql, paramMap, connection) {
            @Override
            protected void processResultSet(ResultSet rs) throws SQLException {
                result = new HashSet<String>();
                while (rs.next()) {
                    result.add(rs.getObject(1).toString());
                }
            }
        };
    }

    public static Query<List<Map<String, Object>>> getQueryForListOfRowMaps(String sql, Map<String, String> paramMap, Connection connection) {
        return new Query<List<Map<String, Object>>>(sql, paramMap, connection) {
            @Override
            protected void processResultSet(ResultSet rs) throws SQLException {
                result = new ArrayList<Map<String, Object>>();
                // Create mapTempate
                List<String> columns = new LinkedList<String>();
                int count = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= count; i++) {
                    columns.add(rs.getMetaData().getColumnLabel(i));
                }

                while (rs.next()) {
                    Map<String, Object> row = new HashMap<String, Object>();
                    for (String columnName : columns) {
                        row.put(columnName, rs.getObject(columnName));
                    }
                    result.add(row);
                }
            }
        };
    }


    /**
     * Create Query Object for retreinving result as a guava Table
     * First table row is 1. Column names are String, values are Objects.
     * Not memory-efficient or fast. Thanks to google for NOT supporting nulls.
     * @param sql  SQL to be executed
     * @param paramMap  SQL parameters (substituted with dumb simple String.replace())
     * @param connection  JDBC Connection
     * @return table with resultSet fetching result. row keys are row numbers (starts from 1)
     */
    public static Query<Table<Integer, String, Object>> getQueryForTable(String sql, Map<String, String> paramMap, Connection connection) {
        return new Query<Table<Integer, String, Object>>(sql, paramMap, connection) {
            @Override
            protected void processResultSet(ResultSet rs) throws SQLException {
                List<String> columns = new ArrayList<String>();
                int colCount = rs.getMetaData().getColumnCount();

                for (int i = 1; i <= colCount; i++) {
                    columns.add(rs.getMetaData().getColumnLabel(i));
                }

                // Nasty workaround for the fact that google tables except for ArrayTable DO NOT SUPPORT NULLS
                //   and ArrayTable cannot be created without specifying final table size.

                // 1. Create List of rows (each row is an array)
                List<Object[]> rows = new ArrayList<Object[]>();
                while (rs.next()) {
                    Object[] row = new Object[columns.size()];
                    for (int i = 1; i <= columns.size(); i++) {
                        row[i - 1] = rs.getObject(i);
                    }
                    rows.add(row);
                }

                // Create a list of row numbers
                List<Integer> rowKeys = new LinkedList<Integer>();
                for (int i = 1; i <= rows.size(); i++) {
                    rowKeys.add(i);
                }

                // Supply column names and row numbers to ArrayTable on creation
                result = ArrayTable.create(rowKeys, columns);

                //Populate ArrayTable
                for (Integer rowId : rowKeys) {
                    for (int colId = 0; colId < columns.size(); colId++) {
                        result.put(rowId, columns.get(colId), rows.get(rowId - 1)[colId]);
                    }
                }
            }
        };
    }


}
