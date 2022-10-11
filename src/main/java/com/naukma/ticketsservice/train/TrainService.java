package com.naukma.ticketsservice.train;

import com.naukma.ticketsservice.runs.Run;

import java.util.List;
import java.util.UUID;

public interface TrainService {

    Train createTrain(List<Wagon> wagons, int speed, List<Run> runs);

    Train findTrain(UUID id);

    List<Train> getTrains();

    Train update(UUID id, Train train);

    void delete(UUID id);

    void addWagon(UUID id, Wagon wagon);

    void deleteWagon(UUID id, Wagon wagon);
}
