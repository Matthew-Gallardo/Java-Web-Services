package org.acumen.training.codes.controller.test;

import org.acumen.training.codes.model.data.Boat;
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

import java.util.List;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/context/applicationContext.xml"})
public class BoatRestControllerTest {

    private static final String BASE_URL = "http://localhost:8080/gallardoweb/rest/bms/boat";
    private static final Logger LOGGER = LogManager.getLogger(BoatRestControllerTest.class);

    @Test
    public void testAddBoat() {
        RestTemplate client = new RestTemplate();
        Boat boat = new Boat();
        boat.setName("Black Pearl");
        boat.setColour("Black");
        HttpEntity<Boat> request = new HttpEntity<>(boat);
        Boat response = client.postForObject(BASE_URL + "/add", request, Boat.class);
        LOGGER.info("Added boat: {}", response);
    }

    @SuppressWarnings("rawtypes")
	@Test
    public void testListAllBoats() {
        RestTemplate client = new RestTemplate();
        ResponseEntity<List> response = client.exchange(
                BASE_URL + "/list/all", HttpMethod.GET, null, List.class);
        LOGGER.info("List of boats: {}", response.getBody());
    }
}
