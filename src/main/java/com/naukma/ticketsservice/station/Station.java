package com.naukma.ticketsservice.station;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @JsonIgnore
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "adjacent_stations")
    @MapKeyColumn(name = "adjacent_station")
    private Map<Station, Integer> adjacentStations;

    public Station(String name) {
        this.name = name;
        this.adjacentStations = new HashMap<>();
    }

    public Station() {

    }

    public void addAdjacentStation(Station station, int distance) {
        adjacentStations.put(station, distance);
        station.adjacentStations.put(this, distance);
    }

    public Map<Station, Integer> getAdjacentStations() {
        return adjacentStations;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    

}
