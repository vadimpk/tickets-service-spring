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
    public ResponseEntity<Train> show(@PathVariable String id){
        log.info("Showing train with id = " + id);
        Optional<Train> train = service.findTrain(Long.parseLong(id));
        return train.map(t -> new ResponseEntity<>(t, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/train/")
    public ResponseEntity<HttpStatus> create(@RequestBody Map<String, String> body){
        log.info("Creating train with speed = " + body.get("speed"));
        return service.createTrain(new Train(Integer.parseInt(body.get("speed")))) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PutMapping("/train/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable String id, @RequestBody Map<String, String> body){
        Train train = new Train(Integer.parseInt(body.get("speed")));
        train.setId(Long.valueOf(id));
        return service.update(train.getId(), train) > 0 ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/train/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String id){
        return service.delete(Long.parseLong(id)) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/train/{id}/wagon")
    public ResponseEntity<List<Wagon>> showWagons(@PathVariable String id){
        return service.findTrain(Long.parseLong(id))
                .map(train -> new ResponseEntity<>(train.getWagons(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/train/{id}/wagon/{wagonName}")
    public ResponseEntity<Wagon> showWagon(@PathVariable String id, @PathVariable String wagonName){
        Optional<Wagon> wagon = service.findWagonInTrain(Long.parseLong(id), wagonName);
        return wagon.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/train/{id}/wagon/{wagonName}")
    public ResponseEntity<HttpStatus> addWagon(@PathVariable String id, @PathVariable String wagonName){
        log.info("Adding wagon " + wagonName + " to train with id" + id);

        return service.addWagon(Long.parseLong(id), wagonName) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/train/{id}/wagon/{wagonName}")
    public ResponseEntity<HttpStatus> updateWagon(@PathVariable String id, @PathVariable String wagonName){
        Optional<Wagon> wagon = service.findWagonInTrain(Long.parseLong(id), wagonName);
        if (wagon.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccessControlRequestMethod(HttpMethod.PUT);
        headers.setLocation(URI.create("http://localhost:8080/api/v1/wagon/" + wagonName));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @DeleteMapping("/train/{id}/wagon/{wagonName}")
    public ResponseEntity<HttpStatus> deleteWagon(@PathVariable String id, @PathVariable String wagonName){
        return service.deleteWagon(Long.parseLong(id), wagonName) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
