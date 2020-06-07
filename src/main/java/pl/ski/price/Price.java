package pl.ski.price;

import pl.ski.unit.Unit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Price {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Double value;

    @NotNull
    @ManyToOne
    private Unit unit;

    public Price() {
    }

    public Price(@NotNull Double value, @NotNull Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
