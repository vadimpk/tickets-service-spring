package com.naukma.ticketsservice.train;

import com.naukma.ticketsservice.route.RouteService;
import com.naukma.ticketsservice.run.Run;
import com.naukma.ticketsservice.run.RunService;
import com.naukma.ticketsservice.station.StationService;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.LinkedList;

@Component
public class Populate {

    private final TrainService trainService;
    private final RunService runService;
    private final RouteService routeService;
    private final StationService stationService;


    public Populate(TrainService trainService, RunService runService, RouteService routeService, StationService stationService) {
        this.trainService = trainService;
        this.runService = runService;
        this.routeService = routeService;
        this.stationService = stationService;

        populate();
    }

    private void populate() {
        Train[] trains = new Train[] {
                new Train("train1", 100),
                new Train("train2", 100),
                new Train("train3", 100),
                new Train("train4", 100),
                new Train("train5", 100),
                new Train("train6", 100)
        };

//        Station station = new Station("station");
//        Station station1 = new Station("station1");
//        Station station2 = new Station("station2");
//        station.addAdjacentStation(station1, 100);
//        station1.addAdjacentStation(station2, 100);
//        station2.addAdjacentStation(station, 100);
//        stationService.createStation(station);
//        stationService.createStation(station1);
//        stationService.createStation(station2);
//
//
//        routeService.createRoute(stationService.getStations().stream().mapToLong(Station::getId).boxed().toList());
//        Route route = routeService.getRoutes().get(0);

        Run[] runs = new Run[]{
                new Run("run1", null, trains[0], Time.valueOf("12:00:00"), Time.valueOf("12:00:00")),
                new Run("run2", null, trains[1], Time.valueOf("12:00:00"), Time.valueOf("12:00:00")),
                new Run("run3", null, trains[2], Time.valueOf("12:00:00"), Time.valueOf("12:00:00")),
                new Run("run4", null, trains[3], Time.valueOf("12:00:00"), Time.valueOf("12:00:00")),
                new Run("run5", null, trains[4], Time.valueOf("12:00:00"), Time.valueOf("12:00:00")),
                new Run("run6", null, trains[5], Time.valueOf("12:00:00"), Time.valueOf("12:00:00"))
        };

        for (Train train : trains) {
            trainService.createTrain(train);
        }
        for (Run run : runs) {
            runService.createRun(run);
        }

        for (int i = 1; i < trains.length; i++) {
            trains[i].addRun(runs[i]);
            trains[i].addRun(runs[0]);
            trainService.update(trains[i].getId(), trains[i]);
        }



    }
}
