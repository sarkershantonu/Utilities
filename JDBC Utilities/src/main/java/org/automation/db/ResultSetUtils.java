package org.automation.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shantonu on 12/31/16.
 * 
 */
public class ResultSetUtils {

  public static List<List<String>> getAllResutlsInTable(final ResultSet resultSet) throws SQLException {
        final List<List<String>> table = new ArrayList<>();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            List<String> aRow = new ArrayList<>();
            for (int i = 1; i <= columnsNumber; i++) {

                //String columnValue = resultSet.getString(i);

                //System.out.print(columnValue + " " + rsmd.getColumnName(i));
                aRow.add(resultSet.getString(i));
            }
            System.out.println("");
        }
        return table;
    }

 public static void printAllItemInResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = resultSet.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
        }
    }
  //bad way to get, traverse full result set
   public static int getRowCount(ResultSet rs) throws SQLException {
        int count = 0;
        while (rs.next()){
            count++;
        }
        return count;
    }
}
