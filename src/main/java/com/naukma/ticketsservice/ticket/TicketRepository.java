package com.naukma.ticketsservice.ticket;

import com.naukma.ticketsservice.run.Run;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    // TODO: 23.10.2022
    List<Ticket> findByRun(Run run);
}
