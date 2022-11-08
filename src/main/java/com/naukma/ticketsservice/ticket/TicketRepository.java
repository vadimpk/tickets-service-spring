package com.naukma.ticketsservice.ticket;

import com.naukma.ticketsservice.run.Run;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByRun(Run run);
    Optional<Ticket> findById(Long id);
    void deleteById(Long aLong);
}
