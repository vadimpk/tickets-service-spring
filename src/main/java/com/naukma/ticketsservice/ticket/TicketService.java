package com.naukma.ticketsservice.ticket;

import com.naukma.ticketsservice.run.Run;

import java.util.List;
import java.util.Optional;

public interface TicketService {

    Ticket createTicket(Run run);
    // List<Route> findTicket(); // when user is created
    List<Ticket> findTicketsByRun(Run run);
    void delete(Long id);
    Optional<Ticket> findTicketById(Long id);
}
