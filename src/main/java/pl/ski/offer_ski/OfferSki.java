package pl.ski.offer_ski;

import pl.ski.company.Company;
//import pl.ski.price.Price;
import pl.ski.ski.Ski;
import pl.ski.transaction.Transaction;
//import pl.ski.unit.Unit;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class OfferSki {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull
    private String city;

    @NotNull
    private Date startOffer;

    private Date stopOffer;

    @NotNull
    private int quantity;

    @NotNull
    @ManyToOne
    private Company company;

//    @NotNull
//    @ManyToOne
//    private Price price;

    @NotNull
    private double priceForDay;

    @NotNull
    @ManyToOne
    private Ski ski;

    private List<String> pictures;

    public OfferSki() {
    }

    public OfferSki(@NotNull String city, @NotNull Date startOffer, @NotNull int quantity, @NotNull Company company, @NotNull double priceForDay, @NotNull Ski ski) {
        this.city = city;
        this.startOffer = startOffer;
        this.quantity = quantity;
        this.company = company;
        this.priceForDay = priceForDay;
        this.ski = ski;
    }

    public OfferSki(@NotNull String city, @NotNull Date startOffer, Date stopOffer, @NotNull int quantity, @NotNull Company company, @NotNull double priceForDay, @NotNull Ski ski) {
        this.city = city;
        this.startOffer = startOffer;
        this.stopOffer = stopOffer;
        this.quantity = quantity;
        this.company = company;
        this.priceForDay = priceForDay;
        this.ski = ski;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

//    public Price getPrice() {
//        return price;
//    }
//
//    public void setPrice(Price price) {
//        this.price = price;
//    }


    public double getPriceForDay() {
        return priceForDay;
    }

    public void setPriceForDay(double priceForDay) {
        this.priceForDay = priceForDay;
    }

    public Ski getSki() {
        return ski;
    }

    public void setSki(Ski ski) {
        this.ski = ski;
    }

    public boolean isEquality(OfferSki offerSki){
        return this.id == offerSki.getId();
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }
}
