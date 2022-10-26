package com.naukma.ticketsservice.train;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface WagonRepository extends JpaRepository<Wagon, Long> {
    Optional<Wagon> findByName(String name);

    @Transactional
    @Modifying
    @Query("update Wagon w set w.numberOfSeats = ?3, w.name = ?2 where w.name = ?1")
    int setNumberOfSeatsAndNameByName(String name, String newName, int numberOfSeats);
}