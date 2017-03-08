import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * Created by IO on 11.11.2016.
 */
public class JdbcTemplate {

    private DataSource dataSource;


    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int update(String sql, Object... objects) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int rowNum = 1;
            for (Object object : objects) {
                statement.setObject(rowNum++, object);
            }
            return statement.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public int update(String sql, Connection connection, Object... objects) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            int rowNum = 1;
            for (Object object : objects) {
                statement.setObject(rowNum++, object);
            }
            return statement.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    public Long insert(String sql, Object... objects) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            return insert(statement, objects);
        } catch (SQLException e) {
            return 0L;
        }
    }

    public Long insert(String sql, Connection connection, Object... objects) {
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            return insert(statement, objects);
        } catch (SQLException e) {
            return 0L;
        }
    }

    public <T> List<T> query(String sql, ResultSetExtractor<T> resultSetExtractor, Object... objects) {
        return (List<T>) queryForCollection(new ArrayList<>(), sql, resultSetExtractor, (Object[]) objects);
    }

    public <T> String queryShow(String sql, Object... objects) {
        return  queryForCollectionShow(sql, (Object[]) objects);
    }

    public <T> List<T> query(String sql, ResultSetExtractor<T> resultSetExtractor, Map<String, Object> namedParameters) {
        return (List<T>) queryForCollection(new ArrayList<>(), sql, resultSetExtractor, namedParameters);
    }

    public <T> String queryShow(String sql, Map<String, Object> namedParameters) {
        return  queryForCollectionShow(sql, namedParameters);
    }

    private <T> Collection<T> queryForCollection(Collection<T> collection, String sql,
                                                 ResultSetExtractor<T> resultSetExtractor, Object... objects) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int rowNum = 1;
            for (Object object : objects) {
                statement.setObject(rowNum++, object);
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                collection.add(resultSetExtractor.extractData(resultSet));
            }
            return collection;
        } catch (SQLException e) {

        }
        return collection;
    }

    private <T> String queryForCollectionShow(String sql, Object... objects) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int rowNum = 1;
            for (Object object : objects) {
                statement.setObject(rowNum++, object);
            }

            return statement.toString();
        } catch (SQLException e) {

        }
        return "";
    }

    private <T> Collection<T> queryForCollection(Collection<T> collection, String sql,
                                                 ResultSetExtractor<T> resultSetExtractor, Map<String, Object> namedParameters) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            NamedParameterStatement namedParameterStatement = new NamedParameterStatement(connection, sql);
            namedParameters.keySet().forEach(key -> {
                try {
                    if (namedParameters.get(key) instanceof Integer){
                        namedParameterStatement.setInt(key, (Integer) namedParameters.get(key));
                    }else if (namedParameters.get(key) instanceof String){
                        namedParameterStatement.setString(key, (String) namedParameters.get(key));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            });
            ResultSet resultSet = namedParameterStatement.executeQuery();
            while (resultSet.next()) {
                collection.add(resultSetExtractor.extractData(resultSet));
            }
            return collection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return collection;
    }

    private <T> String queryForCollectionShow(String sql, Map<String, Object> namedParameters) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            NamedParameterStatement namedParameterStatement = new NamedParameterStatement(connection, sql);

            for (String s : namedParameters.keySet()) {
                try {
                    if (namedParameters.get(s) instanceof Integer){
                        namedParameterStatement.setInt(s, (Integer) namedParameters.get(s));
                    }else if (namedParameters.get(s) instanceof String){
                        namedParameterStatement.setString(s, (String) namedParameters.get(s));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return namedParameterStatement.getStatement().toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public <T> Set<T> queryForSet(String sql, ResultSetExtractor<T> resultSetExtractor, Object... objects) {
        return (Set<T>) queryForCollection(new HashSet<>(), sql, resultSetExtractor, (Object[]) objects);
    }

    private Long insert(PreparedStatement statement, Object... objects) {
        int rowNum = 1;
        try {
            for (Object object : objects) {
                statement.setObject(rowNum++, object);
            }
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {

        }
        return 0L;
    }

    public int[] batchUpdate(String sql, Object[][] objects) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int rowNum = 1;
            for (Object[] objectsForSql : objects) {
                for (Object object : objectsForSql) {
                    statement.setObject(rowNum++, object);
                }
                statement.addBatch();
                rowNum = 1;
            }
            return statement.executeBatch();
        } catch (SQLException e) {

            return new int[]{};
        }
    }


    public <T> T findeOne(String sql, ResultSetExtractor<T> resultSetExtractor, Object... objects) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int rowNum = 1;
            for (Object object : objects) {
                statement.setObject(rowNum++, object);
            }
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSetExtractor.extractData(resultSet);
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public <T> T findeOne(String sql, ResultSetExtractor<T> resultSetExtractor) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSetExtractor.extractData(resultSet);
            }
        } catch (SQLException e) {

        }
        return null;
    }
}
