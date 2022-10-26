package com.naukma.ticketsservice.runs;

import com.naukma.ticketsservice.TicketsServiceApplication;
import com.naukma.ticketsservice.route.Route;
import com.naukma.ticketsservice.train.Train;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1")
@RestController
public class RunController {

    static final Logger log =
            LoggerFactory.getLogger(TicketsServiceApplication.class);

    private final RunService service;

    @Autowired
    public RunController(RunService service) { this.service = service; }

    @GetMapping("/run")
    public ResponseEntity<List<Run>> index() {return new ResponseEntity<>(service.getRuns(), HttpStatus.OK);}

    @GetMapping("/run/{id}")
    public ResponseEntity<Run> show(@PathVariable String id) {
        log.info("Showing run with id = " + id);
        Optional<Run> run = service.findRun(Long.valueOf(id));
        return run.map(t -> new ResponseEntity<>(t,HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/train/")
    public ResponseEntity<HttpStatus> create(@RequestBody Route route,
                                             @RequestBody Train train,
                                             @RequestBody Time departureTime,
                                             @RequestBody Time arrivalTime) {
        log.info("Creating run with: " +route+", "+train+", "+departureTime+", "+arrivalTime);
        return service.createRun(new Run(route,train,departureTime,arrivalTime)) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/run/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable String id,
                                             @RequestBody Route route,
                                             @RequestBody Train train,
                                             @RequestBody Time departureTime,
                                             @RequestBody Time arrivalTime) {
        Run run = new Run(route,train,departureTime,arrivalTime);
        run.setId(Long.valueOf(id));
        return service.update(run.getId(), run) > 0 ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PutMapping("/run/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable String id,
                                             @RequestBody Route route) {
        return service.setRoute(Long.valueOf(id), route) > 0 ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PutMapping("/run/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable String id,
                                             @RequestBody Train train) {
        return service.setTrain(Long.valueOf(id), train) > 0 ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @DeleteMapping("/train/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String id) {
        return service.delete(Long.valueOf(id)) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
