package pl.ski.company;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.ski.company.Company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CompanyControllerTestIntegration {
    @LocalServerPort
    private int port;

    private final String BASE_URL = "http://localhost:";

    private RestTemplate restTemplate = new RestTemplate();

    private Company company = new Company("test", true);

    @Test
    public void addCompanyTest(){
        ResponseEntity<Company> responseEntity = restTemplate.postForEntity(BASE_URL + port + "/api/company", company, Company.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(company.getName(), responseEntity.getBody().getName());
        assertEquals(company.getActive(), responseEntity.getBody().getActive());
        assertNotNull(responseEntity.getBody());

        company = responseEntity.getBody();

        restTemplate.exchange(BASE_URL + port + "/api/company", HttpMethod.DELETE, new HttpEntity<>(company), Company.class);
    }

    @Test
    public void updateCompanyTest(){
        ResponseEntity<Company> responseEntity = restTemplate.postForEntity(BASE_URL + port + "/api/producer", company, Company.class);
        company = responseEntity.getBody();

        company.setName("test2");

        ResponseEntity<Company> responseMS  = restTemplate.exchange(BASE_URL + port + "/api/producer", HttpMethod.PUT, new HttpEntity<>(company), Company.class);

        assertEquals(HttpStatus.OK, responseMS.getStatusCode());
        assertEquals(responseMS.getBody().getId(), company.getId());
        assertEquals(responseMS.getBody().getName(), company.getName());
        assertNotNull(responseMS.getBody());

        restTemplate.exchange(BASE_URL + port + "/api/producer", HttpMethod.DELETE, new HttpEntity<>(company), Company.class);
    }

    @Test
    public void deleteCompanyTest(){
        ResponseEntity<Company> responseEntity = restTemplate.postForEntity(BASE_URL + port + "/api/company", company, Company.class);
        company = responseEntity.getBody();

        ResponseEntity<Company> responseMS  = restTemplate.exchange(BASE_URL + port + "/api/company", HttpMethod.DELETE, new HttpEntity<>(company), Company.class);

        assertEquals(HttpStatus.OK, responseMS.getStatusCode());
        assertEquals(responseMS.getBody().getId(), company.getId());
        assertEquals(responseMS.getBody().getName(), company.getName());
        assertNotNull(responseMS.getBody());
    }
}
