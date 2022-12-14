package com.naukma.ticketsservice.ticket;

import com.naukma.ticketsservice.run.Run;
import com.naukma.ticketsservice.run.RunDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TicketWebController {

    private final TicketRepository repository;

    @Autowired
    public TicketWebController(TicketRepository ticketRepository) {
        repository = ticketRepository;
    }

    @GetMapping("/admin/tickets")
    public String index(Model model) {
        List<Ticket> tickets = repository.findAll();
        model.addAttribute("tickets", tickets);
        return "ticket/tickets";
    }
}
