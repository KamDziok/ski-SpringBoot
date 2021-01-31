package pl.ski.offerSki;

import org.junit.jupiter.api.Test;
import pl.ski.dataBase.DataBaseOfferSki;
import pl.ski.dataBase.DataBaseTransaction;
import pl.ski.offer_ski.IOfferSkiRepository;
import pl.ski.offer_ski.OfferSki;
import pl.ski.offer_ski.OfferSkiController;
import pl.ski.transaction.ITransactionRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OfferSkiTestUnit {

    static private Date createDate(int year, int month, int day){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try{
            date = format.parse((year + "-" + month + "-" + day));
        }catch (Exception e){ }
        return date;
    }

    static private Date startDate(){
        return createDate(2021, 01, 02);
    }

    static private Date stopDate(){
        return createDate(2021, 01, 04);
    }

    private static String dateToString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }

    static private IOfferSkiRepository prepareOfferSkiData(){
        IOfferSkiRepository iOfferSkiRepository = mock(IOfferSkiRepository.class);
        when(iOfferSkiRepository.findAllByStartOfferGreaterThanAndStopOfferLessThanOrStopOffer(
                OfferSkiTestUnit.startDate(), OfferSkiTestUnit.stopDate(), null)
        ).thenReturn(DataBaseOfferSki.getAllBetweenDate());
        when(iOfferSkiRepository.findAllByCityLikeAndStartOfferBeforeAndStopOfferAfterOrCityLikeAndStartOfferBeforeAndStopOfferIsNull(
                "Zakopane", OfferSkiTestUnit.startDate(), OfferSkiTestUnit.stopDate(), "Zakopane", OfferSkiTestUnit.startDate())
        ).thenReturn(DataBaseOfferSki.getAllBetweenDateAndCityZakopane());
        return iOfferSkiRepository;
    }

    static private ITransactionRepository prepareTransactionData() {
        ITransactionRepository iTransactionRepository = mock(ITransactionRepository.class);
        when(iTransactionRepository.findAllByStartTransactionBetweenAndStartTransactionBetween(
                OfferSkiTestUnit.startDate(), OfferSkiTestUnit.stopDate(),
                OfferSkiTestUnit.startDate(), OfferSkiTestUnit.stopDate()
        )).thenReturn(DataBaseTransaction.getAllTransactionBetweenDate());
        return iTransactionRepository;
    }

    private static OfferSkiController prepareOfferSkiController(){
        IOfferSkiRepository iOfferSkiRepository = OfferSkiTestUnit.prepareOfferSkiData();
        ITransactionRepository iTransactionRepository = OfferSkiTestUnit.prepareTransactionData();
        OfferSkiController offerSkiController = new OfferSkiController();
        offerSkiController.setiOfferSkiRepository(iOfferSkiRepository);
        offerSkiController.setiTransactionRepository(iTransactionRepository);
        return offerSkiController;
    }

    @Test
    void countReadyOfferSkiTest(){
        OfferSkiController offerSkiController = OfferSkiTestUnit.prepareOfferSkiController();
        List<OfferSki> offerSkiList = offerSkiController.countReadyOfferSki(
                DataBaseOfferSki.getAllBetweenDateAndCityZakopane(), DataBaseTransaction.getAllTransactionBetweenDate());
        assertThat(offerSkiList, hasSize(3));
        assertEquals(offerSkiList.get(0).getQuantity(), 2);
        assertEquals(offerSkiList.get(1).getQuantity(), 4);
        assertEquals(offerSkiList.get(2).getQuantity(), 3);
    }

    @Test
    void getOfferSkiActiveAndBetweenDateAndCityTest(){
        OfferSkiController offerSkiController = OfferSkiTestUnit.prepareOfferSkiController();
        List<OfferSki> offerSkiList = offerSkiController.getOfferSkiActiveAndBetweenDateAndCity(
                OfferSkiTestUnit.dateToString(OfferSkiTestUnit.startDate()), OfferSkiTestUnit.dateToString(OfferSkiTestUnit.stopDate()), "Zakopane");
        assertThat(offerSkiList, hasSize(3));
        assertEquals(offerSkiList.get(0).getQuantity(), 2);
        assertEquals(offerSkiList.get(1).getQuantity(), 4);
        assertEquals(offerSkiList.get(2).getQuantity(), 3);
    }
}
