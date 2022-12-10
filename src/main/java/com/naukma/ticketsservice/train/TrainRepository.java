package com.naukma.ticketsservice.train;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface TrainRepository extends JpaRepository<Train, Long> {

    Optional<Train> findByName(String name);

}
