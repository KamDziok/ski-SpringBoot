package pl.ski.dataBase;

import pl.ski.offer_ski.OfferSki;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataBaseOfferSki {

    private static Date createDate(int year, int month, int day){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try{
            date = format.parse((year + "-" + month + "-" + day));
        }catch (Exception e){ }
        return date;
    }

    public static Date nowDate(){
        return DataBaseOfferSki.createDate(2020, 12, 15);
    }

    public static OfferSki getOfferSki1(){
        OfferSki offerSki = new OfferSki("Zakopane", DataBaseOfferSki.createDate(2020, 6, 9),
                DataBaseOfferSki.createDate(2020, 12, 29), 5, DataBaseCompany.getCompanyActive1(), 19.99, DataBaseSki.getSki1Producent1());
        offerSki.setId((long) 1);
        return offerSki;
    }

    public static OfferSki getOfferSki2(){
        OfferSki offerSki = new OfferSki("Zakopane", DataBaseOfferSki.createDate(2020, 12, 30),
                null, 5, DataBaseCompany.getCompanyActive1(), 49.99, DataBaseSki.getSki1Producent1());
        offerSki.setId((long) 2);
        return offerSki;
    }

    public static OfferSki getOfferSki3(){
        OfferSki offerSki = new OfferSki("Zakopane", DataBaseOfferSki.createDate(2020, 12, 30),
                null, 5, DataBaseCompany.getCompanyActive2(), 29.99, DataBaseSki.getSki2Producent1());
        offerSki.setId((long) 3);
        return offerSki;
    }

    public static OfferSki getOfferSki4(){
        OfferSki offerSki = new OfferSki("Zakopane", DataBaseOfferSki.createDate(2020, 10, 4),
                null, 4, DataBaseCompany.getCompanyActive1(), 24.99, DataBaseSki.getSki3Producent2());
        offerSki.setId((long) 4);
        return offerSki;
    }

    public static OfferSki getOfferSki5(){
        OfferSki offerSki = new OfferSki("Zakopane", DataBaseOfferSki.createDate(2020, 10, 4),
                null, 3, DataBaseCompany.getCompanyDontActive(), 14.99, DataBaseSki.getSki3Producent2());
        offerSki.setId((long) 5);
        return offerSki;
    }

    public static List<OfferSki> getAllBetweenDate(){
        List<OfferSki> result = new ArrayList<>();

        result.add(DataBaseOfferSki.getOfferSki2());
        result.add(DataBaseOfferSki.getOfferSki3());
        result.add(DataBaseOfferSki.getOfferSki4());
        result.add(DataBaseOfferSki.getOfferSki5());

        return result;
    }

    public static List<OfferSki> getAllBetweenDateAndCityZakopane(){
        List<OfferSki> result = new ArrayList<>();

        result.add(DataBaseOfferSki.getOfferSki2());
        result.add(DataBaseOfferSki.getOfferSki3());
        result.add(DataBaseOfferSki.getOfferSki4());
        result.add(DataBaseOfferSki.getOfferSki5());

        return result;
    }
}
