package com.naukma.ticketsservice.station;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class StationDto {

    @NotBlank
    private String name;

    public StationDto(@JsonProperty(value = "name", required = true)String name) {
        this.name = name;
    }

    public StationDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
