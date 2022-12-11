package com.naukma.ticketsservice.route;

import com.naukma.ticketsservice.station.Station;

import java.util.List;
import java.util.Optional;

public interface RouteService {

    Route create(List<Long> stations);
    Route update(Route route, List<Long> stationsIds);

    List<Route> findRoutes(Station startStation, Station finishStation);

    Optional<Route> find(Long id);

    List<Route> getRoutes();

    boolean delete(Long id);
}
