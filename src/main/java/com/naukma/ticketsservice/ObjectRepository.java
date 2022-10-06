package com.naukma.ticketsservice;

import java.util.UUID;

public interface ObjectRepository<T> {

    void add(T t);

    T read();

    T update(UUID id, T t);

    T delete(UUID id);
}