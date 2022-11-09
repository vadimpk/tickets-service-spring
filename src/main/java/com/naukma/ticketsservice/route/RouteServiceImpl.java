package com.naukma.ticketsservice.route;

import com.naukma.ticketsservice.station.Station;
import com.naukma.ticketsservice.station.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository repository;
    private final StationRepository stationRepository;

    public RouteServiceImpl(@Autowired RouteRepository repository,
                            @Autowired StationRepository stationRepository) {
        this.repository = repository;
        this.stationRepository = stationRepository;
    }

    @Override
    public Route createRoute(List<Long> stationsIds) {

        List<Station> stations = validateStations(stationsIds);
        if (stations.size() < 2) return null;

        int distance = validateRoute(stations);
        if (distance == -1) return null;

        return repository.save(new Route(stations.get(0), stations.get(stations.size()-1), stations, distance));
    }

    @Override
    public Route updateRoute(Route route, List<Long> stationsIds) {

        List<Station> stations = validateStations(stationsIds);
        if (stations.size() < 2) return null;

        int distance = validateRoute(stations);
        if (distance == -1) return null;

        route.setDistance(distance);
        route.setStartStation(stations.get(0));
        route.setFinishStation(stations.get(stations.size() - 1));
        route.setStations(stations);

        return repository.save(route);
    }

    private List<Station> validateStations(List<Long> stationsIds) {
        List<Station> stations = new ArrayList<>();
        for (Long id : stationsIds) {
            Optional<Station> station = stationRepository.findById(id);
            station.ifPresent(stations::add);
        }
        return stations;
    }

    private int validateRoute(List<Station> stations) {
        int distance = 0;
        for (int i = 0; i < stations.size() - 1; i++) {
            if (stations.get(i).getAdjacentStations().containsKey(stations.get(i+1))) {
                distance += stations.get(i).getAdjacentStations().get(stations.get(i+1));
            } else return -1;
        }

        return distance;
    }

    @Override
    public List<Route> findRoutes(Station startStation, Station finishStation) {
        // get all routes and manually find all routes that have startStation and finishStation in it
        List<Route> allRoutes = getRoutes();
        List<Route> routes = new ArrayList<>();
        for (Route route : allRoutes) {
            boolean foundStart = false;
            for (Station station: route.getStations()) {
                if (station == startStation) {
                    foundStart = true;
                }
                if (foundStart && station == finishStation) {
                    routes.add(route);
                    break;
                }
            }
        }
        return routes;
    }

    @Override
    public Optional<Route> findRouteById(Long routeId) {
        return repository.findById(routeId);
    }

    @Override
    public List<Route> getRoutes() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
