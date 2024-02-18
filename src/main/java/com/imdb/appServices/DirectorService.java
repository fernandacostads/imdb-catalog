package com.imdb.appServices;

import com.imdb.model.Director;
import com.imdb.repository.impl.DirectorRepository;
import java.util.List;
import java.util.Optional;

public class DirectorService {

  private final DirectorRepository directorRepository;

  public DirectorService() {
    directorRepository = DirectorRepository.getInstance();
  }

  public void addDirector(Director director) {
    directorRepository.addDirector(director);
  }

  public void removeDirector(Director director) {
    directorRepository.removeDirector(director);
  }

  public Director updateDirector(Director director) {
    return directorRepository.updateDirector(director);
  }

  public Optional<Director> searchDirector(String name) {
    return directorRepository.searchDirector(name);
  }

  public List<Director> getAllDirectors() {
    return directorRepository.getAllDirectors();
  }
  /* public Director findDirectorByName(String name) {
    return directorRepository.findDirectorByName(name);
  }

  public Director findDirectorByNationality(String nationality) {
    return directorRepository.findDirectorByNationality(nationality);
  }*/

}
