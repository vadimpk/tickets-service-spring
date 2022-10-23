package com.naukma.ticketsservice.train;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Wagon {

    @Id
    private UUID id;

    @Column(name = "number_of_sits")
    private int numberOfSits;

    public Wagon(UUID id, int numberOfSits){
        this.id = id;
        this.numberOfSits=numberOfSits;
    }

    public Wagon() {

    }

    public int getNumberOfSits() {
        return numberOfSits;
    }

    public void setNumberOfSits(int numberOfSits) {
        this.numberOfSits = numberOfSits;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wagon wagon = (Wagon) o;

        if (numberOfSits != wagon.numberOfSits) return false;
        return id.equals(wagon.id);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + numberOfSits;
        return result;
    }
}
