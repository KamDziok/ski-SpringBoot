package pl.ski.ski;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/ski")
@CrossOrigin


public class SkiController {

    @Autowired
    private SkiRepository skiRepository;

    @GetMapping
    private List<Ski> getAllSki(){
        return (List<Ski>) skiRepository.findAll();
    }

    @PostMapping
    private Ski addSki(@RequestBody Ski ski) { return skiRepository.save(ski);
    }

    @PutMapping
    private Ski updateSki(@RequestBody Ski ski){
        return skiRepository.save(ski);
    }

    @DeleteMapping
    private Ski deleteSki(@RequestBody Ski ski){
        skiRepository.delete(ski);
        return ski;
    }
}

