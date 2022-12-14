package com.naukma.ticketsservice.run;

import com.naukma.ticketsservice.route.Route;
import com.naukma.ticketsservice.route.RouteRepository;
import com.naukma.ticketsservice.ticket.Ticket;
import com.naukma.ticketsservice.ticket.TicketService;
import com.naukma.ticketsservice.train.Train;
import com.naukma.ticketsservice.train.TrainRepository;
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
    private final TicketService ticketService;

    @Autowired
    public RunServiceImpl(RunRepository runRepository, RouteRepository routeRepository, TrainRepository trainRepository, TicketService ticketService) {
        this.runRepository = runRepository;
        this.routeRepository = routeRepository;
        this.trainRepository = trainRepository;
        this.ticketService = ticketService;
    }

    @Override
    public Run create(Run newRun) {
        return runRepository.save(newRun);
    }

    @Override
    public Run create(RunDto newRun) {
        Optional<Run> checkName = find(newRun.getName());
        if (checkName.isPresent()) return null;

        Run run = new Run();
        run.setName(newRun.getName());

        Optional<Train> train = trainRepository.findById(newRun.getTrainId());
        if (train.isEmpty()) return null;
        run.setTrain(train.get());

        Optional<Route> route = routeRepository.findById(newRun.getRouteId());
        if (route.isEmpty()) return null;
        run.setRoute(route.get());

        if (newRun.getDepartureTime() == null || newRun.getArrivalTime() == null || newRun.getDepartureDate() == null || newRun.getArrivalDate() == null)
            return null;

        run.setArrivalTime(newRun.getArrivalTime());
        run.setDepartureTime(newRun.getDepartureTime());
        run.setDepartureDate(newRun.getDepartureDate());
        run.setArrivalDate(newRun.getArrivalDate());

        return runRepository.save(run);
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

        Run run = check.get();
        Optional<Run> checkName = find(runDto.getName());
        if (checkName.isPresent()) return null;

        if (runDto.getDepartureTime() == null || runDto.getArrivalTime() == null || runDto.getDepartureDate() == null || runDto.getArrivalDate() == null)
            return null;

        Optional<Train> train = trainRepository.findById(runDto.getTrainId());
        if (train.isEmpty()) return null;

        Optional<Route> route = routeRepository.findById(runDto.getRouteId());
        if (route.isEmpty()) return null;

        run.setName(runDto.getName());
        run.setTrain(train.get());
        run.setRoute(route.get());
        run.setArrivalTime(runDto.getArrivalTime());
        run.setDepartureTime(runDto.getDepartureTime());
        run.setDepartureDate(runDto.getDepartureDate());
        run.setArrivalDate(runDto.getArrivalDate());
        run.setTakenSeats(runDto.getTakenSeats());

        return runRepository.save(run);
    }

    @Override
    public Run update(Long id, Run run) {
        runRepository.updateById(id, run.getName(), run.getRoute(), run.getTrain(),
                run.getDepartureTime(), run.getArrivalTime(), run.getDepartureDate(),
                run.getArrivalDate(), run.getTakenSeats());
        return run;
    }


    @Override
    public int setTrain(Long id, Train train) {
        runRepository.setTrainById(id,train);
        return 1;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Run> run = find(id);
        if (run.isEmpty()) return false;
        List<Ticket> tickets = ticketService.findTicketsByRun(run.get());
        for (Ticket t : tickets) {
            ticketService.delete(t.getId());
        }
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
        if(route.isEmpty())
            return Optional.empty();
        return route;
    }

    @Override
    public Optional<Train> findTrainById(Long TrainId) {
        Optional<Train> train = trainRepository.findById(TrainId);
        if(train.isEmpty())
            return Optional.empty();
        return train;
    }

    @Override
    public List<Run> findByRouteAndDepartureDate(Route route, Date departureDate) {
        return runRepository.findAllByRouteAndDepartureDate(route, departureDate);
    }
}
