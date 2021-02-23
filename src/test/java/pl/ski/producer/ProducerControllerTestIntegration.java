package pl.ski.producer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.ski.producer.Producer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProducerControllerTestIntegration {

    @LocalServerPort
    private int port;

    private final String BASE_URL = "http://localhost:";

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void addProducerTest(){
        Producer producer = new Producer("test");
        ResponseEntity<Producer> responseEntity = restTemplate.postForEntity(BASE_URL + port + "/api/producer", producer, Producer.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseEntity.getBody().getName(), producer.getName());
        assertNotNull(responseEntity.getBody());

        restTemplate.exchange(BASE_URL + port + "/api/producer", HttpMethod.DELETE, new HttpEntity<>(producer), Producer.class);
    }

    @Test
    public void updateProducerTest(){
        Producer producer = new Producer("test");
        ResponseEntity<Producer> responseEntity = restTemplate.postForEntity(BASE_URL + port + "/api/producer", producer, Producer.class);
        producer = responseEntity.getBody();

        producer.setName("test2");

        ResponseEntity<Producer> responseMS  = restTemplate.exchange(BASE_URL + port + "/api/producer", HttpMethod.PUT, new HttpEntity<>(producer), Producer.class);

        assertEquals(HttpStatus.OK, responseMS.getStatusCode());
        assertEquals(responseMS.getBody().getId(), producer.getId());
        assertEquals(responseMS.getBody().getName(), producer.getName());
        assertNotNull(responseMS.getBody());

        restTemplate.exchange(BASE_URL + port + "/api/producer", HttpMethod.DELETE, new HttpEntity<>(producer), Producer.class);
    }

    @Test
    public void deleteProducerTest(){
        Producer producer = new Producer("test");
        ResponseEntity<Producer> responseEntity = restTemplate.postForEntity(BASE_URL + port + "/api/producer", producer, Producer.class);
        producer = responseEntity.getBody();

        ResponseEntity<Producer> responseMS  = restTemplate.exchange(BASE_URL + port + "/api/producer", HttpMethod.DELETE, new HttpEntity<>(producer), Producer.class);

        assertEquals(HttpStatus.OK, responseMS.getStatusCode());
        assertEquals(responseMS.getBody().getId(), producer.getId());
        assertEquals(responseMS.getBody().getName(), producer.getName());
        assertNotNull(responseMS.getBody());
    }
}
