package com.naukma.ticketsservice.station;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StationService {

    Station createStation(Station station);

    Optional<Station> findById(Long id);
    Optional<Station> findByName(String name);

    List<Station> findAllById(List<Long> id);

    List<Station> getStations();

    Station update(Long id, Station station);

    void delete(Long id);

    void addAdjacentStation(Station station, int distance);
}
