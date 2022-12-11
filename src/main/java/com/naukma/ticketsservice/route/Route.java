package com.naukma.ticketsservice.route;

import com.naukma.ticketsservice.station.Station;

import javax.persistence.*;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "start_station_fk", nullable = false)
    private Station startStation;

    @ManyToOne
    @JoinColumn(name = "finish_station_fk", nullable = false)
    private Station finishStation;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Station> stations;

    private int distance;

    public Route() {
    }

    public Route(Station startStation, Station finishStation, List<Station> stations, int distance) {
        this.startStation = startStation;
        this.finishStation = finishStation;
        this.stations = stations;
        this.distance = distance;
    }

    public Long getId() {
        return id;
    }

    public String getName() { return getStartStation().getName()+" - "+getFinishStation().getName();}

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
