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
    public Train update(Train train) {
        return repository.save(train);
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
