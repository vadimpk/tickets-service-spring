package com.naukma.ticketsservice.station;

import com.naukma.ticketsservice.route.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class StationServiceImpl implements StationService{

    private final StationRepository repository;

    public StationServiceImpl(@Autowired StationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Station createStation(UUID id) {
        return null;
    }

    @Override
    public Station findStation(UUID id) {
        return null;
    }

    @Override
    public List<Station> getStations() {
        return null;
    }

    @Override
    public Station update(UUID id, Station station) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void addAdjacentStation(Station station, Station adjacentStation, int distance) {

    }
}
