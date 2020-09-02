package pl.ski.ski;

import pl.ski.producer.Producer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Ski {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    private Integer lengthSki;

    private String type;

    @NotNull
    @ManyToOne
    private Producer producer;

    public Ski() {
    }

    public Ski(@NotNull String name, @NotNull Producer producer) {
        this.name = name;
        this.producer = producer;
    }

    public Ski(@NotNull String name, Integer lengthSki, String type, @NotNull Producer producer) {
        this.name = name;
        this.lengthSki = lengthSki;
        this.type = type;
        this.producer = producer;
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

    public Integer getLengthSki() {
        return lengthSki;
    }

    public void setLengthSki(Integer lengthSki) {
        this.lengthSki = lengthSki;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }
}
