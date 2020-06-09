package pl.ski.offer_ski;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface OfferSkiRepository extends PagingAndSortingRepository<OfferSki, Long> {
    List<OfferSki> findAllByCityAndStartOfferLessThanAndStopOfferGreaterThan(String city, Date date);
}
