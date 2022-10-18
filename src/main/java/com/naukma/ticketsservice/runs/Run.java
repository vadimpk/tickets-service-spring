package com.naukma.ticketsservice.runs;

import com.naukma.ticketsservice.route.Route;
import com.naukma.ticketsservice.train.Train;

import java.time.Duration;
import java.util.UUID;

public class Run {

    private final UUID id;
    private Route route;
    private Train train;
    private Duration departureTime;
    private Duration arrivalTime;

    public Run(Route route) {
        this.id = UUID.randomUUID();
        this.route = route;
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
}
