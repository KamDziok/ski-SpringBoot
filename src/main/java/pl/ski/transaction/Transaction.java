package pl.ski.transaction;

import pl.ski.offer_ski.OfferSki;
import pl.ski.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

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

    public List<OfferSki> getOfferSkiList() {
        return offerSkiList;
    }

    public void setOfferSkiList(List<OfferSki> offerSkiList) {
        this.offerSkiList = offerSkiList;
    }
}
