package org.acumen.training.codes.dao.test;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.acumen.training.codes.dao.BoatDao;
import org.acumen.training.codes.model.data.Boat;
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
public class TestBoatDao {

    @Autowired
    private BoatDao dao;

    private Boat boat;

    @BeforeEach
    public void setup() {
        boat = new Boat();
    }

    @Test
    public void addBoat() {
        boat.setName("Yacht");
        boat.setColour("RED");

        dao.addBoat(boat);
        assertTrue(boat.getId() > 0);  
    }

    @Test
    public void testGetAllBoats() {
        List<Boat> boats = dao.getAllBoats();
        System.out.println(boats.toString());
    }
}
