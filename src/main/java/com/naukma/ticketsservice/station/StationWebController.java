package com.naukma.ticketsservice.station;

import com.naukma.ticketsservice.user.User;
import com.naukma.ticketsservice.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Controller
public class StationWebController {

    private final StationService stationService;

    @Autowired
    public StationWebController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/admin/stations")
    public String stationPanel(Model model) {
        model.addAttribute("stationsMap", stationService.getStationsMap());
        model.addAttribute("newStation", new StationDto());
        return "station/station";
    }

    @PostMapping("/admin/stations/create")
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
        model.addAttribute("stationsMap", stationService.getStationsMap());
        model.addAttribute("newStation", new StationDto());
        return "station/station";
    }


    @PostMapping("/admin/stations/update/{id}")
    public String updateStationFromAdminPanel(@Valid @ModelAttribute("newStation") StationDto station,
                                              @PathVariable Long id,
                                              BindingResult result,
                                              Model model) {

        // check if name is unique
        Optional<Station> check = stationService.findById(id);
        if (check.isEmpty()) {
            result.rejectValue("name", null,
                    "No such obj");
            return "redirect:&failedUpdate&error=no_such_object";
        }

        if (result.hasErrors()) {
            return "redirect:&failedUpdate&error=bad_request";
        }

        stationService.update(id, new Station(station.getName()));
        model.addAttribute("stationsMap", stationService.getStationsMap());
        model.addAttribute("newStation", new StationDto());
        return "station/station";
    }

    @PostMapping("/admin/stations/delete/{id}")
    public String deleteStationFromAdminPanel(@PathVariable Long id, Model model) {
        Optional<Station> st = stationService.findById(id);
        if (st.isPresent()) {
            stationService.delete(id);
            model.addAttribute("stationsMap", stationService.getStationsMap());
            model.addAttribute("newStation", new StationDto());
            return "station/station";
        }
        return "redirect:?failedDeletion";
    }

}
