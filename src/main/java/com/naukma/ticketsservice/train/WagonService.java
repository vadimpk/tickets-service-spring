package com.naukma.ticketsservice.train;

import java.util.List;
import java.util.Optional;

public interface WagonService {

    Wagon createWagon(Wagon wagon);

    Optional<Wagon> findWagon(String name);

    List<Wagon> getWagons();

    int update(String name, Wagon wagon);

    void delete(long id);

}
