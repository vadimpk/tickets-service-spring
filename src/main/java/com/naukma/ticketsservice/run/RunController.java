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
        // check if name is unique
        Optional<Run> check = runService.findRunByName(run.getName());
        if (check.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        // check if entered train exists otherwise set to null
        Long trainID = (run.getTrainId() != null) ? run.getTrainId() : 0;
        Optional<Train> t1 = trainService.findTrain(trainID);
        Train train = null;
        if (t1.isPresent()) {
            train = t1.get();
        }

        // check if entered route exists otherwise set to null
        Long routeId = (run.getRouteId() != null) ? run.getRouteId() : 0;
        Optional<Route> r1 = routeService.findRouteById(routeId);
        Route route = null;
        if (r1.isPresent()) {
            route = r1.get();
        }

        Run r = new Run(run.getName(),route,train,run.getDepartureTime(),run.getArrivalTime());
        return new ResponseEntity<>(runService.createRun(r),HttpStatus.OK);
    }

    @GetMapping("/run/{id}")
    public ResponseEntity<Run> show(@PathVariable String id) {
        Optional<Run> run = runService.findRunById(Long.valueOf(id));
        return run.map(t -> new ResponseEntity<>(t,HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/run/{id}")
    public ResponseEntity<Run> update(@PathVariable Long id, @Valid @RequestBody RunDto run) {

        // check if such run exists
        Optional<Run> runToChange = runService.findRunById(id);
        if (runToChange.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // check if new name is not unique
        Optional<Run> check = runService.findRunByName(run.getName());
        if (check.isPresent() && !check.get().getName().equals(runToChange.get().getName())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        // check if entered route exists otherwise set to the one that was before
        if (run.getRouteId() != null) {
            Optional<Route> r1 = routeService.findRouteById(run.getRouteId());
            if (r1.isPresent()) {
                runToChange.get().setRoute(r1.get());
            }
        }
        // check if entered train exists otherwise set to the one that was before
        if (run.getTrainId() != null) {
            Optional<Train> t1 = trainService.findTrain(run.getRouteId());
            if (t1.isPresent()) {
                runToChange.get().setTrain(t1.get());
            }
        }

        /**
         * Need to do smth with time
         * */

        runToChange.get().setName(runToChange.get().getName());
        return new ResponseEntity<>(runService.save(runToChange.get()), HttpStatus.OK);

    }
    @DeleteMapping("run/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        Optional<Run> r = runService.findRunById(id);
        if (r.isPresent()) {
            runService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
