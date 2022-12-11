package com.naukma.ticketsservice.run;

import com.naukma.ticketsservice.route.Route;
import com.naukma.ticketsservice.train.Train;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    @Column
    private int takenSeats;

    @Column(name = "departure_time")
    private Date departureTime;
    @Column(name = "arrival_time")
    private Date arrivalTime;
    @Column(name = "departure_date")
    private Date departureDate;
    @Column(name = "arrival_date")
    private Date arrivalDate;

    public Run() {}

    public Run(String name, Route route, Train train, Date departureTime, Date arrivalTime, Date departureDate, Date arrivalDate) {
        this.name = name;
        this.route = route;
        this.train = train;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        takenSeats = 0;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Long getId() {
        return id;
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

    public Date getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getDepartureDate() {
        return departureDate;
    }
    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }
    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void incrementTakenSeats() {takenSeats++;}
    public void decrementTakenSeats() {takenSeats--;}

    public int getTakenSeats() {
        return takenSeats;
    }
    public void setTakenSeats(int takenSeats) {
        this.takenSeats = takenSeats;
    }

    public String getDepartureDateToString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM 'at' HH:mm");
        return sdf.format(new Date(departureTime.getTime() + departureDate.getTime() + 3*3600*1000));
    }

    public String getArrivalDateToString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM 'at' HH:mm");
        return sdf.format(new Date(arrivalTime.getTime() + arrivalDate.getTime() + 3*3600*1000));
    }

    public int getFreeSeats() {
        return train.getCapacity() - takenSeats;
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
