package com.naukma.ticketsservice;

import com.naukma.ticketsservice.route.Route;
import com.naukma.ticketsservice.runs.Run;
import com.naukma.ticketsservice.ticket.Ticket;
import com.naukma.ticketsservice.ticket.TicketService;
import com.naukma.ticketsservice.ticket.TicketServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Time;
import java.time.Duration;


@SpringBootApplication
public class TicketsServiceApplication {

    static final Logger log =
            LoggerFactory.getLogger(TicketsServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TicketsServiceApplication.class, args);
        log.debug("Starting TicketsServiceApplication application in debug with {} args", args.length);
        log.info("Starting TicketsServiceApplication application with {} args.", args.length);
    }

}
