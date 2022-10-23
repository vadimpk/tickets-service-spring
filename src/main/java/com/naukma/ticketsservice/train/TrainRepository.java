package com.naukma.ticketsservice.train;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrainRepository extends JpaRepository<Train, UUID> {
    // TODO: 23.10.2022  
//    void addWagon(UUID id, UUID wagonID);
//    void deleteWagon(UUID id, UUID wagonID);

}
