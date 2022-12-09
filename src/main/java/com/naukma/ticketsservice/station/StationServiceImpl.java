package com.naukma.ticketsservice.station;

import com.naukma.ticketsservice.aspects.LogInAndOutArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public Station update(Station station) {
        repository.save(station);
        return station;
    }

    @Override
    public void delete(Long id) {
        List<Station> stations = getStations();
        for (Station station : stations) {
            station.getAdjacentStations().remove(id);
        }
        repository.deleteById(id);
    }

    @Override
    public void addAdjacentStation(Station station, int distance) {
        //repository.addAdjacentStation(station, distance);
    }

    @Override
    @LogInAndOutArgs
    public Map<Long, Station> getStationsMap() {

        List<Station> stations = getStations();
        Map<Long, Station> res = new HashMap<>();

        for (Station station : stations) {
            res.put(station.getId(), station);
        }
        return res;
    }
}
