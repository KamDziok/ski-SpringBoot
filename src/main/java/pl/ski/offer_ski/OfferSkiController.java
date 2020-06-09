package pl.ski.offer_ski;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    private List<OfferSki> getAllOfferSki(){
        return (List<OfferSki>) offerSkiRepository.findAll();
    }

    @GetMapping("/{city}/{date}")
    private List<OfferSki> getOfferSkiInCity(@PathVariable String city, @PathVariable String date) throws ParseException {
        return (List<OfferSki>) offerSkiRepository.findAllByCityAndStartOfferLessThanAndStopOfferGreaterThan(city, (Date) new SimpleDateFormat("dd-MM-yyyy").parse(date));
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
