package pl.ski.transaction;

import pl.ski.offer_ski.OfferSki;
import pl.ski.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Date prepareTransaction;

    @NotNull
    private Date startTransaction;

    @NotNull
    private Date stopTransaction;

    @NotNull
    @ManyToOne
    private User user;

    @ManyToMany
    private List<OfferSki> offerSkiList;

    public Transaction() {
    }

    public Transaction(@NotNull Date prepareTransaction, @NotNull Date startTransaction, @NotNull Date stopTransaction) {
        this.prepareTransaction = prepareTransaction;
        this.startTransaction = startTransaction;
        this.stopTransaction = stopTransaction;
    }

    public Transaction(@NotNull Date prepareTransaction, @NotNull Date startTransaction, @NotNull Date stopTransaction, @NotNull User user) {
        this.prepareTransaction = prepareTransaction;
        this.startTransaction = startTransaction;
        this.stopTransaction = stopTransaction;
        this.user = user;
    }

    public Transaction(@NotNull Date prepareTransaction, @NotNull Date startTransaction, @NotNull Date stopTransaction, @NotNull User user, List<OfferSki> offerSkiList) {
        this.prepareTransaction = prepareTransaction;
        this.startTransaction = startTransaction;
        this.stopTransaction = stopTransaction;
        this.user = user;
        this.offerSkiList = offerSkiList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPrepareTransaction() {
        return prepareTransaction;
    }

    public void setPrepareTransaction(Date prepareTransaction) {
        this.prepareTransaction = prepareTransaction;
    }

    public Date getStartTransaction() {
        return startTransaction;
    }

    public void setStartTransaction(Date startTransaction) {
        this.startTransaction = startTransaction;
    }

    public Date getStopTransaction() {
        return stopTransaction;
    }

    public void setStopTransaction(Date stopTransaction) {
        this.stopTransaction = stopTransaction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OfferSki> getOfferSkiList() {
        return offerSkiList;
    }

    public void setOfferSkiList(List<OfferSki> offerSkiList) {
        this.offerSkiList = offerSkiList;
    }

    public int getCountOfferSki(OfferSki offerSki){
        AtomicInteger counter = new AtomicInteger(0);
        this.offerSkiList.forEach(offerSki1 -> {
            if(offerSki1.getId().intValue() == offerSki.getId().intValue()){
                counter.addAndGet(1);
            }
        });
        return counter.get();
    }
}
