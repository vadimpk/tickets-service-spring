package com.naukma.ticketsservice.train;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.naukma.ticketsservice.runs.Run;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<Wagon> wagons;

    private int speed;

    @ManyToMany
    private List<Run> runs;

//    public Train(@JsonProperty("wagons") List<Wagon> wagons,
//                 @JsonProperty("speed") int speed,
//                 @JsonProperty("runs") List<Run> runs) {
        public Train(@JsonProperty("speed") int speed) {
        this.wagons = new ArrayList<>();
        this.speed =speed;
        this.runs = new ArrayList<>();
    }

    public Train() {

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

    public void setId(Long id) {
        this.id = id;
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
