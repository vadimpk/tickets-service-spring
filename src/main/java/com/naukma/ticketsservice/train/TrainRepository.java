package com.naukma.ticketsservice.train;

import com.naukma.ticketsservice.CRUDOperations;

import java.util.UUID;

public interface TrainRepository extends CRUDOperations<Train> {

    void addWagon(UUID id, UUID wagonID);
    void deleteWagon(UUID id, UUID wagonID);

}
