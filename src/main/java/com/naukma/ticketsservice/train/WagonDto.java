package com.naukma.ticketsservice.train;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class WagonDto {

    @Pattern(regexp = "[a-zA-z0-9]+", message = "wagon name length must be of 3 to 16 element with no special parameter")
    @Size(min = 3,max = 16)
    private String name;

    @Min(10)
    @Max(200)
    private int numberOfSeats;

    @Nullable
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
