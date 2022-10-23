package com.naukma.ticketsservice.train;

import com.naukma.ticketsservice.runs.Run;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Entity
public class Train {

    @Id
    private UUID id;

    @OneToMany
    private List<Wagon> wagons;

    private int speed;

    @ManyToMany
    private List<Run> runs;

    public Train(UUID id){

        this.id = id;
    }

    public Train() {

    }

    public List<Wagon> getWagons() {
        return wagons;
    }

    public void setWagons(List<Wagon> wagons) {
        this.wagons = wagons;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Train train = (Train) o;

        if (speed != train.speed) return false;
        if (!id.equals(train.id)) return false;
        if (!wagons.equals(train.wagons)) return false;
        return runs.equals(train.runs);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + wagons.hashCode();
        result = 31 * result + speed;
        result = 31 * result + runs.hashCode();
        return result;
    }
}
