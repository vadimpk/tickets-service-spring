package com.naukma.ticketsservice.ticket;

import com.naukma.ticketsservice.TicketsServiceApplication;
import com.naukma.ticketsservice.route.Route;
import com.naukma.ticketsservice.runs.Run;
import com.naukma.ticketsservice.ticket.pricemanager.PriceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
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
        int price = priceManager.setPrice(run);
        log.info(String.valueOf(price));
        repository.add(new Ticket(UUID.randomUUID(), run, price));
    }

    @Override
    public List<Ticket> getRunTickets(Run run) {
        return repository.readByRun(run.getId());
    }

    @Override
    public Ticket delete(UUID id) {
        return repository.delete(id);
    }
}
