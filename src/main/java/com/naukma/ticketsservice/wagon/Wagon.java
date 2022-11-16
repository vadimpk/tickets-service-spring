package com.naukma.ticketsservice.wagon;

import com.naukma.ticketsservice.train.Train;

import javax.persistence.*;

@Entity
@Table(name = "wagons")
public class Wagon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private int numberOfSeats;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "train_id", referencedColumnName = "id")
    private Train train;

    public Wagon() {

    }

    public Wagon(String name, int numberOfSeats, Train train){
        this.name = name;
        this.numberOfSeats =numberOfSeats;
        this.train = train;
    }


    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSits) {
        this.numberOfSeats = numberOfSits;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public void setName(String name) {
        this.name = name;
    }

}
