import com.webtechnology.config.AppConfig;
import com.webtechnology.model.City;
import com.webtechnology.model.Country;
import com.webtechnology.model.Region;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IO on 11.11.2016.
 */
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
public class JdbcTemplateText {

    @Resource
    private DataSource dataSource;

    private ResultSetExtractor<Region> extractor = resultSet -> {
        Region region = new Region();
        region.setId(resultSet.getLong("id"));
        region.setTitle(resultSet.getString("title"));
        return region;
    };

    private ResultSetExtractor<City> extractorCity = resultSet -> {
        City city = new City();
        city.setId(resultSet.getLong("id"));
        city.setTitle(resultSet.getString("title"));
        Country country = new Country();
        country.setId(resultSet.getLong("id_country"));
        country.setTitle(resultSet.getString("title_co"));
        city.setCountry(country);
        return city;
    };

    @Test
    public void testJdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Region> regionList = jdbcTemplate.query("select * from public.region;", extractor);
        List<Region> regionList1 = jdbcTemplate.query("select * from public.region where region.id = ?;", extractor, 1);
        String sqlShow1 = jdbcTemplate.queryShow("select * from public.region where region.id = ?;", 1);
        HashMap<String, Object> keysValues = new HashMap<>();
        keysValues.put("id", 1);



        List<Region> regionList2 = jdbcTemplate.query("select * from public.region where region.id = :id;", extractor, keysValues );
        String sqlShow2 = jdbcTemplate.queryShow("select * from public.region where region.id = :id;", keysValues);


        jdbcTemplate.insert("insert into public.region values(?,?);", 4, "title");

        List<City> cityList = jdbcTemplate.query("select c.*, co.id as id_co, co.title as title_co from public.city c left outer join public.country co on c.id_country = co.id;", extractorCity);
        System.out.println();

    }


}
