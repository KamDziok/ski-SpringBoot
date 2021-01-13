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
    private ICompanyRepository ICompanyRepository;

    @GetMapping
    private List<Company> getAllCompany(){
        return (List<Company>) ICompanyRepository.findAll();
    }

    @GetMapping("/id/{id}")
    private Optional<Company> getCompanyById(@PathVariable("id") Long id) {
        return ICompanyRepository.findById(id);
    }

    @PostMapping
    private Company addCompany(@RequestBody Company company){
        return ICompanyRepository.save(company);
    }

    @PutMapping
    private Company updateCompany(@RequestBody Company company){
        return ICompanyRepository.save(company);
    }

    @DeleteMapping
    private Company deleteCompany(@RequestBody Company company){
        ICompanyRepository.delete(company);
        return company;
    }
}
