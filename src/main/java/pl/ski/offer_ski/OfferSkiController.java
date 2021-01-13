package pl.ski.offer_ski;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ski.transaction.Transaction;
import pl.ski.transaction.ITransactionRepository;

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
    private IOfferSkiRepository iOfferSkiRepository;
    @Autowired
    private ITransactionRepository iTransactionRepository;

    @GetMapping
    List<OfferSki> getAllOfferSki(){
        return (List<OfferSki>) iOfferSkiRepository.findAll();
    }

    @GetMapping("/company/{id}")
    List<OfferSki> getOfferSkiCompany(@PathVariable Long id){
        List<OfferSki> offerSkiList = (List<OfferSki>) iOfferSkiRepository.findAll();
        List<OfferSki> result = new ArrayList<>();
        offerSkiList.forEach(offerSki -> {
            if(offerSki.getCompany().getId().intValue() == id.intValue()){
                result.add(offerSki);
            }
        });
        return result;
    }

    @GetMapping("/company/{id}/active")
    List<OfferSki> getOfferSkiCompanyActive(@PathVariable Long id){
        Date date = new Date();
        List<OfferSki> offerSkiList = (List<OfferSki>) iOfferSkiRepository.findAllByStartOfferGreaterThanAndStopOfferLessThanOrStopOffer(date, date, null);
        List<OfferSki> result = new ArrayList<>();
        offerSkiList.forEach(offerSki -> {
            if(offerSki.getCompany().getId().intValue() == id.intValue()){
                result.add(offerSki);
            }
        });
        return result;
    }

    @GetMapping("/{city}/{date}")
    List<OfferSki> getOfferSkiInCityAndDate(@PathVariable String city, @PathVariable String date) throws ParseException {
        return (List<OfferSki>) iOfferSkiRepository.findAllByCityAndStartOfferLessThanAndStopOfferGreaterThan(city, (Date) new SimpleDateFormat("dd-MM-yyyy").parse(date), (Date) new SimpleDateFormat("dd-MM-yyyy").parse(date));
    }

    @GetMapping("/sctive")
    List<OfferSki> getOfferSkiActive() {
        Date date = new Date();
        return iOfferSkiRepository.findAllByStartOfferGreaterThanAndStopOfferLessThanOrStopOffer(date, date, null);
    }

    List<Transaction> getAllransactionBeetwenDate(Date begin, Date end) {
        return iTransactionRepository.findAllByStartTransactionBetweenAndStartTransactionBetween(begin, end, begin, end);
    }

    List<OfferSki> countReadyOfferSki(List<OfferSki> offerSkiList, List<Transaction> transactionList){
        List<OfferSki> finalResult = new ArrayList<>();
        if(!transactionList.isEmpty()) {
            offerSkiList.forEach(offerSki -> {
                transactionList.forEach(transaction -> {
                    offerSki.setQuantity(offerSki.getQuantity() - transaction.getCountOfferSki(offerSki));
                });
                if(offerSki.getQuantity() > 0){
                    finalResult.add(offerSki);
                }
            });
        } else {
            return offerSkiList;
        }
        return finalResult;
    }

    @GetMapping("/start-date/{begin}/stop-date/{end}/city/{city}")
    List<OfferSki> getOfferSkiActiveAndBeetwenDateAndCity(@PathVariable String begin, @PathVariable String end, @PathVariable String city) {
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
        List<Transaction> transactionList = getAllransactionBeetwenDate(beginDate, endDate);
        List<OfferSki> offerSkiList = iOfferSkiRepository
                .findAllByCityAndStartOfferAfterAndStopOfferBeforeOrStopOfferIsNull(city, beginDate, endDate);
        return countReadyOfferSki(offerSkiList, transactionList);
    }

    @GetMapping("/{city}")
    List<OfferSki> getOfferSkiInCity(@PathVariable String city) {
        List<OfferSki> result = new ArrayList<>();
        List<OfferSki> offerSkiList = (List<OfferSki>) iOfferSkiRepository.findByCity(city);
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
        return iOfferSkiRepository.save(offerSki);
    }

    @PutMapping
    private OfferSki updateOfferSki(@RequestBody OfferSki offerSki){  return iOfferSkiRepository.save(offerSki);
    }

    @DeleteMapping
    private OfferSki deleteOfferSki(@RequestBody OfferSki offerSki){
        iOfferSkiRepository.delete(offerSki);
        return offerSki;
    }
}
