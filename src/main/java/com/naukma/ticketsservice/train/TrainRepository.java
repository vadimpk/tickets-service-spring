package com.naukma.ticketsservice.train;

import com.naukma.ticketsservice.runs.Run;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TrainRepository extends JpaRepository<Train, UUID> {
    // TODO: 23.10.2022
//    @Query("")
//    void saveWagonToTrain(UUID id, UUID wagonID);
//
//    @Query("")
//    void deleteWagonFromTrain(UUID id, UUID wagonID);

    @Modifying
    @Query("update Train t set t.wagons = ?2, t.speed = ?3, t.runs = ?4  where t.id = ?1 ")
    void setTrainById(UUID id, List<Wagon> wagons, int speed, List<Run> runs);

}
