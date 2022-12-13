package com.naukma.ticketsservice.run;

import com.naukma.ticketsservice.route.Route;
import com.naukma.ticketsservice.train.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RunRepository extends JpaRepository<Run, Long> {

    Optional<Run> findByName(String name);
    @Transactional
    @Modifying
    @Query("update Run r set r.route=?2 where r.id=?1")
    void setRouteById(long id, Route route);

    @Transactional
    @Modifying
    @Query("update Run r set r.train=?2 where r.id=?1")
    void setTrainById(long id, Train train);

    List<Run> findAllByDepartureDate(Date departureDate);

    List<Run> findAllByRouteAndDepartureDate(Route route, Date departureDate);
}
