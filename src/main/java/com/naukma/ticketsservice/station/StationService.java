package com.naukma.ticketsservice.station;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StationService {

    void createStation(Station station);

    Optional<Station> findById(Long id);

    List<Station> findAllById(List<Long> id);

    List<Station> getStations();

    Station update(Long id, Station station);

    void delete(Long id);

    void addAdjacentStation(UUID id, UUID adjacentID, int distance);
}
