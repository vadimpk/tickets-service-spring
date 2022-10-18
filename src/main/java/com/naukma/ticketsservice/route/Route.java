package com.naukma.ticketsservice.route;

import com.naukma.ticketsservice.station.Station;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

public class Route {

    private final UUID id;
    private Station startStation;
    private Station finishStation;
    private List<Station> stations;
    private int distance;
    private Duration duration;



    public Route(Station startStation, Station finishStation, List<Station> stations, int distance, Duration duration) {
        this.id = UUID.randomUUID();
        this.startStation = startStation;
        this.finishStation = finishStation;
        this.stations = stations;
        this.distance = distance;
        this.duration = duration;
    }

    public UUID getId() {
        return id;
    }

    public Station getStartStation() {
        return startStation;
    }

    public void setStartStation(Station startStation) {
        this.startStation = startStation;
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

    public Duration getDuration() {
        return duration;
    }
    public void setDuration(Duration duration) {
        this.duration = duration;
    }

}
