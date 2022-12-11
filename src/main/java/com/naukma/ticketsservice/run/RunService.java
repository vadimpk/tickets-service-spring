package com.naukma.ticketsservice.run;

import com.naukma.ticketsservice.route.Route;
import com.naukma.ticketsservice.train.Train;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RunService {

    Run create(Run newRun);
    Run save(Run run);

    Optional<Run> find(Long id);
    Optional<Run> find(String name);

    List<Run> getByDepartureDate(Date departureDate);

    List<Run> getRuns();

    Run update(Long id, RunDto newRun);

    boolean delete(Long id);

    int setTrain(Long id, Train train);

    int setRoute(Long id, Route route);

    Optional<Route> findRouteById(Long RouteId);
    Optional<Train> findTrainById(Long TrainId);
}
