package com.imdb.appServices;

import com.imdb.model.Director;
import com.imdb.model.Movie;
import com.imdb.repository.DirectorRepository;
import java.util.ArrayList;
import java.util.List;

public class DirectorService {

  private static DirectorRepository directorRepository;

  public DirectorService(DirectorRepository directorRepository) {
    this.directorRepository = directorRepository;
  }

  public static void addDirector(Director director) {
    directorRepository.addDirector(director);
  }

  public Director searchDirector(String name) {
    try{
      return directorRepository.search(name);
    }catch (NumberFormatException e){
      return null;
    }
  }

  public List<Director> getAllDirectors() {
    return directorRepository.getAllDirectors();
  }

  public void updateDirector(Director director) {
    directorRepository.updateDirector(director);
  }

  public void removeDirector(String name) {
    directorRepository.removeDirector(name);
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

  public void editDirector(Movie movieToEdit) {}
}
