package pl.ski.user;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByeMail(String eMail);
    User findByeMailAndPassword(String eMail, String password);
}
