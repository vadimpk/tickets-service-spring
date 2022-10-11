package com.naukma.ticketsservice.runs;

import com.naukma.ticketsservice.route.Route;
import com.naukma.ticketsservice.train.Train;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

public interface RunService {

    void createRun(Run run);

    Run findRun(UUID id);

    List<Run> getRuns();

    Run update(UUID id, Run run);

    void delete(UUID id);

    void setTrain(UUID id, Train train);

    void setRoute(UUID id, Route route);
}
