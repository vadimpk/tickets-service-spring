package com.naukma.ticketsservice.ticket;

import com.naukma.pricemanager.PriceManager;
import com.naukma.ticketsservice.TicketsServiceApplication;
import com.naukma.ticketsservice.route.Route;
import com.naukma.ticketsservice.runs.Run;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketServiceImpl implements TicketService {

    static final Logger log =
            LoggerFactory.getLogger(TicketsServiceApplication.class);

    private TicketRepository repository;
    private PriceManager priceManager;

    public TicketServiceImpl(@Autowired TicketRepository repository, @Autowired PriceManager priceManager) {

        Route route = new Route(null, null, null, 100, Duration.ZERO);
        Run run = new Run(route);

        this.repository = repository;
        this.priceManager = priceManager;

        createTicket(run);
    }

    @Override
    public void createTicket(Run run) {
        int price = priceManager.setPrice(run.getRoute().getDistance());
        log.info("Price: " + price);
        repository.saveAndFlush(new Ticket(UUID.randomUUID(), run, price));
    }

    @Override
    public List<Ticket> getRunTickets(Run run) {
        return repository.findByRun(run);
    }

    @Override
    public Ticket delete(UUID id) {
        Optional<Ticket> ticket = repository.findById(id);
        if(ticket.isEmpty()) throw new RuntimeException("not such ticket with id " + id);
        repository.delete(ticket.get());
        return ticket.get();
    }
}
