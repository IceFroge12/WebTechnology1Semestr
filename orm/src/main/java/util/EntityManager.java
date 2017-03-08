package util;


import sqlbuilder.*;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;


/**
 * Created by IO on 25.12.2016.
 */
public class EntityManager {


    private Session session;

    public EntityManager(DataSource dataSource) {
        this.session = new Session(dataSource);
    }

    public <T> T getById(int id, Class<T> instanceClass) {
        String tableName = EntityUtil.getTableName(instanceClass);
        String idColumnName = EntityUtil.extractIdColumn(instanceClass);
        QuerySql querySql = SqlBuilder
                .select(SqlColumn.of("*"))
                .from(SqlTable.of(tableName))
                .where(
                        SqlColumn.of(idColumnName).eq(id)
                );
        T result = null;
        try {
            ResultSet resultSet = session.QueryExecute(querySql);
            result = EntityUtil.extractObject(resultSet, instanceClass);
        } catch (IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public<T> void update(T instance, Class<T> instanceClass) throws SQLException {
        String tableName = EntityUtil.getTableName(instanceClass);
        String idTableColumn = EntityUtil.extractIdColumn(instanceClass);
        int id = EntityUtil.extractId(instance);
        Map<String, String> mapping = EntityUtil.getMapping(instance);
        UpdateSql sql = SqlBuilder
                .update(SqlTable.of(tableName))
                .setSegment(mapping)
                .where(
                        SqlColumn.of(idTableColumn).eq(id)


                );
        session.ExecuteDelete(sql);
    }

    public <T> void delete(T instance, Class<T> instanceClass){
        String tableName = EntityUtil.getTableName(instanceClass);
        String idTableColumn = EntityUtil.extractIdColumn(instanceClass);
        int id = EntityUtil.extractId(instance);
        DeleteSql sql = SqlBuilder
                .delete()
                .from(SqlTable.of(tableName))
                .where(
                        SqlColumn.of(tableName).eq(idTableColumn)
                );
        try {
            session.ExecuteDelete(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void startTransaction() throws SQLException {
        session.beginTransaction();
    }

    public void endTransaction() throws SQLException {
        session.closeTransaction();
    }
}
