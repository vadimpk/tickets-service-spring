package com.naukma.ticketsservice.station;

import javax.persistence.*;
import java.time.Duration;
import java.util.Map;
import java.util.UUID;

@Entity
public class Station {

    @Id
    private UUID id;

    @ElementCollection
    @CollectionTable(name = "adjacent_stations")
    @MapKeyColumn(name = "adjacent_station")
    @Column(name = "distance")
    private Map<Station, Integer> adjacentStations;

    private Duration duration;

    public Station(UUID id) {
        this.id = id;
    }

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

        Station station = (Station) o;

        return id.equals(station.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
