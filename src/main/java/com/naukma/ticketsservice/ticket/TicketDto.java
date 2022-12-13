package com.naukma.ticketsservice.ticket;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Pattern;

public class TicketDto {

    private Long id;
    private Long runId;

    @Pattern(regexp = "[a-zA-z]{3}", message = "currency code must contains only 3 letters (example: 'USD')")
    private String currency;

    public double price;

    // private User user;


    public TicketDto(@JsonProperty(value = "run_id", required = true) Long runId,
                     @JsonProperty(value = "currency") String currency) {
        this.runId = runId;
        if (currency == null || currency.equals("")) this.currency = "USD";
        else this.currency = currency;
    }

    public TicketDto() {
    }

    public Long getId() {
        return id;
    }

    public Long getRunId() {
        return runId;
    }

    public void setRunId(Long run) {
        this.runId = run;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
