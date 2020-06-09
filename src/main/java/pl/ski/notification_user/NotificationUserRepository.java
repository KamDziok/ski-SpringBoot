package pl.ski.notification_user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.ski.user.User;

import java.util.List;

public interface NotificationUserRepository extends PagingAndSortingRepository<NotificationUser, Long> {

    @Query("select nu from NotificationUser nu where nu.user.id = ?1")
    List<NotificationUser> findAllByUser(Long idUser);

}
