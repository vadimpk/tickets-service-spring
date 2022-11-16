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
//
//        Station station1 = new Station("A");
//        Station station2 = new Station("B");
//        Station station3 = new Station("C");
//        Station station4 = new Station("D");
//        Station station5 = new Station("E");
//        Station station6 = new Station("F");
//        Station station7 = new Station("G");
//
//        stationRepository.save(station1);
//        stationRepository.save(station2);
//        stationRepository.save(station3);
//        stationRepository.save(station4);
//        stationRepository.save(station5);
//        stationRepository.save(station6);
//        stationRepository.save(station7);
//
//        station1.addAdjacentStation(station2, 50);
//        station1.addAdjacentStation(station3, 40);
//
//        station2.addAdjacentStation(station4, 30);
//
//        station3.addAdjacentStation(station4, 30);
//        station3.addAdjacentStation(station5, 30);
//        station3.addAdjacentStation(station6, 25);
//
//        station4.addAdjacentStation(station7, 20);
//
//        station6.addAdjacentStation(station7, 25);
//
//        stationRepository.save(station1);
//        stationRepository.save(station2);
//        stationRepository.save(station3);
//        stationRepository.save(station4);
//        stationRepository.save(station5);
//        stationRepository.save(station6);
//        stationRepository.save(station7);
//
//        log.info(String.valueOf(stationRepository.findAll()));
    }
}
