package com.naukma.ticketsservice.train;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1")
@RestController
public class WagonController {

    private final WagonService service;
    // private final TrainService trainService;

    @Autowired
    public WagonController(WagonService service, TrainService trainService) {
        this.service = service;
        //this.trainService = trainService;
    }

    @GetMapping("/wagon")
    public ResponseEntity<List<Wagon>> wagon(){
        return new ResponseEntity<>(service.getWagons(), HttpStatus.OK);
    }

    @GetMapping("/wagon/{id}")
    public ResponseEntity<Wagon> show(@PathVariable Long id){
        Optional<Wagon> w = service.findWagonById(id);
        return w.map(wagon -> new ResponseEntity<>(wagon, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/wagon")
    public ResponseEntity<Wagon> addWagon(@RequestBody Wagon wagon){
        // check if name is unique
        Optional<Wagon> check = service.findWagonByName(wagon.getName());
        if (check.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(service.createWagon(wagon), HttpStatus.OK);
    }

    @PutMapping("/wagon/{id}")
    public ResponseEntity<Wagon> update(@PathVariable Long id, @RequestBody Wagon wagon) {
        // check if new name is not unique
        Optional<Wagon> check = service.findWagonByName(wagon.getName());
        if (check.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>((service.update(id, wagon) > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("wagon/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        Optional<Wagon> w = service.findWagonById(id);
        if (w.isPresent()) {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/wagon/{id}/train")
    public ResponseEntity<Train> showTrain(@PathVariable Long id){
        Optional<Wagon> w = service.findWagonById(id);
        return w.map(wagon -> new ResponseEntity<>(wagon.getTrain(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    @PostMapping("wagon/{id}/add_train/{trainID}")
//    public ResponseEntity<HttpStatus> addTrain(@PathVariable Long id, @PathVariable Long trainID){
//        Optional<Wagon> w = service.findWagonById(id);
//        Optional<Train> t = trainService.findTrain(trainID);
//        if (w.isPresent() && t.isPresent()) {
//            w.get().setTrain(t.get());
//            service.save(w.get());
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
}
