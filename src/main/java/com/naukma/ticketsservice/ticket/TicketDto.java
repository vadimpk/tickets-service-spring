package com.naukma.ticketsservice.ticket;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TicketDto {

    private Long id;
    private Long runId;

    // private User user;


    public TicketDto(@JsonProperty(value = "run_id", required = true) Long runId) {
        this.runId = runId;
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

}
