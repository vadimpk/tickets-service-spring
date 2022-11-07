package com.naukma.ticketsservice.run;

import com.naukma.ticketsservice.route.Route;
import com.naukma.ticketsservice.train.Train;
import javax.persistence.*;
import java.sql.Time;

@Entity
public class Run {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;

    @ManyToOne
    private Route route;
    @ManyToOne
    private Train train;

    @Column(name = "departure_time")
    private Time departureTime;
    @Column(name = "arrival_time")
    private Time arrivalTime;

    public Run() {}
    public Run(String name, Route route, Train train, Time departureTime, Time arrivalTime) {
        this.name = name;
        this.route = route;
        this.train = train;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }
    public void setRoute(Route route) { this.route = route; }

    public Train getTrain() {
        return train;
    }
    public void setTrain(Train train) {
        this.train = train;
    }

    public Time getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Run run = (Run) o;

        return id.equals(run.id);
    }
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
