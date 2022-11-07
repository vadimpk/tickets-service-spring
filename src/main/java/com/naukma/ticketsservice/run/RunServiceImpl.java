package com.naukma.ticketsservice.run;

import com.naukma.ticketsservice.route.Route;
import com.naukma.ticketsservice.train.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RunServiceImpl implements RunService{

    private final RunRepository repository;

    public RunServiceImpl(@Autowired RunRepository repository) {
        this.repository = repository;
    }

    @Override
    public Run createRun(Run newRun) {
        return repository.saveAndFlush(newRun);
    }

    @Override
    public Optional<Run> findRunById(Long id) {return repository.findById(id);}
    @Override
    public Optional<Run> findRunByName(String name) { return repository.findByName(name);}

    @Override
    public Optional<Run> findRun(Long id) { return repository.findById(id); }


    @Override
    public List<Run> getRuns() { return repository.findAll(); }

    @Override
    public int update(Long id, Run run) {
        repository.setRouteById(id,run.getRoute());
        repository.setTrainById(id,run.getTrain());
        repository.setDepartureTimeById(id,run.getDepartureTime());
        repository.setArrivalTimeById(id,run.getArrivalTime());
        return 1;
    }

    @Override
    public int setTrain(Long id, Train train) {
        repository.setTrainById(id,train);
        return 1;
    }

    @Override
    public boolean delete(Long id) {
       repository.deleteById(id);
        return true;
    }

    @Override
    public int setRoute(Long id, Route route) {
        repository.setRouteById(id,route);
        return 1;
    }
}
