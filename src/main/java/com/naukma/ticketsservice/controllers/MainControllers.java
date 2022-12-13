package com.naukma.ticketsservice.controllers;

import com.naukma.ticketsservice.aspects.LogExecTime;
import com.naukma.ticketsservice.run.SearchDto;
import com.naukma.ticketsservice.station.StationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainControllers {

    private final StationService stationService;

    public MainControllers(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/")
    @LogExecTime
    public String index(Model model) {
        model.addAttribute("searchArgs", new SearchDto());
        model.addAttribute("runs", null);
        model.addAttribute("stations", stationService.getStations());
        return "index";
    }

    @GetMapping("/run")
    public String runIndex() {return "run/runs"; }
}
