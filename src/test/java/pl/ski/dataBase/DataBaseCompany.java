package pl.ski.dataBase;

import pl.ski.company.Company;

import java.util.ArrayList;
import java.util.List;

public class DataBaseCompany {

    public static Company getCompanyActive1(){
        Company company = new Company("company1", true);
        company.setId((long) 1);
        return company;
    }

    public static Company getCompanyDontActive(){
        Company company = new Company("company2", false);
        company.setId((long) 2);
        return company;
    }

    public static Company getCompanyActive2(){
        Company company = new Company("company3", true);
        company.setId((long) 3);
        return company;
    }

    public static List<Company> getAllCompany(){
        List<Company> companies = new ArrayList<Company>();

        companies.add(getCompanyActive1());
        companies.add(getCompanyActive2());
        companies.add(getCompanyDontActive());

        return companies;
    }

    public static List<Company> getAllCompanyActive(){
        List<Company> companies = new ArrayList<Company>();

        companies.add(getCompanyActive1());
        companies.add(getCompanyActive2());

        return companies;
    }
}
