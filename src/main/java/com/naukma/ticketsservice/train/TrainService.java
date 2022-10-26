package com.naukma.ticketsservice.train;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TrainService {

    Train createTrain(Train train);

    Optional<Train> findTrain(Long id);
    Optional<Train> findTrainByName(String name);

    List<Train> getTrains();

    int update(Long id, Train train);

    boolean delete(Long id);

    boolean addWagon(Long id, String wagonName);

    boolean deleteWagon(Long id, String wagonName);

    Optional<Wagon> findWagonInTrain(Long id, String wagonName);
}
