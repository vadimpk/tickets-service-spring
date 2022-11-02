package com.naukma.ticketsservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.slf4j.MarkerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TicketsServiceApplication {

    static final Logger log =
            LoggerFactory.getLogger(TicketsServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TicketsServiceApplication.class, args);

        log.debug("Starting TicketsServiceApplication application in debug with {} args", args.length);
        log.debug(MarkerFactory.getMarker("CONFIDENTIAL"),"This is a confidential message....");
        log.info("some info");
        log.warn("some warn");
        log.error("some error");
        log.trace("some trace");


        new Thread(() -> {
            MDC.put("request_id", "128");
            MDC.put("application", "runner");
            log.debug("What MDC will give me?");
            MDC.clear();
        }).start();

    }

}
