package pl.ski.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.ski.picture.IPictureRepository;
import pl.ski.picture.Picture;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/company")
@CrossOrigin


public class CompanyController {
    @Autowired
    private ICompanyRepository iCompanyRepository;
    @Autowired
    private IPictureRepository iPictureRepository;

    @GetMapping
    private List<Company> getAllCompany(){
        return (List<Company>) iCompanyRepository.findAll();
    }

    @GetMapping("/id/{id}")
    private Optional<Company> getCompanyById(@PathVariable("id") Long id) {
        return iCompanyRepository.findById(id);
    }

    @PostMapping
    private Company addCompany(@RequestBody Company company){
        return iCompanyRepository.save(company);
    }

    private Optional<Company> getCompanyByIdLocal(Long id){
        return iCompanyRepository.findById(id);
    }

    @PostMapping("/{id}")
    public Picture uploadImage(@RequestParam("imageFile") MultipartFile file, @PathVariable("id") Long id){
        Optional<Company> company =  getCompanyByIdLocal(id);
        return iPictureRepository.save(new Picture().uploadImageProfileCompany(file, company.get()));
    }

    @PutMapping
    private Company updateCompany(@RequestBody Company company){
        return iCompanyRepository.save(company);
    }

    @DeleteMapping
    private Company deleteCompany(@RequestBody Company company){
        iCompanyRepository.delete(company);
        return company;
    }
}
