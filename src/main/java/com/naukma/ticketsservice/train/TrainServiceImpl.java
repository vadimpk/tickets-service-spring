package com.naukma.ticketsservice.train;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TrainServiceImpl implements TrainService{

    private final TrainRepository repository;
    private final WagonRepository wagonRepository;

    public TrainServiceImpl(@Autowired TrainRepository repository, @Autowired WagonRepository wagonRepository) {
        this.repository = repository;
        this.wagonRepository = wagonRepository;
    }

    @Override
    public boolean createTrain(Train train) {
        repository.saveAndFlush(train);
        return true;
    }

    @Override
    public Optional<Train> findTrain(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Train> getTrains() {
        return repository.findAll();
    }

    @Override
    public int update(Long id, Train train) {
        return repository.setSpeedById(id, train.getSpeed());
    }

    @Override
    public boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean addWagon(Long id, String wagonName) {
        Optional<Wagon> wagon = wagonRepository.findByName(wagonName);
        if (wagon.isPresent()) {
            Optional<Train> train = findTrain(id);
            if (train.isPresent()) {
                train.get().addWagon(wagon.get());
                repository.setWagonsById(id, train.get().getWagons());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteWagon(Long id, String wagonName) {
        Optional<Wagon> wagon = wagonRepository.findByName(wagonName);
        if (wagon.isPresent()) {
            Optional<Train> train = findTrain(id);
            if (train.isPresent()) {
                train.get().deleteWagon(wagon.get());
                repository.setWagonsById(id, train.get().getWagons());
                return true;
            }

        }
        return false;
    }

    @Override
    public Optional<Wagon> findWagonInTrain(Long trainId, String wagonName) {
        Optional<Wagon> wagon = wagonRepository.findByName(wagonName);
        if (wagon.isEmpty()) return Optional.empty();
        if (repository.findTrainWithWagon(trainId, wagon.get()).isPresent()) return wagon;
        return Optional.empty();
    }
}
