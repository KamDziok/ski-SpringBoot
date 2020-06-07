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

    private Integer lenght;

    private Integer type;

    @NotNull
    @ManyToOne
    private Producer producer;

    public Ski() {
    }

    public Ski(@NotNull String name, @NotNull Producer producer) {
        this.name = name;
        this.producer = producer;
    }

    public Ski(@NotNull String name, Integer lenght, Integer type, @NotNull Producer producer) {
        this.name = name;
        this.lenght = lenght;
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

    public Integer getLenght() {
        return lenght;
    }

    public void setLenght(Integer lenght) {
        this.lenght = lenght;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }
}
