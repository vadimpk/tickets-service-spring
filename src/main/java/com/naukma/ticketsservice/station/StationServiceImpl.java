package com.naukma.ticketsservice.station;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class StationServiceImpl implements StationService{

    private final StationRepository repository;

    public StationServiceImpl(@Autowired StationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createStation(Station station) {
        repository.add(station);
    }

    @Override
    public Station findStation(UUID id) {
        return repository.read(id);
    }

    @Override
    public List<Station> getStations() {
        return repository.readAll();
    }

    @Override
    public Station update(UUID id, Station station) {
        return repository.update(id, station);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }

    @Override
    public void addAdjacentStation(UUID id, UUID adjacentID, int distance) {
        repository.addAdjacentStation(id, adjacentID, distance);
    }
}
