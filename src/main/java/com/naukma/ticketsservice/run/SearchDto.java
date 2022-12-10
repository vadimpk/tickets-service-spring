package com.naukma.ticketsservice.run;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SearchDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;

    public SearchDto() {

    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }
}
