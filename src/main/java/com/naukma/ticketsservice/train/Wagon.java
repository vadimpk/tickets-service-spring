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

    @Column(unique = true, nullable = false)
    private String name;

    @Column(name = "number_of_seats", nullable = false)
    private int numberOfSeats;

    public Wagon(@JsonProperty(value = "name", required = true) String name, @JsonProperty("number_of_seats") int numberOfSeats){
        this.id = UUID.randomUUID();
        this.name = name;
        this.numberOfSeats =numberOfSeats;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
