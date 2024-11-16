package org.acumen.training.codes.controller.test;

import org.acumen.training.codes.model.data.Sailor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations ="file:src/main/webapp/WEB-INF/context/applicationContext.xml")
public class SailorRestControllerTest {

    private static final String BASE_URL = "http://localhost:8080/gallardoweb/rest/bms/sailor";
    private static final Logger LOGGER = LogManager.getLogger(SailorRestControllerTest.class);

    @Test
    public void testAddSailor() {
        RestTemplate client = new RestTemplate();
        Sailor sailor = new Sailor();
        sailor.setName("Lebron James");
        sailor.setAge(24);
        sailor.setRating(10);
        HttpEntity<Sailor> request = new HttpEntity<>(sailor);
        Sailor response = client.postForObject(BASE_URL + "/add", request, Sailor.class);
        LOGGER.info("Added sailor: {}", response);
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void testListAllSailors() {
        RestTemplate client = new RestTemplate();
        ResponseEntity<List> response = client.exchange(
                BASE_URL + "/list/all", HttpMethod.GET, null, List.class);
        LOGGER.info("List of sailors: {}", response.getBody());
    }

    @Test
    public void testDeleteSailor() {
        RestTemplate client = new RestTemplate();
        ResponseEntity<String> response = client.exchange(
                BASE_URL + "/delete/36", HttpMethod.DELETE, null, String.class);
        LOGGER.info("Delete response: {}", response.getBody());
    }

    @Test
    public void testUpdateSailorName() {
        RestTemplate client = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        String url = "http://localhost:8080/gallardoweb/rest/bms/sailor/update/name?id=37&name=SemiName";
        ResponseEntity<Sailor> response = client.exchange(
                url, HttpMethod.PATCH, null, Sailor.class);
        LOGGER.info("Updated sailor: {}", response.getBody());
    }

    @Test
    public void testUpdateAllSailorDetails() {
        RestTemplate client = new RestTemplate();
        Sailor sailor = new Sailor();
        sailor.setId(37);
        sailor.setName("John Doe");
        sailor.setAge(30);
        sailor.setRating(9);
        HttpEntity<Sailor> request = new HttpEntity<>(sailor);
        ResponseEntity<Sailor> response = client.exchange(
                BASE_URL + "/update/all", HttpMethod.PUT, request, Sailor.class);
        LOGGER.info("Updated sailor details: {}", response.getBody());
    }
}
