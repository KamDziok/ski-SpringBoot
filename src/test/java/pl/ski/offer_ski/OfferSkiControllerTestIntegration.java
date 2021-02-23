package pl.ski.offer_ski;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import pl.ski.company.Company;
import pl.ski.producer.Producer;
import pl.ski.ski.Ski;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class OfferSkiControllerTestIntegration {

    static private Date createDate(int year, int month, int day){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try{
            date = format.parse((year + "-" + month + "-" + day));
        }catch (Exception e){ }
        return date;
    }

    @LocalServerPort
    private int port;

    private final String BASE_URL = "http://localhost:";

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void addOfferSkiTest(){
        Company company = new Company("test", true);
        Producer producer = new Producer("test");
        Ski ski = new Ski("test", 175, "zjazdowe", null);
        OfferSki offerSki = new OfferSki("miasto", OfferSkiControllerTestIntegration.createDate(2021, 01, 01),
            OfferSkiControllerTestIntegration.createDate(2021, 01, 02), 5, null, 12.99, null);

        ResponseEntity<Company> responseEntityCompany = restTemplate.postForEntity(BASE_URL + port + "/api/company", company, Company.class);
        company = responseEntityCompany.getBody();
        ResponseEntity<Producer> responseEntityProducer = restTemplate.postForEntity(BASE_URL + port + "/api/producer", producer, Producer.class);
        producer = responseEntityProducer.getBody();
        ski.setProducer(producer);
        ResponseEntity<Ski> responseEntitySki = restTemplate.postForEntity(BASE_URL + port + "/api/ski", ski, Ski.class);
        ski = responseEntitySki.getBody();

        offerSki.setCompany(company);
        offerSki.setSki(ski);
        ResponseEntity<OfferSki> responseEntity = restTemplate.postForEntity(BASE_URL + port + "/api/offer-ski", offerSki, OfferSki.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseEntity.getBody().getCompany().getId(), offerSki.getCompany().getId());
        assertEquals(responseEntity.getBody().getSki().getId(), offerSki.getSki().getId());
        assertEquals(responseEntity.getBody().getCity(), offerSki.getCity());
        assertEquals(responseEntity.getBody().getQuantity(), offerSki.getQuantity());
        assertEquals(responseEntity.getBody().getPriceForDay(), offerSki.getPriceForDay());
        assertEquals(responseEntity.getBody().getStartOffer(), offerSki.getStartOffer());
        assertEquals(responseEntity.getBody().getStopOffer(), offerSki.getStopOffer());
        assertNotNull(responseEntity.getBody());

        offerSki = responseEntity.getBody();

        restTemplate.exchange(BASE_URL + port + "/api/offer-ski", HttpMethod.DELETE, new HttpEntity<>(offerSki), OfferSki.class);
        restTemplate.exchange(BASE_URL + port + "/api/company", HttpMethod.DELETE, new HttpEntity<>(company), Company.class);
        restTemplate.exchange(BASE_URL + port + "/api/ski", HttpMethod.DELETE, new HttpEntity<>(ski), Ski.class);
        restTemplate.exchange(BASE_URL + port + "/api/producer", HttpMethod.DELETE, new HttpEntity<>(producer), Producer.class);
    }

    @Test
    public void updateOfferSkiTest(){
        Company company = new Company("test", true);
        Producer producer = new Producer("test");
        Ski ski = new Ski("test", 175, "zjazdowe", null);
        OfferSki offerSki = new OfferSki("miasto", OfferSkiControllerTestIntegration.createDate(2021, 01, 01),
                OfferSkiControllerTestIntegration.createDate(2021, 01, 02), 5, null, 12.99, null);

        ResponseEntity<Company> responseEntityCompany = restTemplate.postForEntity(BASE_URL + port + "/api/company", company, Company.class);
        company = responseEntityCompany.getBody();
        ResponseEntity<Producer> responseEntityProducer = restTemplate.postForEntity(BASE_URL + port + "/api/producer", producer, Producer.class);
        producer = responseEntityProducer.getBody();
        ski.setProducer(producer);
        ResponseEntity<Ski> responseEntitySki = restTemplate.postForEntity(BASE_URL + port + "/api/ski", ski, Ski.class);
        ski = responseEntitySki.getBody();

        offerSki.setCompany(company);
        offerSki.setSki(ski);
        ResponseEntity<OfferSki> responseEntity = restTemplate.postForEntity(BASE_URL + port + "/api/offer-ski", offerSki, OfferSki.class);

        offerSki = responseEntity.getBody();

        offerSki.setCity("miasto2");
        offerSki.setPriceForDay(24.98);

        responseEntity = restTemplate.exchange(BASE_URL + port + "/api/offer-ski", HttpMethod.PUT, new HttpEntity<>(offerSki), OfferSki.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseEntity.getBody().getCompany().getId(), offerSki.getCompany().getId());
        assertEquals(responseEntity.getBody().getSki().getId(), offerSki.getSki().getId());
        assertEquals(responseEntity.getBody().getCity(), offerSki.getCity());
        assertEquals(responseEntity.getBody().getQuantity(), offerSki.getQuantity());
        assertEquals(responseEntity.getBody().getPriceForDay(), offerSki.getPriceForDay());
        assertEquals(responseEntity.getBody().getStartOffer(), offerSki.getStartOffer());
        assertEquals(responseEntity.getBody().getStopOffer(), offerSki.getStopOffer());
        assertNotNull(responseEntity.getBody());

        offerSki = responseEntity.getBody();

        restTemplate.exchange(BASE_URL + port + "/api/offer-ski", HttpMethod.DELETE, new HttpEntity<>(offerSki), OfferSki.class);
        restTemplate.exchange(BASE_URL + port + "/api/company", HttpMethod.DELETE, new HttpEntity<>(company), Company.class);
        restTemplate.exchange(BASE_URL + port + "/api/ski", HttpMethod.DELETE, new HttpEntity<>(ski), Ski.class);
        restTemplate.exchange(BASE_URL + port + "/api/producer", HttpMethod.DELETE, new HttpEntity<>(producer), Producer.class);
    }

    @Test
    public void deleteOfferSkiTest(){
        Company company = new Company("test", true);
        Producer producer = new Producer("test");
        Ski ski = new Ski("test", 175, "zjazdowe", null);
        OfferSki offerSki = new OfferSki("miasto", OfferSkiControllerTestIntegration.createDate(2021, 01, 01),
                OfferSkiControllerTestIntegration.createDate(2021, 01, 02), 5, null, 12.99, null);

        ResponseEntity<Company> responseEntityCompany = restTemplate.postForEntity(BASE_URL + port + "/api/company", company, Company.class);
        company = responseEntityCompany.getBody();
        ResponseEntity<Producer> responseEntityProducer = restTemplate.postForEntity(BASE_URL + port + "/api/producer", producer, Producer.class);
        producer = responseEntityProducer.getBody();
        ski.setProducer(producer);
        ResponseEntity<Ski> responseEntitySki = restTemplate.postForEntity(BASE_URL + port + "/api/ski", ski, Ski.class);
        ski = responseEntitySki.getBody();

        offerSki.setCompany(company);
        offerSki.setSki(ski);
        ResponseEntity<OfferSki> responseEntity = restTemplate.postForEntity(BASE_URL + port + "/api/offer-ski", offerSki, OfferSki.class);

        offerSki = responseEntity.getBody();

        ResponseEntity<OfferSki> responseMS = restTemplate.exchange(BASE_URL + port + "/api/offer-ski", HttpMethod.DELETE, new HttpEntity<>(offerSki), OfferSki.class);

        assertEquals(HttpStatus.OK, responseMS.getStatusCode());
        assertEquals(responseMS.getBody().getCompany().getId(), offerSki.getCompany().getId());
        assertEquals(responseMS.getBody().getSki().getId(), offerSki.getSki().getId());
        assertEquals(responseMS.getBody().getCity(), offerSki.getCity());
        assertEquals(responseMS.getBody().getQuantity(), offerSki.getQuantity());
        assertEquals(responseMS.getBody().getPriceForDay(), offerSki.getPriceForDay());
        assertEquals(responseMS.getBody().getStartOffer(), offerSki.getStartOffer());
        assertEquals(responseMS.getBody().getStopOffer(), offerSki.getStopOffer());
        assertNotNull(responseEntity.getBody());

        restTemplate.exchange(BASE_URL + port + "/api/company", HttpMethod.DELETE, new HttpEntity<>(company), Company.class);
        restTemplate.exchange(BASE_URL + port + "/api/ski", HttpMethod.DELETE, new HttpEntity<>(ski), Ski.class);
        restTemplate.exchange(BASE_URL + port + "/api/producer", HttpMethod.DELETE, new HttpEntity<>(producer), Producer.class);
    }
}
