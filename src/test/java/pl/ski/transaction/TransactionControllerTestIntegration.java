package pl.ski.transaction;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.ski.company.Company;
import pl.ski.offer_ski.OfferSki;
import pl.ski.producer.Producer;
import pl.ski.ski.Ski;
import pl.ski.static_values.Permission;
import pl.ski.user.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TransactionControllerTestIntegration {

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
    public void addTransactionTest(){
        Company company = new Company("test", true);
        Producer producer = new Producer("test");
        Ski ski = new Ski("test", 175, "zjazdowe", null);
        OfferSki offerSki = new OfferSki("miasto", TransactionControllerTestIntegration.createDate(2021, 01, 01),
                TransactionControllerTestIntegration.createDate(2021, 01, 02), 5, null, 12.99, null);
        User user = new User("imie", "nazwisko", "e-mial", "haslo", Permission.user);
        Transaction transaction = new Transaction(TransactionControllerTestIntegration.createDate(2021, 01, 01), TransactionControllerTestIntegration.createDate(2021, 01, 01),
                TransactionControllerTestIntegration.createDate(2021, 01, 03), null, null);

        ResponseEntity<Company> responseEntityCompany = restTemplate.postForEntity(BASE_URL + port + "/api/company", company, Company.class);
        company = responseEntityCompany.getBody();
        ResponseEntity<Producer> responseEntityProducer = restTemplate.postForEntity(BASE_URL + port + "/api/producer", producer, Producer.class);
        producer = responseEntityProducer.getBody();
        ski.setProducer(producer);
        ResponseEntity<Ski> responseEntitySki = restTemplate.postForEntity(BASE_URL + port + "/api/ski", ski, Ski.class);
        ski = responseEntitySki.getBody();
        ResponseEntity<User> responseEntityUser = restTemplate.postForEntity(BASE_URL + port + "/api/user", user, User.class);
        user = responseEntityUser.getBody();

        offerSki.setCompany(company);
        offerSki.setSki(ski);
        ResponseEntity<OfferSki> responseEntityOfferSki = restTemplate.postForEntity(BASE_URL + port + "/api/offer-ski", offerSki, OfferSki.class);
        offerSki = responseEntityOfferSki.getBody();
        List<OfferSki> offerSkiList = new ArrayList<>();
        offerSkiList.add(offerSki);
        offerSkiList.add(offerSki);

        transaction.setUser(user);
        transaction.setOfferSkiList(offerSkiList);
        ResponseEntity<Transaction> responseEntity = restTemplate.postForEntity(BASE_URL + port + "/api/transaction", transaction, Transaction.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertThat(responseEntity.getBody().getOfferSkiList(), hasSize(2));
        assertEquals(responseEntity.getBody().getUser().getId(), user.getId());
        assertEquals(responseEntity.getBody().getPrepareTransaction(), transaction.getPrepareTransaction());
        assertEquals(responseEntity.getBody().getStartTransaction(), transaction.getStartTransaction());
        assertEquals(responseEntity.getBody().getStopTransaction(), transaction.getStopTransaction());
        assertNotNull(responseEntity.getBody());

        transaction = responseEntity.getBody();

        restTemplate.exchange(BASE_URL + port + "/api/transaction", HttpMethod.DELETE, new HttpEntity<>(transaction), Transaction.class);
        restTemplate.exchange(BASE_URL + port + "/api/user", HttpMethod.DELETE, new HttpEntity<>(user), User.class);
        restTemplate.exchange(BASE_URL + port + "/api/offer-ski", HttpMethod.DELETE, new HttpEntity<>(offerSki), OfferSki.class);
        restTemplate.exchange(BASE_URL + port + "/api/company", HttpMethod.DELETE, new HttpEntity<>(company), Company.class);
        restTemplate.exchange(BASE_URL + port + "/api/ski", HttpMethod.DELETE, new HttpEntity<>(ski), Ski.class);
        restTemplate.exchange(BASE_URL + port + "/api/producer", HttpMethod.DELETE, new HttpEntity<>(producer), Producer.class);
    }

    @Test
    public void updateTransactionTest(){
        Company company = new Company("test", true);
        Producer producer = new Producer("test");
        Ski ski = new Ski("test", 175, "zjazdowe", null);
        OfferSki offerSki = new OfferSki("miasto", TransactionControllerTestIntegration.createDate(2021, 01, 01),
                TransactionControllerTestIntegration.createDate(2021, 01, 02), 5, null, 12.99, null);
        User user = new User("imie", "nazwisko", "e-mial", "haslo", Permission.user);
        Transaction transaction = new Transaction(TransactionControllerTestIntegration.createDate(2021, 01, 01), TransactionControllerTestIntegration.createDate(2021, 01, 01),
                TransactionControllerTestIntegration.createDate(2021, 01, 03), null, null);

        ResponseEntity<Company> responseEntityCompany = restTemplate.postForEntity(BASE_URL + port + "/api/company", company, Company.class);
        company = responseEntityCompany.getBody();
        ResponseEntity<Producer> responseEntityProducer = restTemplate.postForEntity(BASE_URL + port + "/api/producer", producer, Producer.class);
        producer = responseEntityProducer.getBody();
        ski.setProducer(producer);
        ResponseEntity<Ski> responseEntitySki = restTemplate.postForEntity(BASE_URL + port + "/api/ski", ski, Ski.class);
        ski = responseEntitySki.getBody();
        ResponseEntity<User> responseEntityUser = restTemplate.postForEntity(BASE_URL + port + "/api/user", user, User.class);
        user = responseEntityUser.getBody();

        offerSki.setCompany(company);
        offerSki.setSki(ski);
        ResponseEntity<OfferSki> responseEntityOfferSki = restTemplate.postForEntity(BASE_URL + port + "/api/offer-ski", offerSki, OfferSki.class);
        offerSki = responseEntityOfferSki.getBody();
        List<OfferSki> offerSkiList = new ArrayList<>();
        offerSkiList.add(offerSki);
        offerSkiList.add(offerSki);

        transaction.setUser(user);
        transaction.setOfferSkiList(offerSkiList);
        ResponseEntity<Transaction> responseEntity = restTemplate.postForEntity(BASE_URL + port + "/api/transaction", transaction, Transaction.class);

        offerSkiList.add(offerSki);
        transaction.setOfferSkiList(offerSkiList);
        transaction.setStopTransaction(TransactionControllerTestIntegration.createDate(2021, 01, 05));
        responseEntity = restTemplate.exchange(BASE_URL + port + "/api/transaction", HttpMethod.PUT, new HttpEntity<>(transaction), Transaction.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertThat(responseEntity.getBody().getOfferSkiList(), hasSize(3));
        assertEquals(responseEntity.getBody().getUser().getId(), user.getId());
        assertEquals(responseEntity.getBody().getPrepareTransaction(), transaction.getPrepareTransaction());
        assertEquals(responseEntity.getBody().getStartTransaction(), transaction.getStartTransaction());
        assertEquals(responseEntity.getBody().getStopTransaction(), transaction.getStopTransaction());
        assertNotNull(responseEntity.getBody());

        transaction = responseEntity.getBody();

        restTemplate.exchange(BASE_URL + port + "/api/transaction", HttpMethod.DELETE, new HttpEntity<>(transaction), Transaction.class);
        restTemplate.exchange(BASE_URL + port + "/api/user", HttpMethod.DELETE, new HttpEntity<>(user), User.class);
        restTemplate.exchange(BASE_URL + port + "/api/offer-ski", HttpMethod.DELETE, new HttpEntity<>(offerSki), OfferSki.class);
        restTemplate.exchange(BASE_URL + port + "/api/company", HttpMethod.DELETE, new HttpEntity<>(company), Company.class);
        restTemplate.exchange(BASE_URL + port + "/api/ski", HttpMethod.DELETE, new HttpEntity<>(ski), Ski.class);
        restTemplate.exchange(BASE_URL + port + "/api/producer", HttpMethod.DELETE, new HttpEntity<>(producer), Producer.class);
    }

    @Test
    public void deleteTransactionTest(){
        Company company = new Company("test", true);
        Producer producer = new Producer("test");
        Ski ski = new Ski("test", 175, "zjazdowe", null);
        OfferSki offerSki = new OfferSki("miasto", TransactionControllerTestIntegration.createDate(2021, 01, 01),
                TransactionControllerTestIntegration.createDate(2021, 01, 02), 5, null, 12.99, null);
        User user = new User("imie", "nazwisko", "e-mial", "haslo", Permission.user);
        Transaction transaction = new Transaction(TransactionControllerTestIntegration.createDate(2021, 01, 01), TransactionControllerTestIntegration.createDate(2021, 01, 01),
                TransactionControllerTestIntegration.createDate(2021, 01, 03), null, null);

        ResponseEntity<Company> responseEntityCompany = restTemplate.postForEntity(BASE_URL + port + "/api/company", company, Company.class);
        company = responseEntityCompany.getBody();
        ResponseEntity<Producer> responseEntityProducer = restTemplate.postForEntity(BASE_URL + port + "/api/producer", producer, Producer.class);
        producer = responseEntityProducer.getBody();
        ski.setProducer(producer);
        ResponseEntity<Ski> responseEntitySki = restTemplate.postForEntity(BASE_URL + port + "/api/ski", ski, Ski.class);
        ski = responseEntitySki.getBody();
        ResponseEntity<User> responseEntityUser = restTemplate.postForEntity(BASE_URL + port + "/api/user", user, User.class);
        user = responseEntityUser.getBody();

        offerSki.setCompany(company);
        offerSki.setSki(ski);
        ResponseEntity<OfferSki> responseEntityOfferSki = restTemplate.postForEntity(BASE_URL + port + "/api/offer-ski", offerSki, OfferSki.class);
        offerSki = responseEntityOfferSki.getBody();
        List<OfferSki> offerSkiList = new ArrayList<>();
        offerSkiList.add(offerSki);
        offerSkiList.add(offerSki);

        transaction.setUser(user);
        transaction.setOfferSkiList(offerSkiList);
        ResponseEntity<Transaction> responseEntity = restTemplate.postForEntity(BASE_URL + port + "/api/transaction", transaction, Transaction.class);

        transaction = responseEntity.getBody();

        responseEntity = restTemplate.exchange(BASE_URL + port + "/api/transaction", HttpMethod.DELETE, new HttpEntity<>(transaction), Transaction.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertThat(responseEntity.getBody().getOfferSkiList(), hasSize(2));
        assertEquals(responseEntity.getBody().getUser().getId(), user.getId());
        assertEquals(responseEntity.getBody().getPrepareTransaction(), transaction.getPrepareTransaction());
        assertEquals(responseEntity.getBody().getStartTransaction(), transaction.getStartTransaction());
        assertEquals(responseEntity.getBody().getStopTransaction(), transaction.getStopTransaction());
        assertNotNull(responseEntity.getBody());

        restTemplate.exchange(BASE_URL + port + "/api/user", HttpMethod.DELETE, new HttpEntity<>(user), User.class);
        restTemplate.exchange(BASE_URL + port + "/api/offer-ski", HttpMethod.DELETE, new HttpEntity<>(offerSki), OfferSki.class);
        restTemplate.exchange(BASE_URL + port + "/api/company", HttpMethod.DELETE, new HttpEntity<>(company), Company.class);
        restTemplate.exchange(BASE_URL + port + "/api/ski", HttpMethod.DELETE, new HttpEntity<>(ski), Ski.class);
        restTemplate.exchange(BASE_URL + port + "/api/producer", HttpMethod.DELETE, new HttpEntity<>(producer), Producer.class);
    }
}
