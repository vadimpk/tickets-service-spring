package com.naukma.ticketsservice.train;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class WagonServiceImpl implements WagonService{

    private final WagonRepository repository;

    public WagonServiceImpl(@Autowired WagonRepository repository) {
        this.repository = repository;
    }


    @Override
    public void createWagon(Wagon wagon) {
        repository.saveAndFlush(wagon);
    }

    @Override
    public Wagon findWagon(UUID id) {
        Optional<Wagon> wagon = repository.findById(id);
        if (wagon.isEmpty()) throw new RuntimeException("not such wagon with id " + id);
        return wagon.get();
    }

    @Override
    public List<Wagon> getWagons() {
        return repository.findAll();
    }

    @Override
    public Wagon update(UUID id, Wagon wagon) {
        repository.setWagonById(id, wagon.getNumberOfSits());
        return wagon;
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
