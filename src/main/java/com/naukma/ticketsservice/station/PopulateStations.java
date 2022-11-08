package com.naukma.ticketsservice.station;

import com.naukma.ticketsservice.TicketsServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PopulateStations {

    static final Logger log =
            LoggerFactory.getLogger(TicketsServiceApplication.class);

    private StationRepository stationRepository;

    @Autowired
    public PopulateStations(StationRepository stationRepository) {

        Station station1 = new Station("A");
        Station station2 = new Station("B");
        Station station3 = new Station("C");
        Station station4 = new Station("D");
//        Station station5 = new Station("E", 24L);
//        Station station6 = new Station("F", 25L);
//        Station station7 = new Station("G", 26L);

        stationRepository.save(station1);
        stationRepository.save(station2);
        stationRepository.save(station3);
        stationRepository.save(station4);
//        stationRepository.save(station5);
//        stationRepository.save(station6);
//        stationRepository.save(station7);

        station1.addAdjacentStation(station2, 40);
        station1.addAdjacentStation(station3, 50);

        station2.addAdjacentStation(station3, 100);

        station4.addAdjacentStation(station2, 100);

        stationRepository.save(station1);
        stationRepository.save(station2);
        stationRepository.save(station4);

        log.info(String.valueOf(stationRepository.findAll()));
    }
}
