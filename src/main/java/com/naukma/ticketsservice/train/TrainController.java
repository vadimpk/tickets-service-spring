package com.naukma.ticketsservice.train;

import com.naukma.ticketsservice.TicketsServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RequestMapping("api/v1")
@RestController
public class TrainController {

    static final Logger log =
            LoggerFactory.getLogger(TicketsServiceApplication.class);

    private final TrainService service;

    @Autowired
    public TrainController(TrainService service) {
        this.service = service;
    }

    @GetMapping("/train")
    public ResponseEntity<List<Train>> index(){
        return new ResponseEntity<>(service.getTrains(), HttpStatus.OK);
    }

    @GetMapping("/train/{id}")
    public ResponseEntity<Train> show(@PathVariable Long id){
        log.info("Showing train with id = " + id);
        Optional<Train> train = service.findTrain(id);
        return train.map(t -> new ResponseEntity<>(t, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/train")
    public ResponseEntity<Train> create(@RequestBody Map<String, String> body){
        log.info("Creating train ");
        // check if name is unique
        Optional<Train> check = service.findTrainByName(body.get("name"));
        if (check.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(service.createTrain(new Train(body.get("name"), Integer.parseInt(body.get("speed")))), HttpStatus.OK);
    }


    @PutMapping("/train/{id}")
    public ResponseEntity<Train> update(@PathVariable Long id, @RequestBody Map<String, String> body){
        // check if new name is not unique
        Optional<Train> check = service.findTrainByName(body.get("name"));
        if (check.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>((service.update(id, new Train(body.get("name"), Integer.parseInt(body.get("speed")))) > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/train/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        return service.delete(id) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/train/{id}/wagon")
    public ResponseEntity<Set<Wagon>> showWagons(@PathVariable String id) {
        return service.findTrain(Long.parseLong(id))
                .map(train -> new ResponseEntity<>(train.getWagons(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/train/{id}/wagon/{wagonID}")
    public ResponseEntity<HttpStatus> addWagon(@PathVariable Long id, @PathVariable Long wagonID) {
        log.info("Adding wagon " + wagonID + " to train with id" + id);

        return service.addWagon(id, wagonID) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/train/{id}/wagon/{wagonID}")
    public ResponseEntity<HttpStatus> deleteWagon(@PathVariable Long id, @PathVariable Long wagonID) {
        log.info("Removing wagon " + wagonID + " from train with id" + id);

        return service.deleteWagon(id, wagonID) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
