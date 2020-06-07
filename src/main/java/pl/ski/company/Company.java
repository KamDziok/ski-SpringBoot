package pl.ski.company;

import pl.ski.offer_ski.OfferSki;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Company {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    private Boolean active;

    private String description;

//    @OneToMany
//    private List<OfferSki> offerSkiList;

    public Company() {
    }

    public Company(@NotNull String name) {
        this.name = name;
    }

    public Company(@NotNull String name, Boolean active, String description) {
        this.name = name;
        this.active = active;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
