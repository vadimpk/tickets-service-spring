package com.naukma.ticketsservice.runs;

import com.naukma.ticketsservice.CRUDOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class RunRepositoryImpl implements RunRepository {
    @Override
    public void add(Run run) {

    }

    @Override
    public Run read(UUID id) {
        return null;
    }

    @Override
    public List<Run> readAll() {
        return null;
    }

    @Override
    public Run update(UUID id, Run run) {
        return null;
    }

    @Override
    public Run delete(UUID id) {
        return null;
    }
}
