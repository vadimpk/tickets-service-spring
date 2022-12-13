package com.naukma.ticketsservice.route;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

public class RouteDto {

    @NotNull
    private List<Long> transitionalStationIDs;

    @NotNull
    private Long startStationID;
    @NotNull
    private Long finishStationID;

    public RouteDto(@JsonProperty(value = "stations", required = true) List<Long> transitionalStationIDs,
                    @JsonProperty(value = "start_station", required = true) Long startStation,
                    @JsonProperty(value = "finish_station", required = true) Long finishStation) {
        this.transitionalStationIDs = transitionalStationIDs;
        this.startStationID = startStation;
        this.finishStationID = finishStation;
    }

    public RouteDto() {}

    public List<Long> getTransitionalStationIDs() {
        return transitionalStationIDs;
    }


    public void setTransitionalStationIDs(List<Long> transitionalStationIDs) {
        this.transitionalStationIDs = transitionalStationIDs;
    }

    public Long getStartStationID() {
        return startStationID;
    }

    public void setStartStationID(Long startStationID) {
        this.startStationID = startStationID;
    }

    public Long getFinishStationID() {
        return finishStationID;
    }

    public void setFinishStationID(Long finishStationID) {
        this.finishStationID = finishStationID;
    }
}
