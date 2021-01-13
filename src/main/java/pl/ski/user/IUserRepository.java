package pl.ski.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;

public interface IUserRepository extends PagingAndSortingRepository<User, Long> {
    List<User> findAllByIdNot(Long id);
    boolean existsUserByeMail(String eMail);
    User findByeMailAndPassword(String eMail, String password);
}
