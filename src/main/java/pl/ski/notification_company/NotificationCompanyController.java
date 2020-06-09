package pl.ski.notification_company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ski.company.Company;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/notification-company")
@CrossOrigin
public class NotificationCompanyController {

    private int maxNumberOfNotification = 10;

    @Autowired
    private NotificationCompanyRepository notificationCompanyRepository;

    @GetMapping
    List<NotificationCompany> getAllNotificationCompany(){
        return (List<NotificationCompany>) notificationCompanyRepository.findAll();
    }

    List<NotificationCompany> getNotificationToCompany(Company company){
        List<NotificationCompany> notificationCompanies = (List<NotificationCompany>) notificationCompanyRepository.findAll();
        List<NotificationCompany> notificationCompanyList = new ArrayList<>();
        notificationCompanies.forEach(notificationCompany -> {
            if(notificationCompany.getCompany().getId() == company.getId()){
                notificationCompanyList.add(notificationCompany);
            }
        });
        return notificationCompanyList;
    }

    @PostMapping
    NotificationCompany addNotificationCompany(@RequestBody NotificationCompany notificationCompany){
        if(notificationCompany.checkUser()){
            List<NotificationCompany> now = getNotificationToCompany(notificationCompany.getCompany());
            if((now.size() + 1) >= maxNumberOfNotification){
                notificationCompany.getCompany().setActive(false);
            }
            return notificationCompanyRepository.save(notificationCompany);
        }
        return null;
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
