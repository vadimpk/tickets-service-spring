package com.naukma.ticketsservice.train;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface WagonRepository extends JpaRepository<Wagon, UUID> {

    @Modifying
    @Query("update Wagon w set w.numberOfSeats = ?2  where w.id = ?1 ")
    void setWagonById(UUID id, int numberOfSeats);
}
