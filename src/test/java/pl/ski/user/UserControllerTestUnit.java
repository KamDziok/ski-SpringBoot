package pl.ski.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.ski.dataBase.DataBaseUser;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerTestUnit {

    static private IUserRepository prepareUserData() {
        IUserRepository iUserRepository = mock(IUserRepository.class);
        when(iUserRepository.findAll()).thenReturn(DataBaseUser.getAllUser());
        when(iUserRepository.findByeMailAndPassword("test@1", "123")).thenReturn(DataBaseUser.getUser1());
        when(iUserRepository.findByeMailAndPassword("test@2", "123")).thenReturn(DataBaseUser.getUser1());
        when(iUserRepository.findByeMailAndPassword("test@3", "123")).thenReturn(DataBaseUser.getUserCompany1());
        when(iUserRepository.findByeMailAndPassword("test@5", "123")).thenReturn(DataBaseUser.getUserCompany2());
        when(iUserRepository.findByeMailAndPassword("test@4", "123")).thenReturn(DataBaseUser.getUserAdmin());
        when(iUserRepository.existsUserByeMail("test@1")).thenReturn(true);
        when(iUserRepository.existsUserByeMail("test@2")).thenReturn(true);
        when(iUserRepository.existsUserByeMail("test@3")).thenReturn(true);
        when(iUserRepository.existsUserByeMail("test@4")).thenReturn(true);
        when(iUserRepository.existsUserByeMail("test@5")).thenReturn(true);
        return iUserRepository;
    }

    private UserController prepareUserController(){
        IUserRepository iUserRepository = prepareUserData();
        UserController userController = new UserController();
        userController.setIUserRepository(iUserRepository);
        return userController;
    }

    @Test
    void getAllUserTest(){
        UserController userController = prepareUserController();
        List<User> userList = userController.getAllUser();
        assertThat(userList, hasSize(5));
    }

    @Test
    void getUserTestTrue(){
        UserController userController = prepareUserController();
        User user1 = userController.getUserByeMailAndPassword("test@1", "123");
        assertEquals(user1.getId(), DataBaseUser.getUser1().getId());
        assertEquals(user1.getPermissions(), DataBaseUser.getUser1().getPermissions());
        assertEquals(user1.geteMail(), DataBaseUser.getUser1().geteMail());
        assertEquals(user1.getPassword(), DataBaseUser.getUser1().getPassword());
        assertEquals(user1.getFirstName(), DataBaseUser.getUser1().getFirstName());
        assertEquals(user1.getLastName(), DataBaseUser.getUser1().getLastName());
        assertEquals(user1.getCompany(), DataBaseUser.getUser1().getCompany());
    }

    @Test
    void getUserTestFalsePassword(){
        UserController userController = prepareUserController();
        User user1 = userController.getUserByeMailAndPassword("test@1", "123456");
        assertEquals(user1, null);
    }

    @Test
    void getUserTestFalseEmail(){
        UserController userController = prepareUserController();
        User user1 = userController.getUserByeMailAndPassword("test1", "123");
        assertEquals(user1, null);
    }

    @Test
    void getUserTestFalse(){
        UserController userController = prepareUserController();
        User user1 = userController.getUserByeMailAndPassword("test1", "123456");
        assertEquals(user1, null);
    }
}
