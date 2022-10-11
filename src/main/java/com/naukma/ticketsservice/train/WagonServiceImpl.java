package com.naukma.ticketsservice.train;

import com.naukma.ticketsservice.route.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class WagonServiceImpl implements WagonService{

    private final WagonRepository repository;

    public WagonServiceImpl(@Autowired WagonRepository repository) {
        this.repository = repository;
    }


    @Override
    public Wagon createWagon(int numberOfSeats) {
        return null;
    }

    @Override
    public List<Wagon> findWagon(int numberOfSeats) {
        return null;
    }

    @Override
    public List<Wagon> getWagons() {
        return null;
    }

    @Override
    public Wagon update(UUID id, Wagon wagon) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
