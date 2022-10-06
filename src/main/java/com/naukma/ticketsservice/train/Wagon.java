package com.naukma.ticketsservice.train;

public class Wagon {

    private int numberOfSits;

    public Wagon(int numberOfSits){
        this.numberOfSits=numberOfSits;
    }

    public int getNumberOfSits() {
        return numberOfSits;
    }

    public void setNumberOfSits(int numberOfSits) {
        this.numberOfSits = numberOfSits;
    }
}
