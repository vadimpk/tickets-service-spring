package com.naukma.ticketsservice.station;

import com.naukma.ticketsservice.CRUDOperations;
import com.naukma.ticketsservice.runs.Run;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.UUID;

public interface StationRepository extends JpaRepository<Run, UUID> {

    //todo
    @Query("")
    void addAdjacentStation(UUID id, UUID adjacentID, int distance);
}
