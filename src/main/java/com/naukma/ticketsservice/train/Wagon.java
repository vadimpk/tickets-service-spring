package com.naukma.ticketsservice.train;

import java.util.UUID;

public class Wagon {

    private final UUID id;

    private int numberOfSits;

    public Wagon(UUID id, int numberOfSits){
        this.id = id;
        this.numberOfSits=numberOfSits;
    }

    public int getNumberOfSits() {
        return numberOfSits;
    }

    public void setNumberOfSits(int numberOfSits) {
        this.numberOfSits = numberOfSits;
    }
}
