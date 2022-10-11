package com.naukma.ticketsservice.runs;

import com.naukma.ticketsservice.route.Route;
import com.naukma.ticketsservice.train.Train;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

public interface RunService {

    Run createRun(Route route, Train train, Duration departureTime, Duration arrivalTime);

    Run findRun(Duration departureTime, Duration arrivalTime);

    List<Run> getRuns();

    Run update(UUID id, Duration departureTime, Duration arrivalTime);

    void delete(UUID id);

    void setTrain(UUID id, Train train);

    void setRoute(UUID id, Route route);
}
