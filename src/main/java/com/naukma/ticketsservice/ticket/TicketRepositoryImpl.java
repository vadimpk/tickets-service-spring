package com.naukma.ticketsservice.ticket;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class TicketRepositoryImpl implements TicketRepository {
    @Override
    public void add(Ticket ticket) {

    }

    @Override
    public Ticket read(UUID id) {
        return null;
    }

    @Override
    public List<Ticket> readAll() {
        return null;
    }

    @Override
    public Ticket update(UUID id, Ticket ticket) {
        return null;
    }

    @Override
    public Ticket delete(UUID id) {
        return null;
    }

    @Override
    public List<Ticket> readByRun(UUID RunID) {
        return null;
    }
}
