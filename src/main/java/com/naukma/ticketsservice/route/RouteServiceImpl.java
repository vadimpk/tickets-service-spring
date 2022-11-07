package com.naukma.ticketsservice.route;

import com.naukma.ticketsservice.station.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository repository;

    public RouteServiceImpl(@Autowired RouteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Route createRoute(List<Station> stations) {

        int distance = 0;
        for (int i = 0; i < stations.size() - 1; i++) {
            if (stations.get(i).getAdjacentStations().containsKey(stations.get(i+1))) {
                distance += stations.get(i).getAdjacentStations().get(stations.get(i+1));
            } else return null;
        }

        return repository.save(new Route(stations.get(0), stations.get(stations.size()-1), stations, distance));
    }

    @Override
    public List<Route> findRoute(Station startStation, Station finishStation) {
        // get all routes and manually find all routes that have startStation and finishStation in it
        List<Route> allRoutes = getRoutes();
        List<Route> routes = new ArrayList<>();
        for (Route route : allRoutes) {
            boolean foundStart = false;
            boolean foundFinish = false;
            for (Station station: route.getStations()) {
                if (station == startStation) {
                    foundStart = true;
                }
                if (foundStart && station == finishStation) {
                    foundFinish = true;
                }
            }
            if (foundStart && foundFinish) {
                routes.add(route);
            }
        }
        return routes;
    }
    @Override
    public Optional<Route> findRouteById(Long routeId) {
        return Optional.of(repository.getReferenceById(routeId));
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
