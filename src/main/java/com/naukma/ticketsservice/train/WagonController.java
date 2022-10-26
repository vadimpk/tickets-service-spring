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

    @Autowired
    public WagonController(WagonService service) {
        this.service = service;
    }

    @GetMapping("/wagon")
    public ResponseEntity<List<Wagon>> wagon(){
        return new ResponseEntity<>(service.getWagons(), HttpStatus.OK);
    }

    @GetMapping("/wagon/{name}")
    public ResponseEntity<Wagon> show(@PathVariable String name){
        Optional<Wagon> w = service.findWagon(name);
        return w.map(wagon -> new ResponseEntity<>(wagon, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/wagon")
    public ResponseEntity<Wagon> addWagon(@RequestBody Wagon wagon){
        // check if name is unique
        Optional<Wagon> check = service.findWagon(wagon.getName());
        if (check.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(service.createWagon(wagon), HttpStatus.OK);
    }

    @PutMapping("/wagon/{name}")
    public ResponseEntity<Wagon> update(@PathVariable String name, @RequestBody Wagon wagon) {
        // check if new name is not unique
        Optional<Wagon> check = service.findWagon(wagon.getName());
        if (check.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>((service.update(name, wagon) > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND));
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
