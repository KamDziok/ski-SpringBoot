package pl.ski.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ski.price.Price;
import pl.ski.price.PriceRepository;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/transaction")
@CrossOrigin

public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping
    private List<Transaction> getAllTransaction(){
        return (List<Transaction>) transactionRepository.findAll();
    }

    @PostMapping
    private Transaction addTransaction(@RequestBody Transaction transaction){
        return transactionRepository.save(transaction);
    }

    @PutMapping
    private Transaction updateTransaction(@RequestBody Transaction transaction){
        return transactionRepository.save(transaction);
    }

    @DeleteMapping
    private Transaction deleteTransaction(@RequestBody Transaction transaction){
        transactionRepository.delete(transaction);
        return transaction;
    }

}
