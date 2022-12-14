package com.naukma.ticketsservice.ticket;

import com.naukma.ticketsservice.run.Run;
import com.naukma.ticketsservice.user.User;

import java.util.List;
import java.util.Optional;

public interface TicketService {

    Ticket createTicket(Run run, TicketDto ticketDto, User user);
    // List<Route> findTicket(); // when user is created
    List<Ticket> findTicketsByRun(Run run);
    void delete(Long id);
    Optional<Ticket> findTicketById(Long id);
    Ticket update(Ticket ticket);

    List<Ticket> findTicketsByUser(User user);

    void setTicketReturned(Ticket ticket);
}
