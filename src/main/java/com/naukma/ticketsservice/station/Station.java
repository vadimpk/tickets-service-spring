package com.naukma.ticketsservice.station;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank
    private String name;

    @ElementCollection(fetch = FetchType.LAZY)
    private Map<Long, Integer> adjacentStations;

    public Station(String name) {
        this.name = name;
        this.adjacentStations = new HashMap<>();
    }

    public Station() {

    }

    public void addAdjacentStation(Station station, int distance) {
        adjacentStations.put(station.id, distance);
        station.adjacentStations.put(this.id, distance);
    }

    public Map<Long, Integer> getAdjacentStations() {
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
