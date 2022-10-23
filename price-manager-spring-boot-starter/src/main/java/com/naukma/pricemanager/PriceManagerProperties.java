package com.naukma.pricemanager;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "service.pricemanager")
public class PriceManagerProperties {

    private int defaultRate;

    public int getDefaultRate() {
        return defaultRate;
    }

    public void setDefaultRate(int defaultRate) {
        this.defaultRate = defaultRate;
    }
}
