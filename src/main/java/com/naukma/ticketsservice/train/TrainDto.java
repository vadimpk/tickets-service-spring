package com.naukma.ticketsservice.train;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

public class TrainDto {

    private Long id;
    @Pattern(regexp = "[a-zA-z\\d]+", message = "train name length must be of 3 to 16 element with no special parameter")
    @Size(min = 3,max = 16)
    private String name;

    @Min(10)
    @Max(200)
    private int speed;

    private Set<String> wagons;
    private Set<String> runs;
    public TrainDto(@JsonProperty(value = "id", required = true) long id,
                    @JsonProperty(value = "name", required = true) String name,
                    @JsonProperty("speed") int speed) {
        this.id = id;
        this.name = name;
        this.speed = speed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<String> getWagons() {
        return wagons;
    }

    public void setWagons(Set<String> wagons) {
        this.wagons = wagons;
    }

    public Set<String> getRuns() {
        return runs;
    }

    public void setRuns(Set<String> runs) {
        this.runs = runs;
    }
}
