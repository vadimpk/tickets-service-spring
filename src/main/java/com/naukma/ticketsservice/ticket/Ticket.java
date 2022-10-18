package com.naukma.ticketsservice.ticket;

import com.naukma.ticketsservice.runs.Run;

import java.util.UUID;

public class Ticket {

    private final UUID id;
    private Run run;
    private int price;

    // private User user;


    public Ticket(UUID id, Run run, int price) {
        this.id = id;
        this.run = run;
        this.price = price;
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
}
