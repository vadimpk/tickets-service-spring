package com.naukma.ticketsservice.route;

import com.naukma.ticketsservice.station.Station;

import javax.persistence.*;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Entity
public class Route {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "start_station_fk", nullable = false)
    private Station startStation;

    @ManyToOne
    @JoinColumn(name = "finish_station_fk", nullable = false)
    private Station finishStation;

    @ManyToMany
    private List<Station> stations;
    private int distance;
    private Duration duration;

    public Route() {
    }

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

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        return id.equals(route.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
