package com.naukma.ticketsservice.train;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.naukma.ticketsservice.runs.Run;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trains")
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ElementCollection(fetch = FetchType.LAZY)
    @Type(type = "wagon-array")
    private List<Wagon> wagons = new ArrayList<>();

    @Column
    private int speed;

    @ManyToMany
    private List<Run> runs;

    public Train() {

    }

//    public Train(@JsonProperty("wagons") List<Wagon> wagons,
//                 @JsonProperty("speed") int speed,
//                 @JsonProperty("runs") List<Run> runs) {
    public Train(@JsonProperty(value = "name", required = true) String name, @JsonProperty("speed") int speed) {
        this.wagons = new ArrayList<>();
        this.speed = speed;
        this.name = name;
        this.runs = new ArrayList<>();
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Wagon> getWagons() {
        return wagons;
    }

    public void setWagons(List<Wagon> wagons) {
        this.wagons = wagons;
    }
    public void addWagon(Wagon wagon) {
        wagons.add(wagon);
    }
    public void deleteWagon(Wagon wagon) {
        wagons.add(wagon);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public List<Run> getRuns() {
        return runs;
    }

    public void setRuns(List<Run> runs) {
        this.runs = runs;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Train train = (Train) o;

        return id.equals(train.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
