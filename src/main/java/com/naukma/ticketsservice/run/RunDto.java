package com.naukma.ticketsservice.run;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.naukma.ticketsservice.route.Route;
import com.naukma.ticketsservice.train.Train;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.sql.Time;
import java.util.List;

public class RunDto {

    private String name;
    private Long routeId;
    private Long trainId;
    private Time departureTime;
    private Time arrivalTime;

    public RunDto(@JsonProperty(value = "name", required = true)String name,
                  @JsonProperty(value = "route_id", required = true)Long routeId,
                  @JsonProperty(value = "train_id", required = true)Long trainId,
                  @JsonProperty(value = "departure_time", required = true)Time departureTime,
                  @JsonProperty(value = "arrival_time", required = true)Time arrivalTime) {
        this.routeId = routeId;
        this.trainId = trainId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Long getRouteId() {
        return routeId;
    }

    public Long getTrainId() {
        return trainId;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public String getName() {
        return name;
    }
}
