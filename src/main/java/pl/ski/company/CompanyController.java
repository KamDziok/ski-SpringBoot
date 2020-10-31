package pl.ski.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

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

    @GetMapping("/id/{id}")
    private Optional<Company> getCompanyById(@PathVariable("id") Long id) {
        return companyRepository.findById(id);
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
