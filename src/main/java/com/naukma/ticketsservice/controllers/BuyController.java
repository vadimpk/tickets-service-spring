package com.naukma.ticketsservice.controllers;

import com.naukma.ticketsservice.TicketsServiceApplication;
import com.naukma.ticketsservice.aspects.LogInAndOutArgs;
import com.naukma.ticketsservice.run.Run;
import com.naukma.ticketsservice.run.RunService;
import com.naukma.ticketsservice.station.Station;
import com.naukma.ticketsservice.station.StationService;
import com.naukma.ticketsservice.ticket.TicketDto;
import com.naukma.ticketsservice.ticket.TicketService;
import com.naukma.ticketsservice.user.User;
import com.naukma.ticketsservice.user.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class BuyController {


    static final Logger log =
            LoggerFactory.getLogger(TicketsServiceApplication.class);

    private final RunService runService;
    private final StationService stationService;
    private final TicketService ticketService;

    @Autowired
    public BuyController(RunService runService, StationService stationService, TicketService ticketService) {
        this.runService = runService;
        this.stationService = stationService;
        this.ticketService = ticketService;
    }

    @GetMapping("/buy/{runName}")
    @LogInAndOutArgs
    public String buyTicketForRun(@ModelAttribute("selectedStartStationId") Long selectedStartStationId,
                                  @ModelAttribute("selectedFinishStationId") Long selectedFinishStationId,
                                  Model model, @PathVariable String runName) {
        Optional<Run> run = runService.find(runName);
        if (run.isEmpty())
            return "redirect:?&error=not_found_such_run";

        log.info("buying ticket on run:" + runName);

        Optional<Station> startStation =  stationService.findById(selectedStartStationId);
        Optional<Station> finishStation =  stationService.findById(selectedFinishStationId);
        if (startStation.isEmpty() || finishStation.isEmpty())
            return "redirect:?failedSearch&error=not_found_station";

        model.addAttribute("startStation", startStation.get());
        model.addAttribute("finishStation", finishStation.get());
        model.addAttribute("run", run.get());
        model.addAttribute("price", 100);
        model.addAttribute("ticketDto", new TicketDto(run.get().getId(), "USD"));
        return "buy";
    }

    @PostMapping("/buy")
    @LogInAndOutArgs
    public String buyTicketForRun(@ModelAttribute("ticketDto") TicketDto ticketDto) {
        Optional<Run> run = runService.find(ticketDto.getRunId());
        if (run.isEmpty())
            return "redirect:?failedSearch&error=not_found_station";

        User user = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        ticketService.createTicket(run.get(), ticketDto, user);
        run.get().incrementTakenSeats();

        runService.update(run.get().getId(), run.get());
        return "redirect:?successful_bought";
    }
}
