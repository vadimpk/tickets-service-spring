package com.naukma.ticketsservice.ticket;

import com.naukma.ticketsservice.run.Run;

import javax.persistence.*;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne()
//    @JoinColumn(name = "run_id", referencedColumnName = "id")
    private Run run;

    @Column(nullable = false)
    private double price;

    private String currency;

    // private User user;

    public Ticket() {

    }

    public Ticket(Run run, String currency, double price) {
        this.run = run;
        this.currency = currency;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public Run getRun() {
        return run;
    }

    public void setRun(Run run) {
        this.run = run;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
