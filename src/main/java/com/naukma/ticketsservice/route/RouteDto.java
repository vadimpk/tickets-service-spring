package com.naukma.ticketsservice.route;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RouteDto {

    private List<Long> transitionalStationIDs;

    public RouteDto(@JsonProperty(value = "stations", required = true) List<Long> transitionalStationIDs) {
        this.transitionalStationIDs = transitionalStationIDs;
    }

    public List<Long> getTransitionalStationIDs() {
        return transitionalStationIDs;
    }
}
