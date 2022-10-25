package com.naukma.ticketsservice.train;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
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
    public Wagon findWagon(String name) {
        Optional<Wagon> wagon = repository.findByName(name);
        if (wagon.isEmpty()) throw new RuntimeException("not such wagon with name " + name);
        return wagon.get();
    }

    @Override
    public List<Wagon> getWagons() {
        return repository.findAll();
    }

    @Override
    public Wagon update(UUID id, Wagon wagon) {
        repository.setWagonById(id, wagon.getName(), wagon.getNumberOfSeats());
        return wagon;
    }

    @Override
    public int delete(String name) {
        return repository.deleteByName(name);
    }
}
