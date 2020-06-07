package pl.ski.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/producer")
@CrossOrigin

public class ProducerController {

    @Autowired
    private ProducerRepository producerRepository;

    @GetMapping
    private List<Producer> getAllProducer(){
        return (List<Producer>) producerRepository.findAll();
    }

    @PostMapping
    private Producer addProducer(@RequestBody Producer producer){
        return producerRepository.save(producer);
    }

    @PutMapping
    private Producer updateProducer(@RequestBody Producer producer){
        return producerRepository.save(producer);
    }

    @DeleteMapping
    private Producer deleteProducer(@RequestBody Producer producer){
        producerRepository.delete(producer);
        return producer;
    }
}
