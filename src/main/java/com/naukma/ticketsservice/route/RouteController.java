package com.naukma.ticketsservice.route;

import com.naukma.ticketsservice.TicketsServiceApplication;
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

    private final RouteService service;
    private final StationService stationService;

    @Autowired
    public RouteController(RouteService service, StationService stationService) {
        this.service = service;
        this.stationService = stationService;
    }

    @PostMapping("/route")
    public ResponseEntity<Route> createRoute(@RequestBody RouteDto routeDto) {

        List<Long> stationsIds = routeDto.getTransitionalStationIDs();
        Route route = service.createRoute(stationsIds);
        if (route == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(route, HttpStatus.OK);
    }

    @GetMapping("/route/{id}")
    public ResponseEntity<Route> show(@PathVariable Long id){
        Optional<Route> route = service.findRouteById(id);
        return route.map(t -> new ResponseEntity<>(t, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/route/{start_station_id}/{end_station_id}")
    public ResponseEntity<List<Route>> show(@PathVariable Long start_station_id, @PathVariable Long end_station_id) {
        Optional<Station> startStation = stationService.findById(start_station_id);
        Optional<Station> endStation = stationService.findById(end_station_id);

        if (startStation.isEmpty() || endStation.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        List<Route> routes = service.findRoutes(startStation.get(), endStation.get());
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @GetMapping("/route")
    public ResponseEntity<List<Route>> showAll(){
        return new ResponseEntity<>(service.getRoutes(), HttpStatus.OK);
    }

    @PutMapping("/route/{id}")
    public ResponseEntity<Route> updateRoute(@RequestBody RouteDto routeDto, @PathVariable Long id) {

        Optional<Route> route = service.findRouteById(id);
        if (route.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<Long> stationsIds = routeDto.getTransitionalStationIDs();
        Route newRoute = service.updateRoute(route.get(), stationsIds);
        if (newRoute == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newRoute, HttpStatus.OK);
    }

    @DeleteMapping("route/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        Optional<Route> route = service.findRouteById(id);
        if (route.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
