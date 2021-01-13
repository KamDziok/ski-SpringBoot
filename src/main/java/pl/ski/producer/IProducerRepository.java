package pl.ski.producer;

import org.springframework.data.repository.PagingAndSortingRepository;


public interface IProducerRepository extends PagingAndSortingRepository<Producer, Long> {
}
