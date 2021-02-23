package pl.ski.offer_ski;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.ski.picture.IPictureRepository;
import pl.ski.picture.Picture;
import pl.ski.transaction.Transaction;
import pl.ski.transaction.ITransactionRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("api/offer-ski")
@CrossOrigin
public class OfferSkiController {
    @Autowired
    private IOfferSkiRepository iOfferSkiRepository;
    @Autowired
    private ITransactionRepository iTransactionRepository;
    @Autowired
    private IPictureRepository iPictureRepository;

    public void setiOfferSkiRepository(IOfferSkiRepository iOfferSkiRepository) {
        this.iOfferSkiRepository = iOfferSkiRepository;
    }

    public void setiTransactionRepository(ITransactionRepository iTransactionRepository) {
        this.iTransactionRepository = iTransactionRepository;
    }

    @GetMapping
    public List<OfferSki> getAllOfferSki(){
        return (List<OfferSki>) iOfferSkiRepository.findAll();
    }

    @GetMapping("/company/{id}")
    public List<OfferSki> getOfferSkiCompany(@PathVariable Long id){
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
    public List<OfferSki> getOfferSkiCompanyActive(@PathVariable Long id){
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

    public List<Transaction> getAllTransactionBeetwenDate(Date begin, Date end) {
        return iTransactionRepository.findAllByStartTransactionBetweenAndStartTransactionBetween(begin, end, begin, end);
    }

    public List<OfferSki> countReadyOfferSki(List<OfferSki> offerSkiList, List<Transaction> transactionList){
        List<OfferSki> finalResult = new ArrayList<>();
        if(!transactionList.isEmpty()) {
            offerSkiList.forEach(offerSki -> {
                if(offerSki.getCompany().getActive()) {
                    transactionList.forEach(transaction -> {
                        offerSki.setQuantity(offerSki.getQuantity() - transaction.getCountOfferSki(offerSki));
                    });
                    if (offerSki.getQuantity() > 0) {
                        finalResult.add(offerSki);
                    }
                }
            });
        } else {
            return offerSkiList;
        }
        return finalResult;
    }

    @GetMapping("/start-date/{begin}/stop-date/{end}/city/{city}")
    public List<OfferSki> getOfferSkiActiveAndBetweenDateAndCity(@PathVariable String begin, @PathVariable String end, @PathVariable String city) {
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
        List<Transaction> transactionList = getAllTransactionBeetwenDate(beginDate, endDate);
        List<OfferSki> offerSkiList = iOfferSkiRepository
                .findAllByCityLikeAndStartOfferBeforeAndStopOfferAfterOrCityLikeAndStartOfferBeforeAndStopOfferIsNull(city, beginDate, endDate, city, beginDate);
        return countReadyOfferSki(offerSkiList, transactionList);
    }

    private void addOfferSkiLocal(OfferSki offerSki){
        iOfferSkiRepository.save(offerSki);
    }

    @PostMapping
    private OfferSki addOfferSki(@RequestBody OfferSki offerSki){
        return iOfferSkiRepository.save(offerSki);
    }

    @PostMapping("/prepare")
    private OfferSki addOfferSkiPrepare(@RequestBody OfferSki offerSki){
        Optional<OfferSki> offerSkiOld = iOfferSkiRepository.findByCompanyAndSkiAndCityAndStopOfferIsNull(offerSki.getCompany(), offerSki.getSki(), offerSki.getCity());
        if(!offerSkiOld.isEmpty()) {
            Calendar c = Calendar.getInstance();
            c.setTime(offerSki.getStartOffer());
            c.add(Calendar.DATE, -1);
            offerSkiOld.get().setStopOffer(c.getTime());
            offerSki.setPictures(offerSkiOld.get().getPictures());
            iOfferSkiRepository.save(offerSkiOld.get());
            return offerSkiOld.get();
        }
        return null;
    }

    @PostMapping("/{id}")
    public Picture uploadImage(@RequestParam("imageFile") MultipartFile file, @PathVariable("id") Long id){
        Optional<OfferSki> offerSki =  iOfferSkiRepository.findById(id);
        return iPictureRepository.save(new Picture().uploadImageOfferSki(file, offerSki.get()));
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
