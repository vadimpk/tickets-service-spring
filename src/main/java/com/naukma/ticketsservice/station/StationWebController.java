package com.naukma.ticketsservice.station;

import com.naukma.ticketsservice.aspects.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class StationWebController {

    private final StationService stationService;

    @Autowired
    public StationWebController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/admin/stations")
    @LogExecTime
    @LogInAndOutArgs
    public String stationPanel(Model model) {
        model.addAttribute("stationsMap", stationService.getStationsMap());
        model.addAttribute("newStation", new StationDto());
        model.addAttribute("newAdjacentStation", new AdjacentStationDto());
        return "station/station";
    }

    @PostMapping("/admin/stations/create")
    @LogExecTime
    @LogInAndOutArgs
    public String createStationFromAdminPanel(@Valid @ModelAttribute("newStation") StationDto station,
                               BindingResult result,
                               Model model) {

        // check if name is unique
        Optional<Station> check = stationService.findByName(station.getName());
        if (check.isPresent()) {
            result.rejectValue("name", null,
                    "Name is taken");
            return "redirect:?failedCreation&error=name_is_taken";
        }

        if (result.hasErrors()) {
            return "redirect:?failedCreation&error=bad_request";
        }

        stationService.createStation(new Station(station.getName()));
        return "redirect:/admin/stations";
    }


    @PostMapping("/admin/stations/update/{id}")
    @LogExecTime
    @LogInAndOutArgs
    public String updateStationFromAdminPanel(@Valid @ModelAttribute("newStation") StationDto station,
                                              @PathVariable Long id,
                                              BindingResult result,
                                              Model model) {

        // check if name is unique
        Optional<Station> check = stationService.findById(id);
        if (check.isEmpty()) {
            result.rejectValue("name", null,
                    "No such obj");
            return "redirect:/admin/stations?failedUpdate&error=no_such_station";
        }

        // check if name is unique
        Optional<Station> checkName = stationService.findByName(station.getName());
        if (checkName.isPresent()) {
            result.rejectValue("name", null,
                    "Name is taken");
            return "redirect:/admin/stations?failedUpdate&error=name_is_taken";
        }

        if (result.hasErrors()) {
            return "redirect:/admin/stations?failedUpdate&error=bad_request";
        }

        check.get().setName(station.getName());

        stationService.update(check.get());
        return "redirect:/admin/stations";
    }


    @PostMapping("/admin/stations/addAdjacent/{id}")
    @LogExecTime
    @LogInAndOutArgs
    public String addAdjacentStationFromAdminPanel(@Valid @ModelAttribute("newAdjacentStation") AdjacentStationDto adjacentStation,
                                              @PathVariable Long id,
                                              BindingResult result,
                                              Model model) {

        // check if exists
        Optional<Station> check = stationService.findById(id);
        if (check.isEmpty()) {
            result.rejectValue("name", null,
                    "No such obj");
            return "redirect:/admin/stations?failedUpdate&error=no_such_station";
        }

        if (result.hasErrors()) {
            return "redirect:/admin/stations?failedUpdate&error=bad_request";
        }

        // check if adjacent exists
        Optional<Station> checkAdj = stationService.findById(adjacentStation.getAdjacentStationID());
        if (checkAdj.isEmpty()) {
            result.rejectValue("name", null,
                    "No such obj");
            return "redirect:/admin/stations?failedUpdate&error=no_such_station";
        }


        stationService.addAdjacentStation(check.get(), checkAdj.get(), adjacentStation.getDistance());

        return "redirect:/admin/stations";
    }

    @PostMapping("/admin/stations/delete/{id}")
    @LogExecTime
    @LogInAndOutArgs
    public String deleteStationFromAdminPanel(@PathVariable Long id, Model model) {
        Optional<Station> st = stationService.findById(id);
        if (st.isPresent()) {
            if (stationService.delete(id))
                return "redirect:/admin/stations";
            return "redirect:/admin/stations/?failedDeletion&error=delete_routes_with_such_station_first";
        }
        return "redirect:/admin/stations/?failedDeletion&error=no_such_station";
    }

}
