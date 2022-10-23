package com.naukma.ticketsservice.ticket;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    // TODO: 23.10.2022  
//    List<Ticket> readByRun(Run run);
}
