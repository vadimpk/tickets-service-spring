package com.naukma.ticketsservice;

import com.naukma.pricemanager.PriceManager;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.io.IOException;


@SpringBootApplication
public class TicketsServiceApplication {

    static final Logger log =
            LoggerFactory.getLogger(TicketsServiceApplication.class);

    public static void main(String[] args){
        SpringApplication.run(TicketsServiceApplication.class, args);
        log.debug("Starting TicketsServiceApplication application in debug with {} args", args.length);
    }

}
