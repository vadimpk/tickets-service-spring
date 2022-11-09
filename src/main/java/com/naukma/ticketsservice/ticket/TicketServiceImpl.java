package com.naukma.ticketsservice.ticket;

import com.naukma.pricemanager.PriceManager;
import com.naukma.ticketsservice.TicketsServiceApplication;
import com.naukma.ticketsservice.run.Run;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    static final Logger log =
            LoggerFactory.getLogger(TicketsServiceApplication.class);

    private TicketRepository repository;
    private PriceManager priceManager;

    public TicketServiceImpl(@Autowired TicketRepository repository, @Autowired PriceManager priceManager) {
        this.repository = repository;
        this.priceManager = priceManager;
    }

    @Override
    public Ticket createTicket(Run run) {

        log.info(String.valueOf(priceManager.covertPriceToUSD(100)));
        int price = priceManager.setPrice(run.getRoute().getDistance());
        Ticket ticket = new Ticket(run);
        repository.saveAndFlush(ticket);

        return ticket;
    }

    @Override
    public List<Ticket> findTicketsByRun(Run run) {
        return repository.findByRun(run);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Ticket> findTicketById(Long id) {
        return repository.findById(id);
    }
}
