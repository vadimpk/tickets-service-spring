package com.naukma.ticketsservice.route;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

public class RouteDto {

    @NotNull
    private List<Long> transitionalStationIDs;

    public RouteDto(@JsonProperty(value = "stations", required = true) List<Long> transitionalStationIDs) {
        this.transitionalStationIDs = transitionalStationIDs;
    }

    public RouteDto() {}

    public List<Long> getTransitionalStationIDs() {
        return transitionalStationIDs;
    }


    public void setTransitionalStationIDs(List<Long> transitionalStationIDs) {
        this.transitionalStationIDs = transitionalStationIDs;
    }
}
