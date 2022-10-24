package com.naukma.ticketsservice.train;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Wagon {

    @Id
    private UUID id;

    @Column(name = "number_of_seats")
    private int numberOfSeats;

    public Wagon(@JsonProperty("id") UUID id,
                 @JsonProperty("number_of_seats") int numberOfSits){
        this.id = id;
        this.numberOfSeats =numberOfSits;
    }

    public Wagon() {

    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSits) {
        this.numberOfSeats = numberOfSits;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wagon wagon = (Wagon) o;

        return id.equals(wagon.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
