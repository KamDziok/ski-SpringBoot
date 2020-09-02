package pl.ski.transaction;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;


public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {
    List<Transaction> findAllByStopTransactionLessThan(Date date);
    List<Transaction> findAllByStopTransactionAfter(Date date);
}
