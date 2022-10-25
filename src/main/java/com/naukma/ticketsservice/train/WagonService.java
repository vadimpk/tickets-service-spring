package com.naukma.ticketsservice.train;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WagonService {

    Wagon createWagon(Wagon wagon);

    Optional<Wagon> findWagon(String name);

    List<Wagon> getWagons();

    Wagon update(UUID id, Wagon wagon);

    void delete(UUID id);

}
