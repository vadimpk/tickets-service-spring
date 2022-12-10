package com.naukma.ticketsservice.station;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface StationService {

    Station createStation(Station station);

    Optional<Station> findById(Long id);
    Optional<Station> findByName(String name);

    List<Station> findAllById(List<Long> id);

    List<Station> getStations();

    Map<Long, Station> getStationsMap();

    Station update(Station station);

    boolean delete(Long id);

    void addAdjacentStation(Station station, Station adjStation, int distance);
    void removeAdjacentStation(Station station, Station stationToRemove);
}
