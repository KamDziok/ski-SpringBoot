package pl.ski.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.ski.static_values.Permission;
import pl.ski.user.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserControllerTestIntegration {

    @LocalServerPort
    private int port;

    private final String BASE_URL = "http://localhost:";

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void getUserByLoginTest(){
        User user = new User("imie", "nazwisko", "emial", "haslo", Permission.user);

        ResponseEntity<User> responseEntity = restTemplate.postForEntity(BASE_URL + port + "/api/user", user, User.class);
        user = responseEntity.getBody();

        responseEntity = restTemplate.getForEntity(BASE_URL + port + "/api/user/email/" + user.geteMail() + "/password/" + user.getPassword(), User.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseEntity.getBody().getFirstName(), user.getFirstName());
        assertEquals(responseEntity.getBody().getLastName(), user.getLastName());
        assertEquals(responseEntity.getBody().geteMail(), user.geteMail());
        assertEquals(responseEntity.getBody().getPassword(), user.getPassword());
        assertEquals(responseEntity.getBody().getPermissions(), user.getPermissions());
        assertNotNull(responseEntity.getBody());

        restTemplate.exchange(BASE_URL + port + "/api/user", HttpMethod.DELETE, new HttpEntity<>(user), User.class);
    }

    @Test
    public void addUserTest(){
        User user = new User("imie", "nazwisko", "e-mial", "haslo", Permission.user);

        ResponseEntity<User> responseEntity = restTemplate.postForEntity(BASE_URL + port + "/api/user", user, User.class);
        user = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseEntity.getBody().getFirstName(), user.getFirstName());
        assertEquals(responseEntity.getBody().getLastName(), user.getLastName());
        assertEquals(responseEntity.getBody().geteMail(), user.geteMail());
        assertEquals(responseEntity.getBody().getPassword(), user.getPassword());
        assertEquals(responseEntity.getBody().getPermissions(), user.getPermissions());
        assertNotNull(responseEntity.getBody());

        restTemplate.exchange(BASE_URL + port + "/api/user", HttpMethod.DELETE, new HttpEntity<>(user), User.class);
    }

    @Test
    public void updateUserTest(){
        User user = new User("imie", "nazwisko", "e-mial", "haslo", Permission.user);

        ResponseEntity<User> responseEntity = restTemplate.postForEntity(BASE_URL + port + "/api/user", user, User.class);
        user = responseEntity.getBody();

        user.setFirstName("imie2");
        user.setPermissions(Permission.admin);
        responseEntity = restTemplate.exchange(BASE_URL + port + "/api/user", HttpMethod.PUT, new HttpEntity<>(user), User.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseEntity.getBody().getFirstName(), user.getFirstName());
        assertEquals(responseEntity.getBody().getLastName(), user.getLastName());
        assertEquals(responseEntity.getBody().geteMail(), user.geteMail());
        assertEquals(responseEntity.getBody().getPassword(), user.getPassword());
        assertEquals(responseEntity.getBody().getPermissions(), user.getPermissions());
        assertNotNull(responseEntity.getBody());

        restTemplate.exchange(BASE_URL + port + "/api/user", HttpMethod.DELETE, new HttpEntity<>(user), User.class);
    }

    @Test
    public void deleteUserTest(){
        User user = new User("imie", "nazwisko", "e-mial", "haslo", Permission.user);

        ResponseEntity<User> responseEntity = restTemplate.postForEntity(BASE_URL + port + "/api/user", user, User.class);
        user = responseEntity.getBody();

        responseEntity = restTemplate.exchange(BASE_URL + port + "/api/user", HttpMethod.DELETE, new HttpEntity<>(user), User.class);
        user = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseEntity.getBody().getFirstName(), user.getFirstName());
        assertEquals(responseEntity.getBody().getLastName(), user.getLastName());
        assertEquals(responseEntity.getBody().geteMail(), user.geteMail());
        assertEquals(responseEntity.getBody().getPassword(), user.getPassword());
        assertEquals(responseEntity.getBody().getPermissions(), user.getPermissions());
        assertNotNull(responseEntity.getBody());
    }
}
