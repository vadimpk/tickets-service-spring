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
    public Wagon createWagon(Wagon wagon) {
        return repository.saveAndFlush(wagon);
    }

    @Override
    public Optional<Wagon> findWagon(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Wagon> getWagons() {
        return repository.findAll();
    }

    @Override
    public Wagon update(UUID id, Wagon wagon) {
        wagon.setId(id);
        return repository.save(wagon);
        //repository.updateWagonById(id, wagon.getName(), wagon.getNumberOfSeats());
        //return wagon;
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
