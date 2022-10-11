package com.naukma.ticketsservice.train;

import com.naukma.ticketsservice.runs.Run;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class TrainServiceImpl implements TrainService{

    private final TrainRepository repository;

    public TrainServiceImpl(@Autowired TrainRepository repository) {
        this.repository = repository;
    }

    @Override
    public Train createTrain(List<Wagon> wagons, int speed, List<Run> runs) {
        return null;
    }

    @Override
    public Train findTrain(UUID id) {
        return null;
    }

    @Override
    public List<Train> getTrains() {
        return null;
    }

    @Override
    public Train update(UUID id, Train train) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void addWagon(UUID id, Wagon wagon) {

    }

    @Override
    public void deleteWagon(UUID id, Wagon wagon) {

    }
}
