package com.imdb.repository;

import com.imdb.model.Director;
import java.util.ArrayList;
import java.util.List;

public class DirectorRepository {

  private final List<Director> directorList;

  public DirectorRepository() {
    this.directorList = new ArrayList<>();
  }

  public void addDirector(Director director) {
    directorList.add(director);
  }

  public Director getDirectorById(int id) {
    for (Director director : directorList) {
      if (director.getId() == id) {
        return director;
      }
    }
    return null;
  }

  public Director getDirectorByName(String name) {
    for (Director director : directorList) {
      if (director.getName().equalsIgnoreCase(name)) {
        return director;
      }
    }
    return null;
  }

  public Director findDirectorById(int id) {
    for (Director director : directorList) {
      if (director.getId() == id) {
        return director;
      }
    }
    return null;
  }

  public Director findDirectorByName(String name) {
    for (Director director : directorList) {
      if (director.getName().equalsIgnoreCase(name)) {
        return director;
      }
    }
    return null;
  }

  public Director findDirectorByNationality(String nationality) {
    for (Director director : directorList) {
      if (director.getNationality().equalsIgnoreCase(nationality)) {
        return director;
      }
    }
    return null;
  }

  public boolean updateDirector(Director updatedDirector) {
    for (Director director : directorList) {
      if (director.getId() == updatedDirector.getId()) {
        director.setName(updatedDirector.getName());
        director.setNationality(updatedDirector.getNationality());
        return true;
      }
    }
    return false;
  }

  public boolean deleteDirector(int id) {
    for (Director director : directorList) {
      if (director.getId() == id) {
        directorList.remove(director);
        return true;
      }
    }
    return false;
  }

  public List<Director> getAllDirectors() {
    return directorList;
  }
}
