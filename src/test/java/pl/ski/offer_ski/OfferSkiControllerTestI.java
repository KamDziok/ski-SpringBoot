package pl.ski.offer_ski;

import org.junit.jupiter.api.Test;
import pl.ski.company.Company;
import pl.ski.price.Price;
import pl.ski.producer.Producer;
import pl.ski.ski.Ski;
import pl.ski.unit.Unit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OfferSkiControllerTestI {

    List<OfferSki> prepareOfferSkiData() throws ParseException {
        Company company = new Company("FirmaTest", true, "FirmaTest test");

        Unit unit = new Unit("za dzień");
        Price price = new Price(40.00, unit);

        Producer producer = new Producer("Fischer");
        Ski ski = new Ski("RC4 RACE JR", producer);

        Date dateStart = new SimpleDateFormat("dd/MM/yyyy").parse("01/05/2020");
        Date dateStop = new SimpleDateFormat("dd/MM/yyyy").parse("01/06/2020");
        Date dateStop2 = new SimpleDateFormat("dd/MM/yyyy").parse("01/09/2020");

        OfferSki offerSki = new OfferSki("Miejscowość1", dateStart, null, 2, company, price, ski);
        OfferSki offerSki1 = new OfferSki("Miejscowość1", dateStart, dateStop, 2, company, price, ski);
        OfferSki offerSki2 = new OfferSki("Miejscowość1", dateStart, dateStop2,2,  company, price, ski);
        OfferSki offerSki3 = new OfferSki("Miejscowość2", dateStart, dateStop, 2, company, price, ski);

        List<OfferSki> offerSkiList = new ArrayList<>();
        offerSkiList.add(offerSki);
        offerSkiList.add(offerSki1);
        offerSkiList.add(offerSki2);
        offerSkiList.add(offerSki3);
        return offerSkiList;
    }

    @Test
    void getOfferSkiInCityTest() throws ParseException {
        List<OfferSki> offerSkis = prepareOfferSkiData();
        List<OfferSki> offerSkis2 = prepareOfferSkiData();
        offerSkis2.remove(3);
        List<OfferSki> offerSkis3 = prepareOfferSkiData();
        offerSkis3.remove(1);
        offerSkis3.remove(2);
        OfferSkiRepository offerSkiRepository = mock(OfferSkiRepository.class);
        OfferSkiController offerSkiController = new OfferSkiController();
        offerSkiController.setOfferSkiRepository(offerSkiRepository);

        String city = "Miejscowość1";
        Date dateNow = new SimpleDateFormat("dd/MM/yyyy").parse("03/06/2020");

        when(offerSkiRepository.findAll()).thenReturn(offerSkis);
        when(offerSkiRepository.findByCity(city)).thenReturn(offerSkis2);
        when(offerSkiRepository.findAllByCityAndStartOfferLessThanAndStopOfferGreaterThan(city, dateNow, dateNow)).thenReturn(offerSkis3);

        List<OfferSki> offerSkiList = offerSkiController.getOfferSkiInCity(city);//, "03-06-2020");
        List<OfferSki> offerSkiList2 = offerSkiController.getAllOfferSki();
        List<OfferSki> offerSkiList3 = offerSkiController.getOfferSkiInCityAndDate(city, "03-06-2020");

        assertThat(offerSkiList, hasSize(3));
        assertThat(offerSkiList2, hasSize(4));
        assertThat(offerSkiList3, hasSize(2));
    }

}
