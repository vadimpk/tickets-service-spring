package com.naukma.ticketsservice.station;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StationServiceImpl implements StationService{

    private final StationRepository repository;

    public StationServiceImpl(@Autowired StationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Station createStation(Station station) {
        return repository.save(station);
    }

    @Override
    public Optional<Station> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Station> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Station> findAllById(List<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public List<Station> getStations() {
        return repository.findAll();
    }

    @Override
    public Station update(Long id, Station station) {
        repository.setStationById(id, station.getAdjacentStations());
        return station;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void addAdjacentStation(UUID id, UUID adjacentID, int distance) {
//        repository.addAdjacentStation(id, adjacentID, distance);
    }
}
