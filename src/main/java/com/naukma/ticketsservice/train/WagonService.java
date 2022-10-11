package com.naukma.ticketsservice.train;

import java.util.List;
import java.util.UUID;

public interface WagonService {

    Wagon createWagon(int numberOfSeats);

    List<Wagon> findWagon(int numberOfSeats);

    List<Wagon> getWagons();

    Wagon update(UUID id, Wagon wagon);

    void delete(UUID id);
}
