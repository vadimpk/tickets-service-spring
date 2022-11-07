package com.naukma.ticketsservice.run;

import com.naukma.ticketsservice.TicketsServiceApplication;
import com.naukma.ticketsservice.route.Route;
import com.naukma.ticketsservice.route.RouteService;
import com.naukma.ticketsservice.train.Train;
import com.naukma.ticketsservice.train.TrainService;
import com.naukma.ticketsservice.train.Wagon;
import com.naukma.ticketsservice.train.WagonDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Run> create(@RequestBody RunDto run){
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
//    @GetMapping("/run")
//    public ResponseEntity<List<Run>> index() {return new ResponseEntity<>(service.getRuns(), HttpStatus.OK);}
//
//    @GetMapping("/run/{id}")
//    public ResponseEntity<Run> show(@PathVariable String id) {
//        log.info("Showing run with id = " + id);
//        Optional<Run> run = service.findRun(Long.valueOf(id));
//        return run.map(t -> new ResponseEntity<>(t,HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    @PostMapping(value = "/train/")
//    public ResponseEntity<HttpStatus> create(@RequestBody Route route,
//                                             @RequestBody Train train,
//                                             @RequestBody Time departureTime,
//                                             @RequestBody Time arrivalTime) {
//        log.info("Creating run with: " +route+", "+train+", "+departureTime+", "+arrivalTime);
//        return service.createRun(new Run(route,train,departureTime,arrivalTime)) ?
//                new ResponseEntity<>(HttpStatus.OK) :
//                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//
//    }
//
//    @PutMapping("/run/{id}")
//    public ResponseEntity<HttpStatus> update(@PathVariable String id,
//                                             @RequestBody Route route,
//                                             @RequestBody Train train,
//                                             @RequestBody Time departureTime,
//                                             @RequestBody Time arrivalTime) {
//        Run run = new Run(route,train,departureTime,arrivalTime);
//        run.setId(Long.valueOf(id));
//        return service.update(run.getId(), run) > 0 ?
//                new ResponseEntity<>(HttpStatus.OK) :
//                new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//    }
//    @PutMapping("/run/{id}")
//    public ResponseEntity<HttpStatus> update(@PathVariable String id,
//                                             @RequestBody Route route) {
//        return service.setRoute(Long.valueOf(id), route) > 0 ?
//                new ResponseEntity<>(HttpStatus.OK) :
//                new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//    }
//    @PutMapping("/run/{id}")
//    public ResponseEntity<HttpStatus> update(@PathVariable String id,
//                                             @RequestBody Train train) {
//        return service.setTrain(Long.valueOf(id), train) > 0 ?
//                new ResponseEntity<>(HttpStatus.OK) :
//                new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//    }
    @DeleteMapping("/run/train/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String id) {
        return runService.delete(Long.valueOf(id)) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
