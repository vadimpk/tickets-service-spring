package com.naukma.ticketsservice.run;

import com.naukma.ticketsservice.route.Route;
import com.naukma.ticketsservice.train.Train;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RunService {

    Run createRun(Run newRun);
    Run save(Run run);

    Optional<Run> findRunById(Long id);
    Optional<Run> findRunByName(String name);

    Optional<Run> findRun(Long id);
    List<Run> getByDepartureDate(Date departureDate);

    List<Run> getRuns();

    int update(Long id, Run newRun);

    boolean delete(Long id);

    int setTrain(Long id, Train train);

    int setRoute(Long id, Route route);
}
