package pl.ski.notification_company;

import pl.ski.company.Company;
import pl.ski.static_values.Permission;
import pl.ski.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class NotificationCompany {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String description;

    @NotNull
    @ManyToOne
    private Company company;

    @NotNull
    @ManyToOne
    private User user;

    public NotificationCompany() {
    }

    public NotificationCompany(@NotNull String description, @NotNull Company company, @NotNull User user) {
        this.description = description;
        this.company = company;
        this.user = user;
    }

    public boolean checkUser(){
        if(this.user.getPermissions() == Permission.user){
            return true;
        }
        return false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
