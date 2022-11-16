package com.naukma.ticketsservice.train;

import com.naukma.ticketsservice.wagon.Wagon;

import java.util.List;
import java.util.Optional;

public interface TrainService {

    Train createTrain(Train train);
    Train save(Train train);

    Optional<Train> findTrain(Long id);
    Optional<Train> findTrainByName(String name);

    List<Train> getTrains();

    int update(Long id, Train train);

    void delete(Long id);

    boolean addWagon(Long id, Long wagonID);

    boolean deleteWagon(Long id, Long wagonID);

    Optional<Wagon> findWagonInTrain(Long id, String wagonName);
}
