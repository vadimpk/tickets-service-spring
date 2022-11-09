package com.naukma.ticketsservice.station;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StationDto {

    private String name;

    public StationDto(@JsonProperty(value = "name", required = true)String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
