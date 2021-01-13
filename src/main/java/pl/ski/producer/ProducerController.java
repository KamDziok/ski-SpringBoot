package pl.ski.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/producer")
@CrossOrigin

public class ProducerController {

    @Autowired
    private IProducerRepository IProducerRepository;

    @GetMapping
    private List<Producer> getAllProducer(){
        return (List<Producer>) IProducerRepository.findAll();
    }

    @PostMapping
    private Producer addProducer(@RequestBody Producer producer){
        return IProducerRepository.save(producer);
    }

    @PutMapping
    private Producer updateProducer(@RequestBody Producer producer){
        return IProducerRepository.save(producer);
    }

    @DeleteMapping
    private Producer deleteProducer(@RequestBody Producer producer){
        IProducerRepository.delete(producer);
        return producer;
    }
}
