package com.naukma.ticketsservice.train;

import java.util.List;
import java.util.Optional;

public interface TrainService {
    Train save(Train train);

    Optional<Train> find(Long id);

    Optional<Train> find(String name);

    List<Train> getTrains();

    int update(Long id, Train train);

    void delete(Long id);

    // boolean addWagon(Long id, Long wagonID);

    // boolean deleteWagon(Long id, Long wagonID);

    // Optional<Wagon> findWagonInTrain(Long id, String wagonName);
}
