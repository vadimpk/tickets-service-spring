package com.naukma.ticketsservice.train;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.naukma.ticketsservice.run.Run;
import com.naukma.ticketsservice.wagon.Wagon;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "train")
    private Set<Wagon> wagons;

    @Column
    private int speed;

    @ManyToMany
    private List<Run> runs;

    public Train() {

    }

    public Train(String name, int speed) {
        this.speed = speed;
        this.name = name;
        runs = new ArrayList<>();
        wagons = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Wagon> getWagons() {
        return wagons;
    }

    public int getSpeed() {
        return speed;
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

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void addRun(Run run) {
        runs.add(run);
    }

    public void addWagon(Wagon wagon) {
        wagons.add(wagon);
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
