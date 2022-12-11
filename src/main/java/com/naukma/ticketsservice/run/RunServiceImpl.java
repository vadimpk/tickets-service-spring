package com.naukma.ticketsservice.run;

import com.naukma.ticketsservice.route.Route;
import com.naukma.ticketsservice.route.RouteRepository;
import com.naukma.ticketsservice.route.RouteService;
import com.naukma.ticketsservice.train.Train;
import com.naukma.ticketsservice.train.TrainRepository;
import com.naukma.ticketsservice.train.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RunServiceImpl implements RunService{

    private final RunRepository runRepository;
    private final RouteRepository routeRepository;
    private final TrainRepository trainRepository;

    @Autowired
    public RunServiceImpl( RunRepository runRepository, RouteRepository routeRepository, TrainRepository trainRepository) {
        this.runRepository = runRepository;
        this.routeRepository = routeRepository;
        this.trainRepository = trainRepository;
    }

    @Override
    public Run create(Run newRun) {
        return runRepository.saveAndFlush(newRun);
    }
    public Run save(Run run) { return runRepository.save(run);}

    @Override
    public Optional<Run> find(Long id) {return runRepository.findById(id);}
    @Override
    public Optional<Run> find(String name) { return runRepository.findByName(name);}

    @Override
    public List<Run> getByDepartureDate(Date departureDate) {
        return runRepository.findAllByDepartureDate(departureDate);
    }

    @Override
    public List<Run> getRuns() { return runRepository.findAll(); }

    @Override
    public Run update(Long id, RunDto runDto) {
        // check if exists
        Optional<Run> check = find(id);
        if (check.isEmpty())
            return null;

        // update name
        Run run = update(check.get(), runDto.getName());
        if (run == null) return null;

        //update Route
        update(run, findRouteById(runDto.getRouteId()).get());
        if (run == null) return null;

        //update Train
        update(run,findTrainById(runDto.getTrainId()).get());
        if(run == null) return null;

        //update Time
        run.setDepartureTime(runDto.getDepartureTime());
        run.setArrivalTime(runDto.getArrivalTime());

        //update Date
        run.setDepartureDate(runDto.getDepartureDate());
        run.setArrivalDate(runDto.getArrivalDate());

        return runRepository.save(run);
    }
    private Run update(Run run, String name) {
        Optional<Run> checkName = find(name);
        if (checkName.isPresent()) return null;

        run.setName(name);
        return run;
    }
    private void update(Run run, Route route) {
        Optional<Route> checkRoute = routeRepository.findById(route.getId());
        if (checkRoute.isPresent()) return;
        run.setRoute(route);
    }
    private void update(Run run, Train train) {
        Optional<Train> checkTrain = trainRepository.findByName(train.getName());
        if (checkTrain.isPresent()) return;

        run.setTrain(train);
    }

    @Override
    public int setTrain(Long id, Train train) {
        runRepository.setTrainById(id,train);
        return 1;
    }

    @Override
    public boolean delete(Long id) {
       runRepository.deleteById(id);
       return true;
    }

    @Override
    public int setRoute(Long id, Route route) {
        runRepository.setRouteById(id,route);
        return 1;
    }

    public Optional<Route> findRouteById(Long RouteId) {
        Optional<Route> route = routeRepository.findById(RouteId);
        if(route.isPresent())
            return null;
        return route;
    }

    @Override
    public Optional<Train> findTrainById(Long TrainId) {
        Optional<Train> train = trainRepository.findById(TrainId);
        if(train.isPresent())
            return null;
        return train;
    }
}
