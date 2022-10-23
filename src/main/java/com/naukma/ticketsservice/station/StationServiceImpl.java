package com.naukma.ticketsservice.station;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class StationServiceImpl implements StationService{

    private final StationRepository repository;

    public StationServiceImpl(@Autowired StationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createStation(Station station) {
        repository.saveAndFlush(station);
    }

    @Override
    public Station findStation(UUID id) {
        Optional<Station> station = repository.findById(id);
        if (station.isEmpty()) throw new RuntimeException("not such station with id " + id);
        return station.get();
    }

    @Override
    public List<Station> getStations() {
        return repository.findAll();
    }

    @Override
    public Station update(UUID id, Station station) {
        repository.setStationById(id, station.getAdjacentStations(), station.getDuration());
        return station;
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void addAdjacentStation(UUID id, UUID adjacentID, int distance) {
        repository.addAdjacentStation(id, adjacentID, distance);
    }
}
