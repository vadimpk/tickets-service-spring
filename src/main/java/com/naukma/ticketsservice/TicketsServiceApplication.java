package com.naukma.ticketsservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


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
