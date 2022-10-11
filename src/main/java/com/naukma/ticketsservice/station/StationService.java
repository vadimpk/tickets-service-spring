package com.naukma.ticketsservice.station;

import java.util.List;
import java.util.UUID;

public interface StationService {

    void createStation(Station station);

    Station findStation(UUID id);

    List<Station> getStations();

    Station update(UUID id, Station station);

    void delete(UUID id);

    void addAdjacentStation(UUID id, UUID adjacentID, int distance);
}
