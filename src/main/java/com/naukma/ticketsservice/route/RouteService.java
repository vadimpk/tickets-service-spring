package com.naukma.ticketsservice.route;

import com.naukma.ticketsservice.station.Station;

import java.util.List;
import java.util.Optional;

public interface RouteService {

    Route createRoute(List<Long> stations);
    Route updateRoute(Route route, List<Long> stationsIds);

    List<Route> findRoute(Station startStation, Station finishStation);

    Optional<Route> findRouteById(Long id);

    List<Route> getRoutes();

    void delete(Long id);
}
