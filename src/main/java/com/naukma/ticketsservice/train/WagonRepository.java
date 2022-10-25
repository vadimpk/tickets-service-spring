package com.naukma.ticketsservice.train;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface WagonRepository extends JpaRepository<Wagon, UUID> {

    @Modifying
    @Query("update Wagon w set w.numberOfSeats = ?3, w.name=?2  where w.id = ?1 ")
    void setWagonById(UUID id, String name, int numberOfSeats);

    Optional<Wagon> findByName(String name);

    @Modifying
    @Query("delete from Wagon w where w.name=?1")
    int deleteByName(String name);
}