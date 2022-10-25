package com.naukma.ticketsservice.train;

import com.naukma.ticketsservice.TicketsServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1")
@RestController
public class WagonController {

    private final WagonService service;

    static final Logger log =
            LoggerFactory.getLogger(TicketsServiceApplication.class);


    @Autowired
    public WagonController(WagonService service) {
        this.service = service;
    }

    @GetMapping("/wagons")
    public ResponseEntity<List<Wagon>> wagon(){
        return new ResponseEntity<>(service.getWagons(), HttpStatus.OK);
    }

    @GetMapping("/wagon/{name}")
    public ResponseEntity<Wagon> show(@PathVariable String name){
        Optional<Wagon> w = service.findWagon(name);
        if (w.isPresent()) {
            return new ResponseEntity<>(w.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/wagon")
    public ResponseEntity<Wagon> addWagon(@RequestBody Wagon wagon){
        Wagon w = service.createWagon(wagon);
        return new ResponseEntity<>(w, HttpStatus.OK);
    }

    @PutMapping("/wagon/{name}")
    public ResponseEntity<Wagon> update(@PathVariable String name, @RequestBody Wagon wagon) {
        log.info(wagon.getName(), wagon.getNumberOfSeats());
        Optional<Wagon> w = service.findWagon(name);
        if (w.isPresent()) {
            return new ResponseEntity<>(service.update(w.get().getId(), wagon), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("wagon/{name}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String name){
        Optional<Wagon> w = service.findWagon(name);
        if (w.isPresent()) {
            service.delete(w.get().getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
