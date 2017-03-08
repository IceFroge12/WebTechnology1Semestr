import org.postgresql.ds.PGPoolingDataSource;

import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Created by IO on 11.11.2016.
 */
public class DataSourceObject {

    private DataSource dataSource;

    private String databasePassword = "admin";

    private String databaseServerName = "localhost:5432";

    private String databaseUsername = "admin";

    private String databaseName = "wt";

    private int maxConnections = 8;

    private DataSourceObject() {
        try {
            dataSource = setUpDataSource();

        } catch (NamingException e) {

        }
    }

    private static class DataSourceSingletonHolder{
        public static final DataSourceObject HOLDER_INSTANCE = new DataSourceObject();
        private DataSourceSingletonHolder() {
        }
    }

    public static DataSource getInstance() {
        return DataSourceSingletonHolder.HOLDER_INSTANCE.getDataSource();
    }

    private DataSource setUpDataSource() throws NamingException {
        PGPoolingDataSource pgDataSource = new PGPoolingDataSource();
        pgDataSource.setDataSourceName("dataSource");
//        pgDataSource.setServerName(databaseServerName);
//        pgDataSource.setDatabaseName(databaseName);
        pgDataSource.setUser(databaseUsername);
        pgDataSource.setPassword(databasePassword);
        pgDataSource.setMaxConnections(maxConnections);
        pgDataSource.setUrl("jdbc:postgresql://localhost:5432/wt");
        return pgDataSource;
    }

    private DataSource getDataSource(){
        return dataSource;
    }

}
