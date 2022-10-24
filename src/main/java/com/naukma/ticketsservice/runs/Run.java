package com.naukma.ticketsservice.runs;

import com.naukma.ticketsservice.route.Route;
import com.naukma.ticketsservice.train.Train;

import javax.persistence.*;
import java.time.Duration;
import java.util.UUID;

@Entity
public class Run {

    @Id
    private UUID id;

    @ManyToOne
    private Route route;

    @ManyToOne
    private Train train;

    @Column(name = "departure_time")
    private Duration departureTime;

    @Column(name = "arrival_time")
    private Duration arrivalTime;

    public Run(Route route) {
        this.id = UUID.randomUUID();
        this.route = route;
    }

    public Run() {

    }

    public UUID getId() {
        return id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Duration getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Duration departureTime) {
        this.departureTime = departureTime;
    }

    public Duration getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Duration arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Run run = (Run) o;

        return id.equals(run.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
