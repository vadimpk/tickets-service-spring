package com.naukma.ticketsservice.train;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public class WagonRepositoryImpl implements WagonRepository {
    @Override
    public void add(Wagon wagon) {

    }

    @Override
    public Wagon read(UUID id) {
        return null;
    }

    @Override
    public List<Wagon> readAll() {
        return null;
    }

    @Override
    public Wagon update(UUID id, Wagon wagon) {
        return null;
    }

    @Override
    public Wagon delete(UUID id) {
        return null;
    }
}
