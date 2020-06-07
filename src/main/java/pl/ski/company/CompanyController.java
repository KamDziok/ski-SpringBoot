package pl.ski.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/company")
@CrossOrigin


public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping
    private List<Company> getAllCompany(){
        return (List<Company>) companyRepository.findAll();
    }

    @PostMapping
    private Company addCompany(@RequestBody Company company){
        return companyRepository.save(company);
    }

    @PutMapping
    private Company updateCompany(@RequestBody Company company){
        return companyRepository.save(company);
    }

    @DeleteMapping
    private Company deleteCompany(@RequestBody Company company){
        companyRepository.delete(company);
        return company;
    }
}
