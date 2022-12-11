package com.naukma.ticketsservice.run;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class RunDto {

    @NotBlank
    private String name;
    private Long routeId;
    private Long trainId;

    @DateTimeFormat(pattern = "HH:mm")
    private Date departureTime;
    @DateTimeFormat(pattern = "HH:mm")
    private Date arrivalTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date arrivalDate;

    public RunDto(@JsonProperty(value = "name", required = true)String name,
                  @JsonProperty(value = "route_id", required = true)Long routeId,
                  @JsonProperty(value = "train_id", required = true)Long trainId,
                  @JsonProperty(value = "departure_time", required = true)Date departureTime,
                  @JsonProperty(value = "arrival_time", required = true)Date arrivalTime,
                  @JsonProperty(value = "departure_date", required = true) Date departureDate,
                  @JsonProperty(value = "arrival_date", required = true) Date arrivalDate) {
        this.name = name;
        this.routeId = routeId;
        this.trainId = trainId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    public RunDto() {

    }

    public Long getRouteId() {
        return routeId;
    }

    public Long getTrainId() {
        return trainId;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() { return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
}
