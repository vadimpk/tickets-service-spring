package com.naukma.ticketsservice.station;

import com.naukma.ticketsservice.ObjectRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class StationRepository implements ObjectRepository<Station> {
    @Override
    public void add(Station station) {

    }

    @Override
    public Station read() {
        return null;
    }

    @Override
    public Station update(UUID id, Station station) {
        return null;
    }

    @Override
    public Station delete(UUID id) {
        return null;
    }

    public void addAdjacentStation(Station station, Station adjacentStation, int distance){

    }
}
