package com.naukma.ticketsservice.station;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AdjacentStationDto {


    @NotNull
    private Long adjacentStationID;

    @Min(1)
    @Max(10000)
    private int distance;

    public AdjacentStationDto() {}

    public Long getAdjacentStationID() {
        return adjacentStationID;
    }

    public void setAdjacentStationID(Long adjacentStationID) {
        this.adjacentStationID = adjacentStationID;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
