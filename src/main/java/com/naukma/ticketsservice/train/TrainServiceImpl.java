package com.naukma.ticketsservice.train;


import com.naukma.ticketsservice.run.Run;
import com.naukma.ticketsservice.run.RunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class TrainServiceImpl implements TrainService{

    private final TrainRepository repository;
    private final RunService runService;

    @Autowired
    public TrainServiceImpl(TrainRepository repository, RunService runService) {
        this.repository = repository;
        this.runService = runService;
    }

    @Override
    public Train create(Train train) {

        Optional<Train> checkName = find(train.getName());
        if (checkName.isPresent()) return null;

        return repository.save(train);
    }

    @Override
    public Optional<Train> find(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Train> find(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Train> getTrains() {
        return repository.findAll();
    }

    @Override
    public Train update(Long id, TrainDto dto) {

        // check if exists
        Optional<Train> check = find(id);
        if (check.isEmpty())
            return null;

        // update name
        Train train = update(check.get(), dto.getName());
        if (train == null) return null;

        //update capacity
        train = update(train, dto.getCapacity());
        if (train == null) return null;

        // update speed
        train.setSpeed(dto.getSpeed());

        return repository.save(train);
    }

    private Train update(Train train, String name) {
        Optional<Train> checkName = find(name);
        if (checkName.isPresent()) return null;

        train.setName(name);
        return train;
    }

    private Train update(Train train, int capacity) {

        List<Run> runs = runService.getRuns();
        for (Run run : runs) {
            if (Objects.equals(run.getTrain().getId(), train.getId()))
                if (run.getTakenSeats() > capacity) {
                    return null;
                }
        }

        train.setCapacity(capacity);
        return train;
    }

    @Override
    public boolean delete(Long id) {

        List<Run> runs = runService.getRuns();
        for (Run run : runs) {
            if (Objects.equals(run.getTrain().getId(), id))
                return false;
        }
        repository.deleteById(id);
        return true;
    }

}
