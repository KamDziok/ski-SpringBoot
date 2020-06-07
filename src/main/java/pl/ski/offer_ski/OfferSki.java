package pl.ski.offer_ski;

import pl.ski.company.Company;
import pl.ski.price.Price;
import pl.ski.ski.Ski;
import pl.ski.transaction.Transaction;
import pl.ski.unit.Unit;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class OfferSki {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Date startOffer;

    private Date stopOffer;

    @NotNull
    @ManyToOne
    private Company company;

    @NotNull
    @ManyToOne
    private Price price;

    @NotNull
    @ManyToOne
    private Ski ski;

    public OfferSki() {
    }

    public OfferSki(@NotNull Date startOffer, @NotNull Company company, @NotNull Price price, @NotNull Ski ski) {
        this.startOffer = startOffer;
        this.company = company;
        this.price = price;
        this.ski = ski;
    }

    public OfferSki(@NotNull Date startOffer, Date stopOffer, @NotNull Company company, @NotNull Price price, @NotNull Ski ski) {
        this.startOffer = startOffer;
        this.stopOffer = stopOffer;
        this.company = company;
        this.price = price;
        this.ski = ski;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartOffer() {
        return startOffer;
    }

    public void setStartOffer(Date startOffer) {
        this.startOffer = startOffer;
    }

    public Date getStopOffer() {
        return stopOffer;
    }

    public void setStopOffer(Date stopOffer) {
        this.stopOffer = stopOffer;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Ski getSki() {
        return ski;
    }

    public void setSki(Ski ski) {
        this.ski = ski;
    }
}
