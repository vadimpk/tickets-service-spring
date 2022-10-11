package com.naukma.ticketsservice.train;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class TrainServiceImpl implements TrainService{

    private final TrainRepository repository;

    public TrainServiceImpl(@Autowired TrainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createTrain(Train train) {
        repository.add(train);
    }

    @Override
    public Train findTrain(UUID id) {
        return repository.read(id);
    }

    @Override
    public List<Train> getTrains() {
        return repository.readAll();
    }

    @Override
    public Train update(UUID id, Train train) {
        return repository.update(id, train);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }

    @Override
    public void addWagon(UUID id, UUID wagonID) {
        repository.addWagon(id, wagonID);
    }

    @Override
    public void deleteWagon(UUID id, UUID wagonID) {
        repository.deleteWagon(id, wagonID);
    }
}
