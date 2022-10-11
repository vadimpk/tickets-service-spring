package com.naukma.ticketsservice.train;

import java.util.List;
import java.util.UUID;

public interface TrainService {

    void createTrain(Train train);

    Train findTrain(UUID id);

    List<Train> getTrains();

    Train update(UUID id, Train train);

    void delete(UUID id);

    void addWagon(UUID id, UUID wagonID);

    void deleteWagon(UUID id, UUID wagonID);
}
