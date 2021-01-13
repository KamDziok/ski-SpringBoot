package pl.ski.offer_ski;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface IOfferSkiRepository extends PagingAndSortingRepository<OfferSki, Long> {
    List<OfferSki> findByCity(String city);
    List<OfferSki> findAllByCityAndStartOfferLessThanAndStopOfferGreaterThan(String city, Date dateStart, Date dateStop);
    List<OfferSki> findAllByStartOfferGreaterThanAndStopOfferLessThanOrStopOffer(Date startOffer, Date stopOffer, Date nullDate);
    List<OfferSki> findAllByStartOfferAfterAndStopOfferBeforeOrStopOfferIsNull(Date startOffer, Date stopOffer);
    List<OfferSki> findAllByCityAndStartOfferAfterAndStopOfferBeforeOrStopOfferIsNull(String city, Date startOffer, Date stopOffer);
}
