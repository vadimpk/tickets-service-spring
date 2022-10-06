package com.naukma.ticketsservice.route;

import com.naukma.ticketsservice.station.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RouteServiceImpl implements RouteService {

    // DI via field (not recommended)
//    @Autowired
//    private RouteRepository repository;

    private final RouteRepository repository;

    public RouteServiceImpl(@Autowired RouteRepository repository) {
        this.repository = repository;
    }

    // DI via setter
//    public void setRepository(@Autowired RouteRepository repository) {
//        this.repository = repository;
//    }

    @Override
    public Route createRoute(Station startStation, Station finishStation, List<Station> transitionalStations) {
        // run algorithm that finds all stations of the route
        // then run repository method
        return null;
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
    public List<Route> getRoutes() {
        // run repository method
        return null;
    }

    @Override
    public void delete(UUID id) {
        // run repository method
    }
}
