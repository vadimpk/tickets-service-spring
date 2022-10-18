package com.naukma.ticketsservice.ticket.pricemanager;

import com.naukma.ticketsservice.runs.Run;

public class PriceManager {

    private final PriceManagerConfig config;

    public PriceManager(PriceManagerConfig config) {
        this.config = config;
    }

    public int setPrice(Run run) {
        return run.getRoute().getDistance() * config.getProperties().getDefaultRate();
    }
}
