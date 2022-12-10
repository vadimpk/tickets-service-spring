package com.naukma.ticketsservice.station;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;
import java.util.Optional;

public interface StationRepository extends JpaRepository<Station, Long> {

    Optional<Station> findByName(String name);
}
