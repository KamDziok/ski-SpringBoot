package pl.ski.producer;

import pl.ski.ski.Ski;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Producer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

//    @ManyToOne
//    private List<Ski> skiList;

    public Producer() {
    }

    public Producer(@NotNull String name) {
        this.name = name;
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

//    public List<Ski> getSkiList() {
//        return skiList;
//    }
//
//    public void setSkiList(List<Ski> skiList) {
//        this.skiList = skiList;
//    }
}
