package com.naukma.ticketsservice.ticket;

import com.naukma.pricemanager.NoSuchCurrencyException;
import com.naukma.pricemanager.PriceManager;
import com.naukma.ticketsservice.TicketsServiceApplication;
import com.naukma.ticketsservice.run.Run;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Ticket createTicket(Run run, TicketDto ticketDto) {

        double price =  priceManager.setPrice(run.getRoute().getDistance());
        if (!ticketDto.getCurrency().equals("USD")) price = priceManager.convertPriceTo(ticketDto.getCurrency(), price);
        log.info("price for run = " + run.getId() + ": " + price + " " + ticketDto.getCurrency());
        Ticket ticket = new Ticket(run, ticketDto.getCurrency(), price);
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
