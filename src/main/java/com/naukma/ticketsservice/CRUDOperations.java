package com.naukma.ticketsservice;

import java.util.List;
import java.util.UUID;

public interface CRUDOperations<T> {

    void add(T t);

    T read(UUID id);

    List<T> readAll();

    T update(UUID id, T t);

    T delete(UUID id);
}