package pl.ski.notification_user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/notification-user")
@CrossOrigin
public class NotificationUserController {

    @Autowired
    private NotificationUserRepository notificationUserRepository;

    @GetMapping
    private List<NotificationUser> getAllNotificationUser(){
        return (List<NotificationUser>) notificationUserRepository.findAll();
    }

    @PostMapping
    private NotificationUser addNotificationUser(@RequestBody NotificationUser notificationUser){
        return notificationUserRepository.save(notificationUser);
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
