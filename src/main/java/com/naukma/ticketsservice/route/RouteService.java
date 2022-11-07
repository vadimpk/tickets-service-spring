package com.naukma.ticketsservice.route;

import com.naukma.ticketsservice.station.Station;

import java.util.List;
import java.util.Optional;

public interface RouteService {

    Route createRoute(List<Station> stations);
    List<Route> findRoute(Station startStation, Station finishStation);

    Optional<Route> findRouteById(Long id);

    List<Route> getRoutes();

    void delete(Long id);
}
