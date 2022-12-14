package com.naukma.ticketsservice.run;

import com.naukma.ticketsservice.TicketsServiceApplication;
import com.naukma.ticketsservice.route.Route;
import com.naukma.ticketsservice.route.RouteService;
import com.naukma.ticketsservice.train.Train;
import com.naukma.ticketsservice.train.TrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("api/v1")
@RestController
public class RunController {

    static final Logger log =
            LoggerFactory.getLogger(TicketsServiceApplication.class);

    private final RunService runService;
    private final RouteService routeService;
    private final TrainService trainService;

    public RunController(RunService runService, RouteService routeService, TrainService trainService) {
        this.runService = runService;
        this.routeService = routeService;
        this.trainService = trainService;
    }

    @PostMapping("/run")
    public ResponseEntity<Run> create(@Valid @RequestBody RunDto run){

        Run created = runService.create(run);
        if (created == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(created, HttpStatus.OK);
    }

    @GetMapping("/run/{id}")
    public ResponseEntity<Run> show(@PathVariable String id) {
        Optional<Run> run = runService.find(Long.valueOf(id));
        return run.map(t -> new ResponseEntity<>(t,HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/run/{id}")
    public ResponseEntity<Run> update(@PathVariable Long id, @Valid @RequestBody RunDto run) {

        // check if such run exists
        Optional<Run> runToChange = runService.find(id);
        if (runToChange.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // check if new name is not unique
        Optional<Run> check = runService.find(run.getName());
        if (check.isPresent() && !check.get().getName().equals(runToChange.get().getName())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        runToChange.get().setName(runToChange.get().getName());

        // check if entered route exists otherwise set to the one that was before
        if (run.getRouteId() != null) {
            Optional<Route> r1 = routeService.find(run.getRouteId());
            r1.ifPresent(route -> runToChange.get().setRoute(route));
        }
        // check if entered train exists otherwise set to the one that was before
        if (run.getTrainId() != null) {
            Optional<Train> t1 = trainService.find(run.getRouteId());
            t1.ifPresent(train -> runToChange.get().setTrain(train));
        }
        runToChange.get().setDepartureTime(run.getDepartureTime());
        runToChange.get().setArrivalTime(run.getArrivalTime());
        runToChange.get().setDepartureDate(run.getDepartureDate());
        runToChange.get().setDepartureDate(run.getDepartureDate());

        return new ResponseEntity<>(runService.save(runToChange.get()), HttpStatus.OK);
    }
    @DeleteMapping("run/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        Optional<Run> r = runService.find(id);
        if (r.isPresent()) {
            runService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
