package pl.ski.user;

import pl.ski.company.Company;
import pl.ski.transaction.Transaction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String eMail;

    @NotNull
    private String password;

    @NotNull
    private int permissions;

    @ManyToOne
    private Company company;

    public User() {
    }

    public User(@NotNull String firstName, @NotNull String lastName, @NotNull String eMail, @NotNull String password, @NotNull int permissions) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.password = password;
        this.permissions = permissions;
    }

    public User(@NotNull String firstName, @NotNull String lastName, @NotNull String eMail, @NotNull String password, @NotNull int permissions, Company company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.password = password;
        this.permissions = permissions;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPermissions() {
        return permissions;
    }

    public void setPermissions(int permissions) {
        this.permissions = permissions;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
