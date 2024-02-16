package com.imdb.appServices;

import com.imdb.model.Director;
import com.imdb.repository.DirectorRepository;

public class DirectorService {
    private final DirectorRepository directorRepository;

    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public void addDirector(Director director) {
        directorRepository.addDirector(director);
    }

    public Director findDirectorById(int id) {
        return directorRepository.findDirectorById(id);
    }

    public Director findDirectorByName(String name) {
        return directorRepository.findDirectorByName(name);
    }

    public Director findDirectorByNationality(String nationality) {
        return directorRepository.findDirectorByNationality(nationality);
    }

    public boolean updateDirector(Director updatedDirector) {
        return directorRepository.updateDirector(updatedDirector);
    }

    public boolean deleteDirector(int id) {
        return directorRepository.deleteDirector(id);
    }
}
