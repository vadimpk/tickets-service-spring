package com.naukma.ticketsservice.train;

import com.naukma.ticketsservice.ObjectRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class WagonRepository implements ObjectRepository<Wagon> {
    @Override
    public void add(Wagon wagon) {

    }

    @Override
    public Wagon read() {
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
