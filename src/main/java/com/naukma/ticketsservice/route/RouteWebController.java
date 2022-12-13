package com.naukma.ticketsservice.route;

import com.naukma.ticketsservice.aspects.LogExecTime;
import com.naukma.ticketsservice.aspects.LogInAndOutArgs;
import com.naukma.ticketsservice.station.Station;
import com.naukma.ticketsservice.station.StationDto;
import com.naukma.ticketsservice.station.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class RouteWebController {

    private final RouteService routeService;
    private final StationService stationService;

    @Autowired
    public RouteWebController(RouteService routeService, StationService stationService) {
        this.stationService = stationService;
        this.routeService = routeService;
    }

    @GetMapping("admin/routes")
    public String routesPanel(Model model) {
        model.addAttribute("routes", routeService.getRoutes());
        model.addAttribute("stations", stationService.getStations());
        model.addAttribute("newRoute", new RouteDto());
        return "routes/routes";
    }

    @PostMapping("admin/routes/create")
    public String createRouteFromAdminPanel(@Valid @ModelAttribute("newRoute") RouteDto route,
                                             BindingResult result,
                                             Model model) {

        if (result.hasErrors()) {
            return "redirect:/admin/routes?failedUpdate&error=bad_request";
        }

        List<Long> stationsIds = route.getTransitionalStationIDs();
        Route newRoute = routeService.create(route.getStartStationID(), stationsIds, route.getFinishStationID());

        if (newRoute == null)
            return "redirect:?failedCreation&error=bad_request_cant_connect_stations";

        return "redirect:/admin/routes";
    }

    @PostMapping("/admin/routes/update/{id}")
    public String updateRouteFromAdminPanel(@Valid @ModelAttribute("newRoute") RouteDto route,
                                              @PathVariable Long id,
                                              BindingResult result,
                                              Model model) {

        Optional<Route> check = routeService.find(id);
        if (check.isEmpty()) {
            result.rejectValue("name", null,
                    "No such obj");
            return "redirect:/admin/routes?failedUpdate&error=no_such_obj";
        }

        if (result.hasErrors()) {
            return "redirect:/admin/routes?failedUpdate&error=bad_request";
        }

        List<Long> stationsIds = route.getTransitionalStationIDs();
        Route newRoute = routeService.update(check.get(), route.getStartStationID(), stationsIds, route.getFinishStationID());

        if (newRoute == null)
            return "redirect:/admin/routes?failedUpdate&error=bad_request_cant_connect_stations";


        return "redirect:/admin/routes";
    }

    @PostMapping("/admin/routes/delete/{id}")
    public String deleteStationFromAdminPanel(@PathVariable Long id, Model model) {
        Optional<Route> route = routeService.find(id);
        if (route.isPresent()) {
            if (routeService.delete(id))
                return "redirect:/admin/routes";
            return "redirect:/admin/routes?failedDeletion&error=delete_runs_with_this_route_first";
        }
        return "redirect:/admin/routes?failedDeletion&error=no_such_route";
    }
}