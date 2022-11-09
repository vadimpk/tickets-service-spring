package com.naukma.ticketsservice.wagon;

import com.naukma.ticketsservice.exceptions.NoSuchWagonException;
import com.naukma.ticketsservice.exceptions.NonUniqueWagonNameException;
import com.naukma.ticketsservice.train.Train;
import com.naukma.ticketsservice.train.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1")
@RestController
public class WagonController {

    private final WagonService service;
    private final TrainService trainService;

    @Autowired
    public WagonController(WagonService service, TrainService trainService) {
        this.service = service;
        this.trainService = trainService;
    }

    @GetMapping("/wagon")
    public ResponseEntity<List<Wagon>> wagon(){
        return new ResponseEntity<>(service.getWagons(), HttpStatus.OK);
    }

    @GetMapping("/wagon/{id}")
    public ResponseEntity<Wagon> show(@PathVariable Long id){
        Optional<Wagon> w = service.findWagonById(id);
        return w.map(wagon -> new ResponseEntity<>(wagon, HttpStatus.OK)).orElseThrow(NoSuchWagonException::new);
    }

    @PostMapping("/wagon")
    public ResponseEntity<Wagon> addWagon(@Valid @RequestBody WagonDto wagon){
        // check if name is unique
        Optional<Wagon> check = service.findWagonByName(wagon.getName());
        if (check.isPresent()) {
            throw new NoSuchWagonException();
        }

        // check if entered train exists otherwise set to null
        Long trainID = (wagon.getTrainID() != null) ? wagon.getTrainID() : 0;
        Optional<Train> t = trainService.findTrain(trainID);
        Train train = null;
        if (t.isPresent()) {
            train = t.get();
        }

        Wagon w = new Wagon(wagon.getName(), wagon.getNumberOfSeats(), train);
        return new ResponseEntity<>(service.createWagon(w), HttpStatus.OK);
    }

    @PutMapping("/wagon/{id}")
    public ResponseEntity<Wagon> update(@PathVariable Long id, @Valid @RequestBody WagonDto wagon) {

        // check if such wagon exists
        Optional<Wagon> wagonToChange = service.findWagonById(id);
        if (wagonToChange.isEmpty()) {
            throw new NoSuchWagonException();
        }

        // check if new name is not unique
        Optional<Wagon> check = service.findWagonByName(wagon.getName());
        if (check.isPresent() && !check.get().getName().equals(wagonToChange.get().getName())) {
            throw new NonUniqueWagonNameException();
        }

        // check if entered train exists otherwise set to the one that was before
        if (wagon.getTrainID() != null) {
            Optional<Train> t = trainService.findTrain(wagon.getTrainID());
            if (t.isPresent()) {
                wagonToChange.get().setTrain(t.get());
            }
        }

        wagonToChange.get().setName(wagon.getName());
        wagonToChange.get().setNumberOfSeats(wagon.getNumberOfSeats());
        return new ResponseEntity<>(service.save(wagonToChange.get()), HttpStatus.OK);
    }

    @DeleteMapping("wagon/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        Optional<Wagon> w = service.findWagonById(id);
        if (w.isPresent()) {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new NoSuchWagonException();
    }

//    @GetMapping("/wagon/{id}/train")
//    public ResponseEntity<Train> showTrain(@PathVariable Long id){
//        Optional<Wagon> w = service.findWagonById(id);
//        return w.map(wagon -> new ResponseEntity<>(wagon.getTrain(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

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

    @ExceptionHandler(value = NoSuchWagonException.class)
    public ResponseEntity<String> exception(NoSuchWagonException exception) {
        return new ResponseEntity<>("Not found such wagon", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NonUniqueWagonNameException.class)
    public ResponseEntity<String> exception(NonUniqueWagonNameException exception) {
        return new ResponseEntity<>("Such wagon name is already present", HttpStatus.CONFLICT);
    }
}
