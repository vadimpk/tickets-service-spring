package com.naukma.ticketsservice.train;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class WagonServiceImpl implements WagonService{

    private final WagonRepository repository;

    public WagonServiceImpl(@Autowired WagonRepository repository) {
        this.repository = repository;
    }


    @Override
    public void createWagon(Wagon wagon) {
        repository.add(wagon);
    }

    @Override
    public Wagon findWagon(UUID id) {
        return repository.read(id);
    }

    @Override
    public List<Wagon> getWagons() {
        return repository.readAll();
    }

    @Override
    public Wagon update(UUID id, Wagon wagon) {
        return repository.update(id, wagon);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }
}
