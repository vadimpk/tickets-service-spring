package com.naukma.ticketsservice.station;

import com.naukma.ticketsservice.TicketsServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("api/v1")
@RestController
public class StationController {

    static final Logger log =
            LoggerFactory.getLogger(TicketsServiceApplication.class);

    private final StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @PostMapping("/station")
    public ResponseEntity<Station> create(@RequestBody StationDto station) {

        // check if name is unique
        Optional<Station> check = stationService.findByName(station.getName());
        if (check.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Station st = stationService.createStation(new Station(station.getName()));
        return new ResponseEntity<>(st, HttpStatus.OK);
    }

    @GetMapping("/station/{id}")
    public ResponseEntity<Station> show(@PathVariable String id) {
        Optional<Station> station = stationService.findById(Long.valueOf(id));
        return station.map(t -> new ResponseEntity<>(t,HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/station/{id}")
    public ResponseEntity<Station> update(@PathVariable Long id, @Valid @RequestBody StationDto station) {
        // check if such station exists
        Optional<Station> stationToChange = stationService.findById(id);
        if (stationToChange.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // check if new name is not unique
        Optional<Station> check = stationService.findByName(station.getName());
        if (check.isPresent() && !check.get().getName().equals(stationToChange.get().getName())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Station newStation = stationService.update(id, stationToChange.get());
        if (newStation == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newStation, HttpStatus.OK);
    }

    @DeleteMapping("station/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        Optional<Station> st = stationService.findById(id);
        if (st.isPresent()) {
            stationService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
