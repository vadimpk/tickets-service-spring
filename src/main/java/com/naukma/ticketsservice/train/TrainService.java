package com.naukma.ticketsservice.train;

import java.util.List;
import java.util.Optional;

public interface TrainService {
    Train create(Train train);

    Optional<Train> find(Long id);

    Optional<Train> find(String name);

    List<Train> getTrains();

    Train update(Train train);

    boolean delete(Long id);
}
