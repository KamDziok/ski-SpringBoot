package pl.ski.ski;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.ski.producer.Producer;
import pl.ski.ski.Ski;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SkiControllerTestIntegration {

    @LocalServerPort
    private int port;

    private final String BASE_URL = "http://localhost:";

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void addSkiTest(){
        Producer producer = new Producer("test");
        Ski ski = new Ski("test", 175, "zjazdowe", null);

        ResponseEntity<Producer> responseEntityProducer = restTemplate.postForEntity(BASE_URL + port + "/api/producer", producer, Producer.class);
        producer = responseEntityProducer.getBody();
        ski.setProducer(producer);
        ResponseEntity<Ski> responseEntitySki = restTemplate.postForEntity(BASE_URL + port + "/api/ski", ski, Ski.class);
        ski = responseEntitySki.getBody();

        assertEquals(HttpStatus.OK, responseEntitySki.getStatusCode());
        assertEquals(responseEntitySki.getBody().getLengthSki(), ski.getLengthSki());
        assertEquals(responseEntitySki.getBody().getName(), ski.getName());
        assertEquals(responseEntitySki.getBody().getType(), ski.getType());
        assertEquals(responseEntitySki.getBody().getProducer().getId(), ski.getProducer().getId());
        assertNotNull(responseEntitySki.getBody());

        restTemplate.exchange(BASE_URL + port + "/api/ski", HttpMethod.DELETE, new HttpEntity<>(ski), Ski.class);
        restTemplate.exchange(BASE_URL + port + "/api/producer", HttpMethod.DELETE, new HttpEntity<>(producer), Producer.class);
    }

    @Test
    public void updateSkiTest(){
        Producer producer = new Producer("test");
        Ski ski = new Ski("test", 175, "zjazdowe", null);

        ResponseEntity<Producer> responseEntityProducer = restTemplate.postForEntity(BASE_URL + port + "/api/producer", producer, Producer.class);
        producer = responseEntityProducer.getBody();
        ski.setProducer(producer);
        ResponseEntity<Ski> responseEntitySki = restTemplate.postForEntity(BASE_URL + port + "/api/ski", ski, Ski.class);
        ski = responseEntitySki.getBody();

        ski.setLengthSki(199);
        ski.setName("test2");

        responseEntitySki = restTemplate.exchange(BASE_URL + port + "/api/ski", HttpMethod.PUT, new HttpEntity<>(ski), Ski.class);

        assertEquals(HttpStatus.OK, responseEntitySki.getStatusCode());
        assertEquals(responseEntitySki.getBody().getLengthSki(), ski.getLengthSki());
        assertEquals(responseEntitySki.getBody().getName(), ski.getName());
        assertEquals(responseEntitySki.getBody().getType(), ski.getType());
        assertEquals(responseEntitySki.getBody().getProducer().getId(), ski.getProducer().getId());
        assertNotNull(responseEntitySki.getBody());

        restTemplate.exchange(BASE_URL + port + "/api/ski", HttpMethod.DELETE, new HttpEntity<>(ski), Ski.class);
        restTemplate.exchange(BASE_URL + port + "/api/producer", HttpMethod.DELETE, new HttpEntity<>(producer), Producer.class);
    }

    @Test
    public void deleteSkiTest(){
        Producer producer = new Producer("test");
        Ski ski = new Ski("test", 175, "zjazdowe", null);

        ResponseEntity<Producer> responseEntityProducer = restTemplate.postForEntity(BASE_URL + port + "/api/producer", producer, Producer.class);
        producer = responseEntityProducer.getBody();
        ski.setProducer(producer);
        ResponseEntity<Ski> responseEntitySki = restTemplate.postForEntity(BASE_URL + port + "/api/ski", ski, Ski.class);
        ski = responseEntitySki.getBody();

        responseEntitySki = restTemplate.exchange(BASE_URL + port + "/api/ski", HttpMethod.DELETE, new HttpEntity<>(ski), Ski.class);

        assertEquals(HttpStatus.OK, responseEntitySki.getStatusCode());
        assertEquals(responseEntitySki.getBody().getLengthSki(), ski.getLengthSki());
        assertEquals(responseEntitySki.getBody().getName(), ski.getName());
        assertEquals(responseEntitySki.getBody().getType(), ski.getType());
        assertEquals(responseEntitySki.getBody().getProducer().getId(), ski.getProducer().getId());
        assertNotNull(responseEntitySki.getBody());

        restTemplate.exchange(BASE_URL + port + "/api/producer", HttpMethod.DELETE, new HttpEntity<>(producer), Producer.class);
    }
}
