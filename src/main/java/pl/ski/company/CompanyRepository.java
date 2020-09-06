package pl.ski.company;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {
    Optional<Company> findById(Long id);
}
