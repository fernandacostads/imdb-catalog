package com.imdb.repository;

import com.imdb.model.Director;
import com.imdb.model.Movie;

import java.util.ArrayList;
import java.util.List;


public class DirectorRepository {
    private final List<Director> directors;

    public DirectorRepository() {
        this.directors = new ArrayList<>();
    }

    public void addDirector(Director director) {
        directors.add(director);
    }

    public Director findDirectorById(int id) {
        for (Director director : directors) {
            if (director.getId() == id) {
                return director;
            }
        }
        return null;
    }

    public Director findDirectorByName(String name) {
        for (Director director : directors) {
            if (director.getName().equalsIgnoreCase(name)) {
                return director;
            }
        }
        return null;
    }

    public Director findDirectorByNationality(String nationality) {
        for (Director director : directors) {
            if (director.getNationality().equalsIgnoreCase(nationality)) {
                return director;
            }
        }
        return null;
    }

    public boolean updateDirector(Director updatedDirector) {
        for (Director director : directors) {
            if (director.getId() == updatedDirector.getId()) {
                // Atualiza os detalhes do diretor
                director.setName(updatedDirector.getName());
                director.setNationality(updatedDirector.getNationality());
                return true;
            }
        }
        return false;
    }

    public boolean deleteDirector(int id) {
        for (Director director : directors) {
            if (director.getId() == id) {
                directors.remove(director);
                return true;
            }
        }
        return false;
    }
}
