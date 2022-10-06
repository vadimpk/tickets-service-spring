package com.naukma.ticketsservice.station;

import com.naukma.ticketsservice.CRUDOperations;

import java.util.UUID;

public interface StationRepository extends CRUDOperations<Station> {

    void addAdjacentStation(UUID id, UUID adjacentID, int distance);
}
