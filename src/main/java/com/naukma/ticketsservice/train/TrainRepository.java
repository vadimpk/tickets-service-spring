package com.naukma.ticketsservice.train;

import com.naukma.ticketsservice.ObjectRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class TrainRepository implements ObjectRepository<Train> {
    @Override
    public void add(Train train) {

    }

    @Override
    public Train read() {
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

    public void addWagon(Train train, Wagon wagon) {

    }

    public void deleteWagon(Train train, Wagon wagon) {

    }
}
