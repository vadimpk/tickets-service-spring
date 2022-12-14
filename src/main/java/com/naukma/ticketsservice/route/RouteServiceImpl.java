package com.naukma.ticketsservice.route;

import com.naukma.ticketsservice.aspects.LogInAndOutArgs;
import com.naukma.ticketsservice.run.Run;
import com.naukma.ticketsservice.run.RunService;
import com.naukma.ticketsservice.station.Station;
import com.naukma.ticketsservice.station.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository repository;
    private final StationRepository stationRepository;
    private final RunService runService;

    @Autowired
    public RouteServiceImpl(RouteRepository repository, StationRepository stationRepository, RunService runService) {
        this.repository = repository;
        this.stationRepository = stationRepository;
        this.runService = runService;
    }

    @Override
    @LogInAndOutArgs
    public Route create(List<Long> stationsIds) {
        List<Station> stations = validateStations(stationsIds);
        return createRoute(stations);
    }

    @Override
    @LogInAndOutArgs
    public Route create(Long startStationId, List<Long> stationsIds, Long finishStationId) {
        List<Station> stations = validateStations(startStationId, stationsIds, finishStationId);
        return createRoute(stations);

    }

    private Route createRoute(List<Station> stations) {
        if (stations.size() < 2) return null;

        int distance = validateRoute(stations);
        if (distance == -1) return null;

        return repository.save(new Route(stations.get(0), stations.get(stations.size()-1), stations, distance));
    }

    @Override
    public Route update(Route route, Long startStationID, List<Long> stationsIds, Long finishStationID) {

        List<Station> stations = validateStations(startStationID, stationsIds, finishStationID);
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

    private List<Station> validateStations(Long startStationId, List<Long> stationsIds, Long finishStationId) {
        List<Station> stations = new ArrayList<>();
        stationRepository.findById(startStationId).ifPresent(stations::add);
        for (Long id : stationsIds) {
            if (id.equals(startStationId) || id.equals(finishStationId))
                continue;
            Optional<Station> station = stationRepository.findById(id);
            station.ifPresent(stations::add);
        }
        stationRepository.findById(finishStationId).ifPresent(stations::add);

        return stations;
    }

    private int validateRoute(List<Station> stations) {
        int distance = 0;
        for (int i = 0; i < stations.size() - 1; i++) {
            if (stations.get(i).getAdjacentStations().containsKey(stations.get(i+1).getId())) {
                distance += stations.get(i).getAdjacentStations().get(stations.get(i+1).getId());
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
    public Optional<Route> find(Long routeId) {
        return repository.findById(routeId);
    }

    @Override
    public List<Route> getRoutes() {
        return repository.findAll();
    }

    @Override
    public boolean delete(Long id) {

        List<Run> runs = runService.getRuns();
        for (Run run : runs) {
            if (Objects.equals(run.getRoute().getId(), id)) {
                return false;
            }
        }
        repository.deleteById(id);
        return true;
    }

    @Override
    public int countDistance(Station start, Station finish, Run run) {
        int distance = 0;
        List<Station> stations = run.getRoute().getStations();
        int startIndex = stations.indexOf(start);
        int finishIndex = stations.indexOf(finish);
        for (int i = startIndex; i < finishIndex; i++)
            distance += stations.get(i).getAdjacentStations().get(stations.get(i + 1).getId());

        return distance;
    }


}

