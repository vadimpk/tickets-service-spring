package com.naukma.ticketsservice.train;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class TrainRepositoryImpl implements TrainRepository {

    @Override
    public void add(Train train) {

    }

    @Override
    public Train read(UUID id) {
        return null;
    }

    @Override
    public List<Train> readAll() {
        return null;
    }

    @Override
    public Train update(UUID id, Train train) {
        return null;
    }

    @Override
    public Train delete(UUID id) {
        return null;
    }

    @Override
    public void addWagon(UUID id, UUID wagonID) {

    }

    @Override
    public void deleteWagon(UUID id, UUID wagonID) {

    }
}
