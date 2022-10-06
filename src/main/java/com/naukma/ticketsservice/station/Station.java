package com.naukma.ticketsservice.station;

import java.time.Duration;
import java.util.Map;

public class Station {

    private Map<Station, Integer> adjacentStations;
    private Duration duration;

    public Station() {

    }

    public void addAdjacentStation(Station station, int distance) {
        adjacentStations.put(station, distance);
    }

    public Map<Station, Integer> getAdjacentStations() {
        return adjacentStations;
    }

    public void setAdjacentStations(Map<Station, Integer> adjacentStations) {
        this.adjacentStations = adjacentStations;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
