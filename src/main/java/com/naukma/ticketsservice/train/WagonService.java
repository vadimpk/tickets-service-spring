package com.naukma.ticketsservice.train;

import java.util.List;
import java.util.Optional;

public interface WagonService {

    Wagon createWagon(Wagon wagon);
    Wagon save(Wagon wagon);

    Optional<Wagon> findWagonByName(String name);
    Optional<Wagon> findWagonById(Long id);

    List<Wagon> getWagons();

    int update(Long id, Wagon wagon);

    void delete(long id);

}
