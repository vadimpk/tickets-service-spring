package com.naukma.ticketsservice.ticket;

import com.naukma.ticketsservice.CRUDOperations;

import java.util.List;
import java.util.UUID;

public interface TicketRepository extends CRUDOperations<Ticket> {
    List<Ticket> readByRun(UUID RunID);
}
