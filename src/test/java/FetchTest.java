import com.webtechnology.config.AppConfig;
import com.webtechnology.model.Country;
import com.webtechnology.model.Style;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IO on 12.11.2016.
 */
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
public class FetchTest {


    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;


    @Test
    public void TestEntityManagerFactory() {

    }


    @Test
    @Transactional
    public void FetchJoin() {
//        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        entityManager2.getTransaction().begin();
        entityManager2.setFlushMode(FlushModeType.AUTO);

        Style style2 = entityManager2.find(Style.class, 4L);

        Style style3 = new Style("jpqejr");

        style2.setTitle("change");

        entityManager2.persist(style3);
        TypedQuery<Style> query = entityManager2.createQuery("select s from Style s where s.title = 'jpqejr'", Style.class);

        List<Style> result = query.getResultList();
        style2.setTitle("changetwo");

        entityManager2.remove(style2);
        style2.setTitle("change");




        entityManager2.getTransaction().rollback();

    }


}
