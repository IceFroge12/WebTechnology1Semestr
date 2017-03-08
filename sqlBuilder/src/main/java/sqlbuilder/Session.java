package sqlbuilder;


import com.sun.istack.internal.NotNull;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IO on 07.12.2016.
 */
public class Session {

    private DataSource dataSource;
    private Connection connection;

    public Session(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public void beginTransaction() throws SQLException {
        if (connection == null){
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
        }else {
            connection.rollback();
            throw new SQLException("Transaction has been opened already");
        }
    }

    public void closeTransaction() throws SQLException {
        if (connection != null){
            connection.commit();
            connection = null;
        }
    }

    public <T> List<T> ExecuteQuery(@NotNull QuerySql querySql, ResultSetExtractor<T> resultSetExtractor) throws SQLException {
        if (connection != null){
            PreparedStatement preparedStatement = connection.prepareStatement(querySql.getSQL());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> result = null;
            while (resultSet.next()){
                if (result == null){
                    result = new LinkedList<T>();
                }
                result.add(resultSetExtractor.extractData(resultSet));
            }
            return result;
        }else {
            throw new SQLException("Session has not been opened");
        }
    }

    public ResultSet QueryExecute(@NotNull QuerySql querySql) throws SQLException {
        if (connection != null){
            PreparedStatement preparedStatement = connection.prepareStatement(querySql.getSQL());
            return preparedStatement.executeQuery();
        }else {
            throw new SQLException("Session has not been opened");
        }
    }

    public int ExecuteDelete(@NotNull DeleteSql querySql) throws SQLException {
        return execute(querySql);
    }

    public int ExecuteDelete(@NotNull UpdateSql updateSql) throws SQLException {
        return execute(updateSql);
    }

    private int execute(SqlSegment segment) throws SQLException {
        if (connection != null){
            PreparedStatement preparedStatement = connection.prepareStatement(segment.getSQL());
            return preparedStatement.executeUpdate();
        }else {
            throw new SQLException("Session has not been opened");
        }
    }
}
