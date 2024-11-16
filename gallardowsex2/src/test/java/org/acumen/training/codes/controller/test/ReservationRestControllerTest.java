package org.acumen.training.codes.controller.test;

import org.acumen.training.codes.model.data.Reservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/context/applicationContext.xml")
public class ReservationRestControllerTest {

    private static final String BASE_URL = "http://localhost:8080/gallardoweb/rest/bms/reservation";
    private static final Logger LOGGER = LogManager.getLogger(ReservationRestControllerTest.class);

    @Test
    public void testAddReservation() {
        RestTemplate client = new RestTemplate();
        Reservation reservation = new Reservation();
        reservation.setSid(23);
        reservation.setBid(102);
        reservation.setDate(new Date());
        HttpEntity<Reservation> request = new HttpEntity<>(reservation);
        Reservation response = client.postForObject(BASE_URL + "/add", request, Reservation.class);
        LOGGER.info("Added reservation: {}", response);
    }

    @Test
    public void testGetReservationById() {
        RestTemplate client = new RestTemplate();
        Reservation response = client.getForObject(BASE_URL + "/id/1", Reservation.class);
        LOGGER.info("Fetched reservation: {}", response);
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void testGetReservationsByBoatId() {
        RestTemplate client = new RestTemplate();
        ResponseEntity<List> response = client.exchange(
                BASE_URL + "/boat/101", HttpMethod.GET, null, List.class);
        LOGGER.info("List of reservations for boat ID 101: {}", response.getBody());
    }
}
