package com.naukma.ticketsservice.train;

import java.util.List;
import java.util.UUID;

public interface WagonService {

    void createWagon(Wagon wagon);

    Wagon findWagon(String name);

    List<Wagon> getWagons();

    Wagon update(UUID id, Wagon wagon);

    int delete(String name);

}
