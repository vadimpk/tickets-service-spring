package com.naukma.ticketsservice;

import com.naukma.ticketsservice.route.Route;
import com.naukma.ticketsservice.route.RouteService;
import com.naukma.ticketsservice.run.Run;
import com.naukma.ticketsservice.run.RunService;
import com.naukma.ticketsservice.station.Station;
import com.naukma.ticketsservice.station.StationRepository;
import com.naukma.ticketsservice.station.StationService;
import com.naukma.ticketsservice.train.Train;
import com.naukma.ticketsservice.train.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Component
public class PopulateDB {

    private final TrainService trainService;
    private final RunService runService;
    private final RouteService routeService;
    private final StationService stationService;

    @Autowired
    public PopulateDB(TrainService trainService, RunService runService, RouteService routeService, StationService stationService) {
        this.trainService = trainService;
        this.runService = runService;
        this.routeService = routeService;
        this.stationService = stationService;

        populate();
    }

    private void populate() {

        /*
        POPULATE STATIONS
         */

        Station station1 = stationService.createStation(new Station("A"));
        Station station2 = stationService.createStation(new Station("B"));
        Station station3 = stationService.createStation(new Station("C"));
        Station station4 = stationService.createStation(new Station("D"));
        Station station5 = stationService.createStation(new Station("E"));
        Station station6 = stationService.createStation(new Station("F"));
        Station station7 = stationService.createStation(new Station("G"));

        stationService.addAdjacentStation(station1, station2, 50);
        stationService.addAdjacentStation(station1, station3, 40);
        stationService.addAdjacentStation(station2, station4, 30);
        stationService.addAdjacentStation(station3, station4, 30);
        stationService.addAdjacentStation(station3, station5, 30);
        stationService.addAdjacentStation(station3, station6, 25);
        stationService.addAdjacentStation(station4, station7, 20);
        stationService.addAdjacentStation(station6, station7, 25);

        /*
        POPULATE ROUTES
         */

        List<Long> stationList1 = new ArrayList<>();
        stationList1.add(station1.getId());
        stationList1.add(station2.getId());
        stationList1.add(station4.getId());
        stationList1.add(station7.getId());

        List<Long> stationList2 = new ArrayList<>();
        stationList2.add(station1.getId());
        stationList2.add(station3.getId());
        stationList2.add(station6.getId());
        stationList2.add(station7.getId());


        List<Long> stationList3 = new ArrayList<>();
        stationList3.add(station1.getId());
        stationList3.add(station3.getId());
        stationList3.add(station5.getId());

        List<Long> stationList4 = new ArrayList<>();
        stationList4.add(station7.getId());
        stationList4.add(station4.getId());
        stationList4.add(station2.getId());
        stationList4.add(station1.getId());

        Route route1 = routeService.createRoute(stationList1);
        Route route2 = routeService.createRoute(stationList2);
        Route route3 = routeService.createRoute(stationList3);
        Route route4 = routeService.createRoute(stationList4);

        /*
        POPULATE TRAINS
         */

        Train train1 = trainService.createTrain(new Train("TH1", 100));
        Train train2 = trainService.createTrain(new Train("TH2", 120));
        Train train3 = trainService.createTrain(new Train("TH3", 130));
        Train train4 = trainService.createTrain(new Train("TH4", 200));

        /*
        POPULATE RUNS
         */
        Run run1 = runService.createRun(new Run("RTZ1", route1, train1, Time.valueOf("12:00:00"), Time.valueOf("14:00:00")));
        Run run2 = runService.createRun(new Run("RTZ2", route2, train3, Time.valueOf("13:30:00"), Time.valueOf("16:00:00")));
        Run run3 = runService.createRun(new Run("RTZ3", route3, train4, Time.valueOf("14:00:00"), Time.valueOf("18:00:00")));
        Run run4 = runService.createRun(new Run("RTZ4", route4, train2, Time.valueOf("15:20:00"), Time.valueOf("20:00:00")));
        Run run5 = runService.createRun(new Run("RTZ5", route2, train1, Time.valueOf("18:00:00"), Time.valueOf("21:00:00")));
        Run run6 = runService.createRun(new Run("RTZ6", route3, train2, Time.valueOf("19:00:00"), Time.valueOf("23:00:00")));
    }


}
