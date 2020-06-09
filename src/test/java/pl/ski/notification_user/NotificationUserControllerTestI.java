package pl.ski.notification_user;

import org.junit.jupiter.api.Test;
import pl.ski.company.Company;
import pl.ski.static_values.Permission;
import pl.ski.user.User;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NotificationUserControllerTestI {

    private Company prepareCompanyData() {
        return new Company("FirmaTest", true, "FirmaTest test");
    }

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

    private List<NotificationUser> prepareNotificationUserData() {
        Company company = prepareCompanyData();
        List<User> userList = prepareUserData();

        List<NotificationUser> notificationUserList = new ArrayList<>();

        notificationUserList.add(new NotificationUser("testowe zgłoszenie", userList.get(0), company));
        notificationUserList.add(new NotificationUser("testowe zgłoszenie", userList.get(0), company));
        notificationUserList.add(new NotificationUser("testowe zgłoszenie", userList.get(1), company));
        return notificationUserList;
    }

    @Test
    void getAllNotificationUserTest(){
        List<NotificationUser> notificationUsers = prepareNotificationUserData();
        NotificationUserRepository notificationUserRepository = mock(NotificationUserRepository.class);
        NotificationUserController notificationUserController = new NotificationUserController();
        notificationUserController.setNotificationUserRepository(notificationUserRepository);

        when(notificationUserRepository.findAll()).thenReturn(notificationUsers);

        List<NotificationUser> notificationUserList = notificationUserController.getAllNotificationUser();

        assertThat(notificationUserList, hasSize(3));
    }

    @Test
    void getNotificationToUserTest(){
        List<NotificationUser> notificationUsers = prepareNotificationUserData();
        NotificationUserRepository notificationUserRepository = mock(NotificationUserRepository.class);
        NotificationUserController notificationUserController = new NotificationUserController();
        notificationUserController.setNotificationUserRepository(notificationUserRepository);

        when(notificationUserRepository.findAll()).thenReturn(notificationUsers);

        List<NotificationUser> notificationUserList = notificationUserController.getNotificationToUser(notificationUsers.get(0).getUser());

        assertThat(notificationUserList, hasSize(2));
    }

    @Test
    void addNotificationUserTest(){
        List<NotificationUser> notificationUsers = prepareNotificationUserData();
        NotificationUserRepository notificationUserRepository = mock(NotificationUserRepository.class);
        NotificationUserController notificationUserController = new NotificationUserController();
        notificationUserController.setNotificationUserRepository(notificationUserRepository);

        when(notificationUserRepository.findAll()).thenReturn(notificationUsers);

        Company company = prepareCompanyData();
        User user = prepareUserData().get(0);
        NotificationUser notificationUser = new NotificationUser("testowe zgłoszenie", user, company);
        NotificationUser result = null;

        when(notificationUserRepository.save(notificationUser)).thenReturn(notificationUser);

        result = notificationUserController.addNotificationUser(notificationUser);

        assertEquals(result.getUser().getPermissions(), Permission.ban);
    }

}
