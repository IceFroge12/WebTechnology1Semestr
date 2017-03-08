package model;

import annotation.Entity;
import com.webtechnology.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import util.EntityManager;
import util.EntityUtil;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by IO on 25.12.2016.
 */

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
public class StyleEntityTest {

    @Resource
    private DataSource dataSource;

    @Test
    public void getId() throws Exception {



        EntityManager entityManager = new EntityManager(dataSource);
        entityManager.startTransaction();
        StyleEntity style1 = entityManager.getById(2, StyleEntity.class);
        style1.setTitle("testUpdate");
        entityManager.update(style1, StyleEntity.class);
        entityManager.endTransaction();
    }

}