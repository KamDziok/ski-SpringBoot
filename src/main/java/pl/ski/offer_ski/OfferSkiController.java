package pl.ski.offer_ski;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ski.transaction.Transaction;
import pl.ski.transaction.TransactionRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/offer-ski")
@CrossOrigin
public class OfferSkiController {
    @Autowired
    private OfferSkiRepository offerSkiRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    public void setOfferSkiRepository(OfferSkiRepository offerSkiRepository) {
        this.offerSkiRepository = offerSkiRepository;
    }

    @GetMapping
    List<OfferSki> getAllOfferSki(){
        return (List<OfferSki>) offerSkiRepository.findAll();
    }

    @GetMapping("/company/{id}")
    List<OfferSki> getOfferSkiCompany(@PathVariable Long id){
        List<OfferSki> offerSkiList = (List<OfferSki>) offerSkiRepository.findAll();
        List<OfferSki> result = new ArrayList<>();
        offerSkiList.forEach(offerSki -> {
            if(offerSki.getCompany().getId().intValue() != id.intValue()){
                result.add(offerSki);
            }
        });
        return result;
    }

    @GetMapping("/{city}/{date}")
    List<OfferSki> getOfferSkiInCityAndDate(@PathVariable String city, @PathVariable String date) throws ParseException {
        return (List<OfferSki>) offerSkiRepository.findAllByCityAndStartOfferLessThanAndStopOfferGreaterThan(city, (Date) new SimpleDateFormat("dd-MM-yyyy").parse(date), (Date) new SimpleDateFormat("dd-MM-yyyy").parse(date));
    }

    @GetMapping("/sctive")
    List<OfferSki> getOfferSkiActive() {
        Date date = new Date();
        return offerSkiRepository.findAllByStartOfferGreaterThanAndStopOfferLessThanOrStopOffer(date, date, null);
    }

//    @CrossOrigin(origins = "http://localhost:8081/api/transaction")
    @GetMapping("/start-date/{begin}/stop-date/{end}")
    List<OfferSki> getOfferSkiActiveAndBeetwenDate(@PathVariable String begin, @PathVariable String end) {
        Date date = new Date();
        Date beginDate = null;
        Date endDate = null;
        List<OfferSki> result = new ArrayList<OfferSki>();
        try {
            beginDate = new SimpleDateFormat("dd-MM-yyyy").parse(begin);
            endDate = new SimpleDateFormat("dd-MM-yyyy").parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
            return result;
        }
        List<Transaction> transactionList = transactionRepository.findAllByStopTransactionAfter(date);
        List<OfferSki> tmp = offerSkiRepository
//                .findAllByStartOfferGreaterThanAndStopOfferLessThanOrStopOffer(beginDate, endDate, null);
//                .findAllByStartOfferGreaterThanAndStopOfferLessThanOrStopOfferEquals(beginDate, endDate, null);
//                .findAllByStartOfferAfterAndStopOfferBefore(beginDate, endDate);
                .findAllByStartOfferAfterAndStopOfferBeforeOrStopOfferIsNull(beginDate, endDate);
        result = tmp;
        List<OfferSki> finalResult = result;
        tmp.forEach(offerSki -> {
            transactionList.forEach(transaction -> {
                if(transaction.getOfferSkiList().contains(offerSki)){
                    if(offerSki.getStartOffer().compareTo(transaction.getStopTransaction()) > 0){
                        if(offerSki.getStopOffer().compareTo(transaction.getStartTransaction()) < 0){

                        } else {
                            offerSki.setQuantity(offerSki.getQuantity() - transaction.getCountOfferSki(offerSki));
                        }
                    } else {
                        offerSki.setQuantity(offerSki.getQuantity() - transaction.getCountOfferSki(offerSki));
                    }
                }
            });
            if(offerSki.getQuantity() > 0){
                finalResult.add(offerSki);
            }
        });
        return finalResult;
    }

    @GetMapping("/{city}")
    List<OfferSki> getOfferSkiInCity(@PathVariable String city) {
        List<OfferSki> result = new ArrayList<>();
        List<OfferSki> offerSkiList = (List<OfferSki>) offerSkiRepository.findByCity(city);
        Date now = new Date();
        offerSkiList.forEach(offerSki -> {
            if(offerSki.getStopOffer() != null){
                if(offerSki.getStopOffer().compareTo(now) < 0){
                    result.add(offerSki);
                }
            }else{
                result.add(offerSki);
            }
        });
        return result;
    }

    @PostMapping
    private OfferSki addOfferSki(@RequestBody OfferSki offerSki){
        return offerSkiRepository.save(offerSki);
    }

    @PutMapping
    private OfferSki updateOfferSki(@RequestBody OfferSki offerSki){  return offerSkiRepository.save(offerSki);
    }

    @DeleteMapping
    private OfferSki deleteOfferSki(@RequestBody OfferSki offerSki){
        offerSkiRepository.delete(offerSki);
        return offerSki;
    }
}
