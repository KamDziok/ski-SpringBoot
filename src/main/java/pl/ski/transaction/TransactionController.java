package pl.ski.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ski.offer_ski.OfferSki;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("api/transaction")
@CrossOrigin

public class TransactionController {

    @Autowired
    private ITransactionRepository ITransactionRepository;

    @GetMapping
    private List<Transaction> getAllTransaction(){
        return (List<Transaction>) ITransactionRepository.findAll();
    }

    @GetMapping("/user/{id}")
    private List<Transaction> getAllUserTransaction(@PathVariable Long id){
        List<Transaction> transactionList = (List<Transaction>) ITransactionRepository.findAll();
        List<Transaction> result = new ArrayList<>();
        transactionList.forEach(transaction -> {
            if(transaction.getUser().getId().intValue() == id.intValue()){
                result.add(transaction);
            }
        });
        return result;
    }

    @GetMapping("/company/{companyId}")
    private List<Transaction> getAllCompanyTransaction(@PathVariable Long companyId){
        List<Transaction> transactionList = (List<Transaction>) ITransactionRepository.findAll();
        List<OfferSki> tmpOfferSki = new LinkedList<>();
        List<Transaction> result = new ArrayList<>();
        transactionList.forEach(transaction -> {
            transaction.getOfferSkiList().forEach(offerSki -> {
                if(offerSki.getCompany().getId().intValue() == companyId.intValue()){
                    tmpOfferSki.add(offerSki);
                }
            });
            if(!tmpOfferSki.isEmpty()){
                transaction.setOfferSkiList(new ArrayList<>(tmpOfferSki));
                result.add(transaction);
            }
            tmpOfferSki.clear();
        });
        return result;
//        return transactionList;
    }

    @PostMapping
    private Transaction addTransaction(@RequestBody Transaction transaction){
        return ITransactionRepository.save(transaction);
    }

    @PutMapping
    private Transaction updateTransaction(@RequestBody Transaction transaction){
        return ITransactionRepository.save(transaction);
    }

    @DeleteMapping
    private Transaction deleteTransaction(@RequestBody Transaction transaction){
        ITransactionRepository.delete(transaction);
        return transaction;
    }

}
