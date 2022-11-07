package com.naukma.ticketsservice.train;

import com.naukma.ticketsservice.run.Run;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TrainRepository extends JpaRepository<Train, Long> {

    Optional<Train> findByName(String name);

    @Transactional
    @Modifying
    @Query("update Train t set t.wagons=?2 where t.id=?1")
    void setWagonsById(long id, Collection<Wagon> wagons);

    @Transactional
    @Modifying
    @Query("update Train t set t.runs=?2 where t.id=?1")
    void setRunsById(long id, List<Run> runs);

    @Transactional
    @Modifying
    @Query("update Train t set t.name = ?2, t.speed = ?3 where t.id = ?1")
    int updateById(Long id, String name, int speed);

    @Query("select t from Train t where t.id=?1 and ?2 in t.wagons" )
    Optional<Train> findTrainWithWagon(Long id, Wagon wagon);
}
