package pl.ski.user;

import org.junit.jupiter.api.Test;
import pl.ski.static_values.Permission;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerTestI {

    private List<User> prepareUserData() {
        User user1 = new User("test", "pierwszy", "test@1", "123", Permission.user);
        user1.setId((long) 1);
        User user2 = new User("test", "drugi", "test@2", "123", Permission.user);
        user2.setId((long) 2);

        List<User> userList = new ArrayList<>();

        userList.add(user1);
        userList.add(user2);

        return userList;
    }

    @Test
    void getAllUserTest(){
        List<User> users = prepareUserData();
        UserRepository userRepository = mock(UserRepository.class);
        UserController userController = new UserController();
        userController.setUserRepository(userRepository);

        when(userRepository.findAll()).thenReturn(users);

        List<User> userList = userController.getAllUser();

        assertThat(userList, hasSize(2));
    }

    @Test
    void getUserTestTrue(){
        List<User> users = prepareUserData();
        User user = users.get(0);
        UserRepository userRepository = mock(UserRepository.class);
        UserController userController = new UserController();
        userController.setUserRepository(userRepository);

        when(userRepository.findByeMailAndPassword("test@1", "123")).thenReturn(user);

        User user1 = userController.getUserByeMailAndPassword("test@1", "123");

        assertEquals(user1, user);
    }

    @Test
    void getUserTestFalsePassword(){
        List<User> users = prepareUserData();
        User user = users.get(0);
        UserRepository userRepository = mock(UserRepository.class);
        UserController userController = new UserController();
        userController.setUserRepository(userRepository);

        when(userRepository.findByeMailAndPassword("test@1", "123")).thenReturn(user);

        User user1 = userController.getUserByeMailAndPassword("test@1", "123456");

        assertEquals(user1, null);
    }

    @Test
    void getUserTestFalseEmail(){
        List<User> users = prepareUserData();
        User user = users.get(0);
        UserRepository userRepository = mock(UserRepository.class);
        UserController userController = new UserController();
        userController.setUserRepository(userRepository);

        when(userRepository.findByeMailAndPassword("test@1", "123")).thenReturn(user);

        User user1 = userController.getUserByeMailAndPassword("test1", "123");

        assertEquals(user1, null);
    }

    @Test
    void getUserTestFalse(){
        List<User> users = prepareUserData();
        User user = users.get(0);
        UserRepository userRepository = mock(UserRepository.class);
        UserController userController = new UserController();
        userController.setUserRepository(userRepository);

        when(userRepository.findByeMailAndPassword("test@1", "123")).thenReturn(user);

        User user1 = userController.getUserByeMailAndPassword("test1", "123456");

        assertEquals(user1, null);
    }
}
