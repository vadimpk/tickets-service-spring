package com.naukma.ticketsservice.train;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

public class TrainDto {


    @Pattern(regexp = "[a-zA-z\\d]+", message = "train name length must be of 3 to 16 element with no special parameter")
    @Size(min = 3,max = 16)
    private String name;

    @Min(10)
    @Max(200)
    private int speed;

    @Min(10)
    @Max(10000)
    private int capacity;

    public TrainDto(@JsonProperty(value = "name", required = true) String name,
                    @JsonProperty("speed") int speed) {
        this.name = name;
        this.speed = speed;
    }

    public TrainDto() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
