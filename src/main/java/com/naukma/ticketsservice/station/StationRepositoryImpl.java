package com.naukma.ticketsservice.station;

import com.naukma.ticketsservice.CRUDOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class StationRepositoryImpl implements StationRepository {
    @Override
    public void add(Station station) {

    }

    @Override
    public Station read(UUID id) {
        return null;
    }

    @Override
    public List<Station> readAll() {return null;}

    @Override
    public Station update(UUID id, Station station) {
        return null;
    }

    @Override
    public Station delete(UUID id) {
        return null;
    }

    @Override
    public void addAdjacentStation(UUID id, UUID adjacentID, int distance){

    }
}
