package pl.ski.notification_user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.ski.company.Company;
import pl.ski.static_values.Permission;
import pl.ski.user.User;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class NotificationUserTestU {

    private static NotificationUser notificationUserAdmin;
    private static NotificationUser notificationUserSupport;
    private static NotificationUser notificationUserCompany;
    private static NotificationUser notificationUserUser;
    private static NotificationUser notificationUserBan;

    @BeforeAll
    static void prepareNotificationUserData(){
        User userAdmin = new User("test", "pierwszy", "test@1", "123", Permission.admin);
        userAdmin.setId((long) 1);
        User userSupport = new User("test", "drugi", "test@2", "123", Permission.support);
        userSupport.setId((long) 2);
        User userCompany = new User("test", "trzeci", "test@3", "123", Permission.company);
        userCompany.setId((long) 3);
        User userUser = new User("test", "czwarty", "test@4", "123", Permission.user);
        userUser.setId((long) 4);
        User userBan = new User("test", "piąty", "test@5", "123", Permission.ban);
        userBan.setId((long) 5);

        Company company = new Company("FirmaTest", true, "FirmaTest test");
        company.setId((long) 1);

        notificationUserAdmin = new NotificationUser("testowe zgłoszenie", userAdmin, company);
        notificationUserSupport = new NotificationUser("testowe zgłoszenie", userSupport, company);
        notificationUserCompany = new NotificationUser("testowe zgłoszenie", userCompany, company);
        notificationUserUser = new NotificationUser("testowe zgłoszenie", userUser, company);
        notificationUserBan = new NotificationUser("testowe zgłoszenie", userBan, company);
    }

    @Test
    void checkUserTestUser(){
        assertTrue(notificationUserUser.checkUser());
    }

    @Test
    void checkUserTestAdmin(){
        assertFalse(notificationUserAdmin.checkUser());
    }

    @Test
    void checkUserTestSupport(){
        assertFalse(notificationUserSupport.checkUser());
    }

    @Test
    void checkUserTestCompany(){
        assertFalse(notificationUserCompany.checkUser());
    }

    @Test
    void checkUserTestBan(){
        assertFalse(notificationUserBan.checkUser());
    }

}
