package com.naukma.pricemanager;

public class PriceManager {

    private final PriceManagerConfig config;

    public PriceManager(PriceManagerConfig config) {
        this.config = config;
    }

//    public int setPrice(Run run) {
//        return run.getRoute().getDistance() * config.getProperties().getDefaultRate();
//    }

    public int setPrice (int distance) {
        return distance * config.getProperties().getDefaultRate();
    }
}
