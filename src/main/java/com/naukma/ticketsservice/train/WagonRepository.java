package com.naukma.ticketsservice.train;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WagonRepository extends JpaRepository<Wagon, UUID> {
}
