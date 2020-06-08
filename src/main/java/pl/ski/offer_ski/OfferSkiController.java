package pl.ski.offer_ski;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
