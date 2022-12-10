package com.naukma.ticketsservice.station;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class StationDto {

    @NotBlank
    private String name;

    private List<Long> adjacentStationsIDs;

    public StationDto(@JsonProperty(value = "name", required = true) String name,
                      @JsonProperty(value = "adjacentStationsIDs") List<Long> adjacentStationsIDs) {
        this.name = name;
        this.adjacentStationsIDs = adjacentStationsIDs;
    }

    public StationDto() {}

    public String getName() {
        return name;
    }

    public List<Long> getAdjacentStationsIDs() {
        return adjacentStationsIDs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdjacentStationsIDs(List<Long> adjacentStationsIDs) {
        this.adjacentStationsIDs = adjacentStationsIDs;
    }

    @Override
    public String toString() {
        return "Station{name='" + name + "'}";
    }
}
