package com.naukma.ticketsservice.route;

import com.naukma.ticketsservice.TicketsServiceApplication;
import com.naukma.ticketsservice.station.Station;
import com.naukma.ticketsservice.station.StationService;
import com.naukma.ticketsservice.train.Wagon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1")
@RestController
public class RouteController {

    static final Logger log =
            LoggerFactory.getLogger(TicketsServiceApplication.class);

    private RouteService service;
    private StationService stationService;

    @Autowired
    public RouteController(RouteService service, StationService stationService) {
        this.service = service;
        this.stationService = stationService;
    }

    @PostMapping("/route")
    public ResponseEntity<Route> createRoute(@RequestBody RouteDto routeDto) {

        log.info("Transitional stations' ids: " + routeDto.getTransitionalStationIDs());

        // stations that were not found won't be included in this list
        List<Station> stations = stationService.findAllById(routeDto.getTransitionalStationIDs());

        for (Station st: stations) {
            log.info("Transitional station: " + st.getName());
        }

        if (stations.size() <= 2) {return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}

        Route route = service.createRoute(stations);
        if (route == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(route, HttpStatus.OK);
    }
}
