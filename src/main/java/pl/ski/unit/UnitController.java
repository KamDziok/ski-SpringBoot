package pl.ski.unit;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ski.price.Price;
import pl.ski.price.PriceRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/unit")
@CrossOrigin

public class UnitController {

    @Autowired
    private UnitRepository unitRepository;

    @GetMapping
    private List<Unit> getAllUnit(){
        return (List<Unit>) unitRepository.findAll();
    }

    @PostMapping
    private Unit addUnit(@RequestBody Unit unit){
        return unitRepository.save(unit);
    }

    @PutMapping
    private Unit updateUnit (@RequestBody Unit unit){
        return unitRepository.save(unit);
    }

    @DeleteMapping
    private Unit deleteUnit(@RequestBody Unit unit){
        unitRepository.delete(unit);
        return unit;
    }
}
