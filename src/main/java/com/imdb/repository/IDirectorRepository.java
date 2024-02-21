package com.imdb.repository;

import com.imdb.model.Director;
import java.util.List;

public interface IDirectorRepository {
    void create(Director director);
    Director update(Director director);
    void delete(int directorId);
    List<Director> getAll();
    Director readById(int id);
    Director readByName(String name);
}