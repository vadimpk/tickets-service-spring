package com.naukma.ticketsservice.ticket;

import com.naukma.ticketsservice.runs.Run;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class Ticket {

    @Id
    private UUID id;

    @ManyToOne
    private Run run;

    private int price;

    // private User user;


    public Ticket(UUID id, Run run, int price) {
        this.id = id;
        this.run = run;
        this.price = price;
    }

    public Ticket() {

    }

    public UUID getId() {
        return id;
    }

    public Run getRun() {
        return run;
    }

    public void setRun(Run run) {
        this.run = run;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        return id.equals(ticket.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
