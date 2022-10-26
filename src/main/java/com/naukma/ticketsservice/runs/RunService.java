package com.naukma.ticketsservice.runs;

import com.naukma.ticketsservice.route.Route;
import com.naukma.ticketsservice.train.Train;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RunService {

    boolean createRun(Run newRun);

    Optional<Run> findRun(Long id);
    List<Run> getRuns();

    int update(Long id, Run newRun);

    boolean delete(Long id);

    void setTrain(Long id, Train train);

    void setRoute(Long id, Route route);
}
