package com.naukma.ticketsservice.runs;

import com.naukma.ticketsservice.route.Route;
import com.naukma.ticketsservice.route.RouteRepository;
import com.naukma.ticketsservice.train.Train;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

public class RunServiceImpl implements RunService{

    private final RunRepository repository;

    public RunServiceImpl(@Autowired RunRepository repository) {
        this.repository = repository;
    }

    @Override
    public Run createRun(Route route, Train train, Duration departureTime, Duration arrivalTime) {
        return null;
    }

    @Override
    public Run findRun(Duration departureTime, Duration arrivalTime) {
        return null;
    }

    @Override
    public List<Run> getRuns() {
        return null;
    }

    @Override
    public Run update(UUID id, Duration departureTime, Duration arrivalTime) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void setTrain(UUID id, Train train) {

    }

    @Override
    public void setRoute(UUID id, Route route) {

    }
}
