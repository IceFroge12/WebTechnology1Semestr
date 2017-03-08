import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IO on 11.11.2016.
 */
public interface ResultSetExtractor<T> {
    T extractData(ResultSet resultSet) throws SQLException;
}
