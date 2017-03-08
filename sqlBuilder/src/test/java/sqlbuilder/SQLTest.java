package sqlbuilder;


import com.webtechnology.config.AppConfig;
import com.webtechnology.model.Region;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;


@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
public class SQLTest {


    @Resource
    private DataSource dataSource;

    @Test
    public void testSQLSelect() {

        Session session = new Session(dataSource);
        try {
            session.beginTransaction();
            QuerySql sql1 = SqlBuilder.
                    select(SqlColumn.of("*")).
                    from(SqlTable.of("public.region"));

            QuerySql sql2 = SqlBuilder.
                    select(SqlColumn.of("*")).
                    from(SqlTable.of("public.style"));
            System.out.println(sql1.toString());
            List<Region> result = session.ExecuteQuery(sql1, extractor);

            session.closeTransaction();
        } catch (SQLException e) {
            try {
                session.closeTransaction();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        Session session = new Session(dataSource);

        try {
            session.beginTransaction();
            DeleteSql sql = SqlBuilder
                    .delete()
                    .from(SqlTable.of("public.style"))
                    .where(
                            SqlColumn.of("id").eq(5)
                                    .AND(
                                            SqlColumn.of("title").eq("test")
                                    ));
            System.out.println(sql.getSQL());
            session.ExecuteDelete(sql);
            session.closeTransaction();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testUpdate() {

        Session session = new Session(dataSource);
        try {
            session.beginTransaction();

            UpdateSql sql = SqlBuilder
                    .update(SqlTable.of("public.style"))
                    .set(SqlColumn.of("title"), "testUpdated")
                    .set(SqlColumn.of("title"), "testUpdated")
                    .where(
                            SqlColumn.of("id").eq(4)
                                    .AND(
                                            SqlColumn.of("title").eq("test")
                                    )

                    );
            System.out.println(sql);
            session.ExecuteDelete(sql);
            session.closeTransaction();
        } catch (SQLException e) {
            try {
                session.closeTransaction();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    private ResultSetExtractor<Region> extractor = resultSet -> {
        Region region = new Region();
        region.setId(resultSet.getLong("id"));
        region.setTitle(resultSet.getString("title"));
        return region;
    };
}
