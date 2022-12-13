package com.naukma.ticketsservice.run;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SearchDto {


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;
    private Long startStationId;
    private Long finishStationId;

    public SearchDto() {

    }

    public Long getStartStationId() {
        return startStationId;
    }

    public void setStartStationId(Long startStationId) {
        this.startStationId = startStationId;
    }

    public Long getFinishStationId() {
        return finishStationId;
    }

    public void setFinishStationId(Long finishStationId) {
        this.finishStationId = finishStationId;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }
}
