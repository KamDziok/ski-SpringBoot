package pl.ski.offer_ski;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OfferSkiControllerTestI {

    List<OfferSki> prepareOfferSkiData(){
        Date dateStart=new SimpleDateFormat("dd/MM/yyyy").parse("01/05/2020");
        OfferSki offerSki = new OfferSki("Miejscowość1", dateStart, null, @NotNull Company company, @NotNull Price price, @NotNull Ski ski);
    }

}
