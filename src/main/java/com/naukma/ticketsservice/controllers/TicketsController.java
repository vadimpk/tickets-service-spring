package com.naukma.ticketsservice.controllers;

import com.naukma.ticketsservice.aspects.LogExecTime;
import com.naukma.ticketsservice.ticket.Ticket;
import com.naukma.ticketsservice.ticket.TicketService;
import com.naukma.ticketsservice.user.User;
import com.naukma.ticketsservice.user.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class TicketsController {

    private final TicketService ticketService;

    public TicketsController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/tickets")
    @LogExecTime
    public String tickets(Model model) {
        User user = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        List<Ticket> tickets = ticketService.findTicketsByUser(user);
        model.addAttribute("tickets", tickets);
        return "tickets/tickets";
    }

    @PostMapping("/tickets/return")
    @LogExecTime
    public String returnTicket(@ModelAttribute("ticketId") Long ticketId,
                               Model model) {
        Optional<Ticket> ticket = ticketService.findTicketById(ticketId);
        if (ticket.isEmpty())
            return "redirect:/tickets?failedSearch";
        ticket.get().getRun().decrementTakenSeats();
        ticketService.setTicketReturned(ticket.get());

        User user = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        List<Ticket> tickets = ticketService.findTicketsByUser(user);
        model.addAttribute("tickets", tickets);
        return "redirect:/tickets?successfullyReturned";
    }

}
