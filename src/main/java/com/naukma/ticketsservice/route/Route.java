package com.naukma.ticketsservice.route;

import com.naukma.ticketsservice.station.Station;
import java.util.List;

public class Route {

    private Station startingStation;
    private Station finishStation;
    private List<Station> stations;
    private int distance;
//    private Time duration;

    public Route(){

    }

    public Station getStartingStation() {
        return startingStation;
    }

    public void setStartingStation(Station startingStation) {
        this.startingStation = startingStation;
    }

    public Station getFinishStation() {
        return finishStation;
    }

    public void setFinishStation(Station finishStation) {
        this.finishStation = finishStation;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
