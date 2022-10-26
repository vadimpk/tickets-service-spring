package com.naukma.ticketsservice.train;

import com.naukma.ticketsservice.TicketsServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WagonServiceImpl implements WagonService{

    private final WagonRepository repository;

    static final Logger log =
            LoggerFactory.getLogger(TicketsServiceApplication.class);

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
    public int update(String name, Wagon wagon) {
        return repository.setNumberOfSeatsAndNameByName(name, wagon.getName(), wagon.getNumberOfSeats());
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
