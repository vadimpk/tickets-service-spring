package com.naukma.ticketsservice.route;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class RouteRepositoryImpl implements RouteRepository {
    @Override
    public void add(Route route) {

    }

    @Override
    public Route read(UUID id) {
        return null;
    }

    @Override
    public List<Route> readAll() {
        return null;
    }

    @Override
    public Route update(UUID id, Route route) {
        return null;
    }

    @Override
    public Route delete(UUID id) {
        return null;
    }
}
