package com.naukma.ticketsservice.station;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;
import java.util.Optional;

public interface StationRepository extends JpaRepository<Station, Long> {

//    //todo
//    @Query("")
//    void addAdjacentStation(UUID id, UUID adjacentID, int distance);

    @Modifying
    @Query("update Station s set s.adjacentStations = ?2 where s.id = ?1")
    void setStationById(Long id, Map<Station, Integer> adjacentStations);

    Optional<Station> findByName(String name);
}
