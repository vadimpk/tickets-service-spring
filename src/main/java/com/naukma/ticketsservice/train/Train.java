package com.naukma.ticketsservice.train;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column
    private int speed;

    @Column
    private int capacity;

    public Train() {

    }

    public Train(String name, int speed, int capacity) {
        this.speed = speed;
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public Long getId() {
        return id;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Train train = (Train) o;

        return id.equals(train.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
