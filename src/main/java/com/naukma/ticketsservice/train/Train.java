package com.naukma.ticketsservice.train;

import com.naukma.ticketsservice.runs.Run;

import java.util.List;

public class Train {


    private List<Wagon> wagons;
    private int speed;
    private List<Run> runs;

    public Train(){

    }

    public List<Wagon> getWagons() {
        return wagons;
    }

    public void setWagons(List<Wagon> wagons) {
        this.wagons = wagons;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public List<Run> getRuns() {
        return runs;
    }

    public void setRuns(List<Run> runs) {
        this.runs = runs;
    }
}
