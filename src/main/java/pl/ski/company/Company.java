package pl.ski.company;

import pl.ski.offer_ski.OfferSki;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Boolean active;

    private String description;

    private String profilePicture;

//    @OneToMany
//    private List<OfferSki> offerSkiList;

    public Company() {
    }

    public Company(@NotNull String name, @NotNull Boolean active) {
        this.name = name;
        this.active = active;
    }

    public Company(@NotNull String name, @NotNull Boolean active, String description) {
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
