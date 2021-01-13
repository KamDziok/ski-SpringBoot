package pl.ski.transaction;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;


public interface ITransactionRepository extends PagingAndSortingRepository<Transaction, Long> {
    List<Transaction> findAllByStartTransactionBetweenAndStartTransactionBetween(
            Date startBegin, Date startEnd,
            Date stopBegin, Date stopEnd
    );
}
