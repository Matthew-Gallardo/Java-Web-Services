package org.acumen.training.codes.dao.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.acumen.training.codes.dao.SailorDao;
import org.acumen.training.codes.model.data.Sailor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/context/applicationContext.xml",
"file:src/main/webapp/WEB-INF/context/jdbcContext.xml"})
@Transactional(transactionManager = "txManager")
@Rollback(value = true)
@WebAppConfiguration
public class TestSailorDao {

    @Autowired
    private SailorDao dao;

    private Sailor sailor;

    @BeforeEach
    public void setup() {
        sailor = new Sailor();
    }

    @Test
    public void addSailor() {
        sailor.setName("John Doe");
        sailor.setAge(30);

        dao.addSailor(sailor);
        assertTrue(sailor.getId() > 0);
    }

    @Test
    public void updateSailor() {
        sailor.setName("John Updated");
        sailor.setAge(32);
        
        dao.updateSailor(sailor);
    }

    @Test
    public void deleteSailor() {
        dao.deleteSailor(35);
    }

    @Test
    public void testGetAllSailors() {
        List<Sailor> sailors = dao.getAllSailors();
        sailors.forEach((sailor)->{
        	System.out.println(sailor);
        }); 
    }

    @Test
    public void testGetSailorById() {
        Sailor sailor = dao.getSailorById(22);
        System.out.println(sailor);
    }
}
