package com.naukma.ticketsservice.ticket;

import com.naukma.ticketsservice.runs.Run;

import java.util.List;
import java.util.UUID;

public interface TicketService {

    void createTicket(Run run);
    // List<Route> findTicket(); // when user is created

    List<Ticket> getRunTickets(Run run);
    Ticket delete(UUID id);
}
