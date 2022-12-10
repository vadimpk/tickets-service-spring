package com.naukma.ticketsservice.station;

import com.naukma.ticketsservice.aspects.LogInAndOutArgs;
import com.naukma.ticketsservice.route.Route;
import com.naukma.ticketsservice.route.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StationServiceImpl implements StationService{

    private final StationRepository repository;
    private final RouteService routeService;

    @Autowired
    public StationServiceImpl(StationRepository repository, RouteService routeService) {
        this.repository = repository;
        this.routeService = routeService;
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
    public boolean delete(Long id) {

        Optional<Station> st = findById(id);
        if (st.isEmpty()) return false;

        List<Route> routes = routeService.getRoutes();
        for (Route r : routes) {
            if (r.getStations().contains(st.get())) {
                return false;
            }
        }

        List<Station> stations = getStations();
        for (Station station : stations) {
            removeAdjacentStation(st.get(), station);
        }
        repository.deleteById(id);
        return true;
    }

    @Override
    public void addAdjacentStation(Station station, Station adjStation, int distance) {
        if (station.getAdjacentStations().containsKey(adjStation.getId()) || station.equals(adjStation)) return;

        station.addAdjacentStation(adjStation, distance);
        repository.save(station);
        repository.save(adjStation);

    }

    @Override
    public void removeAdjacentStation(Station station, Station stationToRemove) {
        if (!station.getAdjacentStations().containsKey(stationToRemove.getId()) || station.equals(stationToRemove)) return;

        station.removeAdjacentStation(stationToRemove);
        repository.save(station);
        repository.save(stationToRemove);

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
