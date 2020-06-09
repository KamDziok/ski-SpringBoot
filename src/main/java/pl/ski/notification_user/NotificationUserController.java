package pl.ski.notification_user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ski.static_values.Permission;
import pl.ski.user.User;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/notification-user")
@CrossOrigin
public class NotificationUserController {

    private int maxNumberOfNotification = 3;

    @Autowired
    private NotificationUserRepository notificationUserRepository;

    public void setNotificationUserRepository(NotificationUserRepository notificationUserRepository) {
        this.notificationUserRepository = notificationUserRepository;
    }

    @GetMapping
    List<NotificationUser> getAllNotificationUser(){
        return (List<NotificationUser>) notificationUserRepository.findAll();
    }

    List<NotificationUser> getNotificationToUser(User user){
        List<NotificationUser> notificationUserList = (ArrayList<NotificationUser>) notificationUserRepository.findAll();
        List<NotificationUser> result = new ArrayList<>();
        notificationUserList.forEach(notificationUser -> {
            if(notificationUser.getUser().getId() == user.getId()){
                result.add(notificationUser);
            }
        });
        return result;
    }

    @PostMapping
    NotificationUser addNotificationUser(@RequestBody NotificationUser notificationUser){
        if(notificationUser.checkUser()) {
            List<NotificationUser> now = getNotificationToUser(notificationUser.getUser());
            if ((now.size() + 1) >= maxNumberOfNotification) {
                notificationUser.getUser().setPermissions(Permission.ban);
            }
            return notificationUserRepository.save(notificationUser);
        }
        return null;
    }

    @PutMapping
    private NotificationUser updateNotificationUser(@RequestBody NotificationUser notificationUser){
        return notificationUserRepository.save(notificationUser);
    }

    @DeleteMapping
    private NotificationUser deleteNotificationUser(@RequestBody NotificationUser notificationUser){
        notificationUserRepository.delete(notificationUser);
        return notificationUser;
    }

}
