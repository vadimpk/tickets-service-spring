package com.naukma.ticketsservice.runs;

import com.naukma.ticketsservice.route.Route;
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
    public void createRun(Run run) {
        repository.add(run);
    }

    @Override
    public Run findRun(UUID id) {
        return repository.read(id);
    }

    @Override
    public List<Run> getRuns() {
        return repository.readAll();
    }

    @Override
    public Run update(UUID id, Run run) {
        return repository.update(id, run);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }

    @Override
    public void setTrain(UUID id, Train train) {

    }

    @Override
    public void setRoute(UUID id, Route route) {

    }
}
