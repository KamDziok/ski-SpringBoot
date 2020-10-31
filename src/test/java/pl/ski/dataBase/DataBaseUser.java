package pl.ski.dataBase;

import pl.ski.static_values.Permission;
import pl.ski.user.User;

import java.util.ArrayList;
import java.util.List;

public class DataBaseUser {

    public static List<User> prepareUserData() {
        User user1 = new User("test", "pierwszy", "test@1", "123", Permission.user);
        user1.setId((long) 1);
        User user2 = new User("test", "drugi", "test@2", "123", Permission.user);
        user2.setId((long) 2);

        List<User> userList = new ArrayList<>();

        userList.add(user1);
        userList.add(user2);

        return userList;
    }

}
