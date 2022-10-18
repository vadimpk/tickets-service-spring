package com.naukma.ticketsservice.ticket;

import com.naukma.ticketsservice.route.Route;
import com.naukma.ticketsservice.runs.Run;
import com.naukma.ticketsservice.ticket.pricemanager.PriceManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public interface TicketService {

    void createTicket(Run run);
    // List<Route> findTicket(); // when user is created

    List<Ticket> getRunTickets(Run run);
    Ticket delete(UUID id);
}
