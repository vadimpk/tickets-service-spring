package com.naukma.ticketsservice.train;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface WagonRepository extends JpaRepository<Wagon, Long> {
    Optional<Wagon> findByName(String name);
}