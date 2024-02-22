package com.imdb.repository;

import java.util.List;


public interface CRUDRepository<T> {
    void create(T entry);

    T update(T entry, T entry2);

    void delete(T entry);

    List<T> getAll();

    T readById(T entry);

    List<T> readByName(T entry);

}
