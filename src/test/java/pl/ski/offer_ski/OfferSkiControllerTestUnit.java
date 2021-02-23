package pl.ski.offer_ski;

import org.junit.jupiter.api.Test;
import pl.ski.dataBase.DataBaseOfferSki;
import pl.ski.dataBase.DataBaseTransaction;
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

public class OfferSkiControllerTestUnit {

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
                OfferSkiControllerTestUnit.startDate(), OfferSkiControllerTestUnit.stopDate(), null)
        ).thenReturn(DataBaseOfferSki.getAllBetweenDate());
        when(iOfferSkiRepository.findAllByCityLikeAndStartOfferBeforeAndStopOfferAfterOrCityLikeAndStartOfferBeforeAndStopOfferIsNull(
                "Zakopane", OfferSkiControllerTestUnit.startDate(), OfferSkiControllerTestUnit.stopDate(), "Zakopane", OfferSkiControllerTestUnit.startDate())
        ).thenReturn(DataBaseOfferSki.getAllBetweenDateAndCityZakopane());
        return iOfferSkiRepository;
    }

    static private ITransactionRepository prepareTransactionData() {
        ITransactionRepository iTransactionRepository = mock(ITransactionRepository.class);
        when(iTransactionRepository.findAllByStartTransactionBetweenAndStartTransactionBetween(
                OfferSkiControllerTestUnit.startDate(), OfferSkiControllerTestUnit.stopDate(),
                OfferSkiControllerTestUnit.startDate(), OfferSkiControllerTestUnit.stopDate()
        )).thenReturn(DataBaseTransaction.getAllTransactionBetweenDate());
        return iTransactionRepository;
    }

    private static OfferSkiController prepareOfferSkiController(){
        IOfferSkiRepository iOfferSkiRepository = OfferSkiControllerTestUnit.prepareOfferSkiData();
        ITransactionRepository iTransactionRepository = OfferSkiControllerTestUnit.prepareTransactionData();
        OfferSkiController offerSkiController = new OfferSkiController();
        offerSkiController.setiOfferSkiRepository(iOfferSkiRepository);
        offerSkiController.setiTransactionRepository(iTransactionRepository);
        return offerSkiController;
    }

    @Test
    public void countReadyOfferSkiTest(){
        OfferSkiController offerSkiController = OfferSkiControllerTestUnit.prepareOfferSkiController();
        List<OfferSki> offerSkiList = offerSkiController.countReadyOfferSki(
                DataBaseOfferSki.getAllBetweenDateAndCityZakopane(), DataBaseTransaction.getAllTransactionBetweenDate());
        assertThat(offerSkiList, hasSize(3));
        assertEquals(offerSkiList.get(0).getQuantity(), 2);
        assertEquals(offerSkiList.get(1).getQuantity(), 4);
        assertEquals(offerSkiList.get(2).getQuantity(), 3);
    }

    @Test
    public void getOfferSkiActiveAndBetweenDateAndCityTest(){
        OfferSkiController offerSkiController = OfferSkiControllerTestUnit.prepareOfferSkiController();
        List<OfferSki> offerSkiList = offerSkiController.getOfferSkiActiveAndBetweenDateAndCity(
                OfferSkiControllerTestUnit.dateToString(OfferSkiControllerTestUnit.startDate()), OfferSkiControllerTestUnit.dateToString(OfferSkiControllerTestUnit.stopDate()), "Zakopane");
        assertThat(offerSkiList, hasSize(3));
        assertEquals(offerSkiList.get(0).getQuantity(), 2);
        assertEquals(offerSkiList.get(1).getQuantity(), 4);
        assertEquals(offerSkiList.get(2).getQuantity(), 3);
    }
}
