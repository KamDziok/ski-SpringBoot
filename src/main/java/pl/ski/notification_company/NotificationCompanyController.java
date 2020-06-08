package pl.ski.notification_company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/notification-company")
@CrossOrigin
public class NotificationCompanyController {
    @Autowired
    private NotificationCompanyRepository notificationCompanyRepository;

    @GetMapping
    private List<NotificationCompany> getAllNotificationCompany(){
        return (List<NotificationCompany>) notificationCompanyRepository.findAll();
    }

    @PostMapping
    private NotificationCompany addNotificationCompany(@RequestBody NotificationCompany notificationCompany){
        return notificationCompanyRepository.save(notificationCompany);
    }

    @PutMapping
    private NotificationCompany updateNotificationCompany(@RequestBody NotificationCompany notificationCompany){
        return notificationCompanyRepository.save(notificationCompany);
    }

    @DeleteMapping
    private NotificationCompany deleteNotificationCompany(@RequestBody NotificationCompany notificationCompany){
        notificationCompanyRepository.delete(notificationCompany);
        return notificationCompany;
    }
}
