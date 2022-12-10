package com.naukma.ticketsservice.train;

import com.naukma.ticketsservice.wagon.Wagon;
import com.naukma.ticketsservice.wagon.WagonRepository;
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
    public Train save(Train train) {
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
    public int update(Long id, Train train) {
        repository.setRunsById(id, train.getRuns());
        repository.setWagonsById(id, train.getWagons());
        return repository.updateById(id, train.getName(), train.getSpeed());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean addWagon(Long id, Long wagonID) {
        Optional<Wagon> wagon = wagonRepository.findById(wagonID);
        Optional<Train> train = find(id);
        if (wagon.isPresent() && train.isPresent()) {
            wagon.get().setTrain(train.get());
            wagonRepository.save(wagon.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteWagon(Long id, Long wagonID) {
        Optional<Wagon> wagon = wagonRepository.findById(wagonID);
        Optional<Train> train = find(id);
        if (wagon.isPresent() && train.isPresent()) {
            wagon.get().setTrain(null);
            wagonRepository.save(wagon.get());
            return true;
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
