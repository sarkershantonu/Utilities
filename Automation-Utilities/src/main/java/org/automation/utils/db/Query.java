package org.automation.utils.db;

import org.slf4j.Logger;



import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;


public abstract class Query<T> {
    private static final Logger LOG = LoggerFactory.getLogger(Query.class);

    private final String sql;
    private final Map<String, String> paramMap;
    private final Connection connection;
    private final String param;
    protected T result = null;

    public Query(String sql, Map<String, String> paramMap, Connection connection) {
        this.sql = sql;
        this.connection = connection;
        this.paramMap = paramMap;
        this.param = null;
    }

    public Query(String sql, String param, Connection connection) {
        this.sql = sql;
        this.connection = connection;
        this.paramMap = null;
        this.param = param;
    }

    public Query(String sql, Connection connection) {
        this.sql = sql;
        this.connection = connection;
        this.paramMap = null;
        this.param = null;
    }

    public void execute() throws SQLException{
        final String query = inlineParamsIntoQuery(sql, paramMap);
        final PreparedStatement stmt = connection.prepareStatement(
                query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        if (null != param) {
            stmt.setString(1, param);
        }
        ResultSet rs = null;  //NOPMD - closed by helper
        try {
            LOG.trace(query);
            rs = stmt.executeQuery();
            if (null != rs) {
                processResultSet(rs);
            }
        } catch (SQLException e) {
            LOG.error(String.format("SQL error while executing following sql:%s. Connection info:%s",  query,ToStringBuilder.reflectionToString(connection)));
            throw e;
        } catch (Exception e) {
            LOG.error("Error while executing following sql:" + query);
            throw new RuntimeException(e.getMessage(),e);
        } finally {

            QueryHelper.closeResultSetAndStatement(rs, stmt);
        }
    }

    public T query() throws SQLException {
        execute();
        return result;
    }

    public T getResult() {
        return result;
    }


    private String inlineParamsIntoQuery(String sql, Map<String, String> paramMap) {
        String query = sql;
        if (null != paramMap) {
            for (Map.Entry<String, String> paramToReplace : paramMap.entrySet()) {
                query = query.replace(paramToReplace.getKey(), paramToReplace.getValue());
            }
        }
        return query;
    }

    protected abstract void processResultSet(ResultSet rs) throws SQLException;
}
