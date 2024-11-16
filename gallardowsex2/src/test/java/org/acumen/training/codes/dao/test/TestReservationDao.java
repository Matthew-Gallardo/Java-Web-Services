package org.acumen.training.codes.dao.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.acumen.training.codes.dao.ReservationDao;
import org.acumen.training.codes.model.data.Reservation;
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
public class TestReservationDao {

    @Autowired
    private ReservationDao dao;

    private Reservation reservation;

    @BeforeEach
    public void setup() {
        reservation = new Reservation();
    }

    @Test
    public void addReservation() {



        dao.addReservation(reservation);
        assertNotNull(reservation.getId());
    }

    @Test
    public void testGetReservationsByBoatId() {
        List<Reservation> reservations = dao.getReservationsByBoatId(101);
        reservations.forEach((x)->{
        	System.out.println(x);
        });
    }
}
