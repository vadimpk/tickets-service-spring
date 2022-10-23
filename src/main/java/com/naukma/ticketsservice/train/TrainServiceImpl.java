package com.naukma.ticketsservice.train;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TrainServiceImpl implements TrainService{

    private final TrainRepository repository;

    public TrainServiceImpl(@Autowired TrainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createTrain(Train train) {
        repository.save(train);
    }

    @Override
    public Train findTrain(UUID id) {
        Optional<Train> train = repository.findById(id);
        if (train.isEmpty()) throw new RuntimeException("not such train with id " + id);
        return train.get();
    }

    @Override
    public List<Train> getTrains() {
        return repository.findAll();
    }

    @Override
    public Train update(UUID id, Train train) {
        repository.setTrainById(id, train.getWagons(), train.getSpeed(), train.getRuns());
        return train;
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void addWagon(UUID id, UUID wagonID) {
        repository.saveWagonToTrain(id, wagonID);
    }

    @Override
    public void deleteWagon(UUID id, UUID wagonID) {
        repository.deleteWagonFromTrain(id, wagonID);
    }
}
