package pl.ski.offer_ski;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.ski.company.Company;
import pl.ski.ski.Ski;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IOfferSkiRepository extends PagingAndSortingRepository<OfferSki, Long> {
    List<OfferSki> findByCity(String city);
    //List<OfferSki> findAllByCityAndStartOfferLessThanAndStopOfferGreaterThan(String city, Date dateStart, Date dateStop);
    List<OfferSki> findAllByStartOfferGreaterThanAndStopOfferLessThanOrStopOffer(Date startOffer, Date stopOffer, Date nullDate);
    //List<OfferSki> findAllByStartOfferAfterAndStopOfferBeforeOrStopOfferIsNull(Date startOffer, Date stopOffer);
//    List<OfferSki> findAllByCityLikeAndStartOfferAfterAndStopOfferBeforeOrStopOfferIsNull(String city, Date startOffer, Date stopOffer);
    List<OfferSki> findAllByCityAndStartOfferAfterAndStopOfferIsNull(String city, Date startOffer);
    List<OfferSki> findAllByCityLikeAndStartOfferBeforeAndStopOfferAfterOrCityLikeAndStartOfferBeforeAndStopOfferIsNull(String city, Date startOffer, Date stopOffer, String city2, Date startOffer2);
    Optional<OfferSki> findByCompanyAndSkiAndCityAndStopOfferIsNull(Company company, Ski ski, String city);
}
