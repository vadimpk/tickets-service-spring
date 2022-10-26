package com.naukma.ticketsservice.train;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class WagonDto {
    private String name;
    private int numberOfSeats;
    private Long trainID;

    public WagonDto(@JsonProperty(value = "name", required = true) String name,
                 @JsonProperty("number_of_seats") int numberOfSeats,
                    @JsonProperty("train_id") Long trainID) {

        this.name = name;
        this.numberOfSeats = numberOfSeats;
        this.trainID = trainID;
    }
    

    public String getName() {
        return name;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public Long getTrainID() {
        return trainID;
    }
}
