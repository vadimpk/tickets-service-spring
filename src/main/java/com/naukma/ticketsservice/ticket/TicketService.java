package com.naukma.ticketsservice.ticket;

import com.naukma.ticketsservice.run.Run;
import org.json.simple.parser.ParseException;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface TicketService {

    Ticket createTicket(Run run, TicketDto ticketDto);
    // List<Route> findTicket(); // when user is created
    List<Ticket> findTicketsByRun(Run run);
    void delete(Long id);
    Optional<Ticket> findTicketById(Long id);
}
