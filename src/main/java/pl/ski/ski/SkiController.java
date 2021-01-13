package pl.ski.ski;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/ski")
@CrossOrigin


public class SkiController {

    @Autowired
    private ISkiRepository ISkiRepository;

    @GetMapping
    private List<Ski> getAllSki(){
        return (List<Ski>) ISkiRepository.findAll();
    }

    @PostMapping
    private Ski addSki(@RequestBody Ski ski) { return ISkiRepository.save(ski);
    }

    @PutMapping
    private Ski updateSki(@RequestBody Ski ski){
        return ISkiRepository.save(ski);
    }

    @DeleteMapping
    private Ski deleteSki(@RequestBody Ski ski){
        ISkiRepository.delete(ski);
        return ski;
    }
}

