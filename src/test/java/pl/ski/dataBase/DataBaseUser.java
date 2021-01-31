package pl.ski.dataBase;

import pl.ski.static_values.Permission;
import pl.ski.user.User;

import java.util.ArrayList;
import java.util.List;

public class DataBaseUser {

    public static User getUser1(){
        User user = new User("test1", "pierwszy", "test@1", "123", Permission.user);
        user.setId((long) 1);
        return user;
    }

    public static User getUser2(){
        User user = new User("test2", "drugi", "test@2", "123", Permission.user);
        user.setId((long) 2);
        return user;
    }

    public static User getUserCompany1(){
        User user = new User("test3", "trzeci", "test@3", "123", Permission.company, DataBaseCompany.getCompanyActive1());
        user.setId((long) 3);
        return user;
    }

    public static User getUserCompany2(){
        User user = new User("test5", "piaty", "test@5", "123", Permission.company, DataBaseCompany.getCompanyActive2());
        user.setId((long) 5);
        return user;
    }

    public static User getUserAdmin(){
        User user = new User("test4", "czwarty", "test@4", "123", Permission.admin);
        user.setId((long) 4);
        return user;
    }

    public static List<User> getAllUser() {
        List<User> userList = new ArrayList<>();

        userList.add(getUser1());
        userList.add(getUser2());
        userList.add(getUserCompany1());
        userList.add(getUserCompany2());
        userList.add(getUserAdmin());

        return userList;
    }

    public static List<User> getAllUserUser() {
        List<User> userList = new ArrayList<>();

        userList.add(getUser1());
        userList.add(getUser2());

        return userList;
    }

    public static List<User> getAllUserCompany() {
        List<User> userList = new ArrayList<>();

        userList.add(getUserCompany1());
        userList.add(getUserCompany2());

        return userList;
    }

    public static List<User> getAllUserAdmin() {
        List<User> userList = new ArrayList<>();

        userList.add(getUserAdmin());

        return userList;
    }
}
