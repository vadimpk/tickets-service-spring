package com.naukma.ticketsservice.station;

import java.util.List;
import java.util.UUID;

public interface StationService {

    Station createStation(UUID id);

    Station findStation(UUID id);

    List<Station> getStations();

    Station update(UUID id, Station station);

    void delete(UUID id);

    void addAdjacentStation(Station station, Station adjacentStation, int distance);
}
