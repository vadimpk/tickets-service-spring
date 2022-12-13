package com.naukma.ticketsservice.controllers;

import com.naukma.ticketsservice.TicketsServiceApplication;
import com.naukma.ticketsservice.aspects.LogInAndOutArgs;
import com.naukma.ticketsservice.route.Route;
import com.naukma.ticketsservice.route.RouteService;
import com.naukma.ticketsservice.run.Run;
import com.naukma.ticketsservice.run.RunService;
import com.naukma.ticketsservice.run.SearchDto;
import com.naukma.ticketsservice.station.Station;
import com.naukma.ticketsservice.station.StationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class SearchController {

    static final Logger log =
            LoggerFactory.getLogger(TicketsServiceApplication.class);


    private final RunService runService;
    private final RouteService routeService;
    private final StationService stationService;


    @Autowired
    public SearchController(RunService runService, RouteService routeService, StationService stationService) {
        this.runService = runService;
        this.routeService = routeService;
        this.stationService = stationService;
    }

    @GetMapping("/searchRuns")
    @LogInAndOutArgs
    public String searchForRuns(@Valid @ModelAttribute("searchArgs") SearchDto searchDto,
                                BindingResult result, Model model) {
        Optional<Station> startStation =  stationService.findById(searchDto.getStartStationId());
        Optional<Station> finishStation =  stationService.findById(searchDto.getFinishStationId());
        if (startStation.isEmpty() || finishStation.isEmpty())
            return "redirect:?failedSearch&error=not_found_station";

        List<Route> routes = routeService.findRoutes(startStation.get(), finishStation.get());
        List<Run> runs = new ArrayList<>();
        for (Route route : routes) {
            runs.addAll(runService.findByRouteAndDepartureDate(route, searchDto.getDepartureDate()));
        }
        model.addAttribute("runs", runs);

        log.info("result for searching runs: " + searchDto.getStartStationId() +  " -> " + searchDto.getFinishStationId() +
                " at " + searchDto.getDepartureDate() + ": " + runs );

        if (result.hasErrors()) {
            return "redirect:?failedSearch&error=bad_request";
        }

        model.addAttribute("stations", stationService.getStations());
        model.addAttribute("selectedStartStationId", startStation.get().getId());
        model.addAttribute("selectedFinishStationId", finishStation.get().getId());

        return "index";
    }
}
