package pl.ski.price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/price")
@CrossOrigin

public class PriceController {

    @Autowired
    private PriceRepository priceRepository;

    @GetMapping
    private List<Price> getAllPrice(){
        return (List<Price>) priceRepository.findAll();
    }

    @PostMapping
    private Price addPrice(@RequestBody Price price){
        return priceRepository.save(price);
    }

    @PutMapping
    private Price updatePrice(@RequestBody Price price){
        return priceRepository.save(price);
    }

    @DeleteMapping
    private Price deletePrice(@RequestBody Price price){
       priceRepository.delete(price);
        return price;
    }

}
