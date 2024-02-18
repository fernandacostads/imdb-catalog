package com.imdb.repository.impl;

import com.imdb.model.Director;
import com.imdb.repository.IDirectorRepository;
import com.imdb.resources.DataCollector;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DirectorRepository implements IDirectorRepository {

  private static final String FILE_PATH =
    "src/main/java/com/imdb/resources/directors.txt";
  private int idGenerator = 1;
  private static DirectorRepository instance;
  private static List<Director> directorsList;

  private DirectorRepository() {
    directorsList = new ArrayList<>(10);
  }

  public static synchronized DirectorRepository getInstance() {
    if (instance == null) {
      instance = new DirectorRepository();
      // directorsList = loadData();
    }
    return instance;
  }

  @Override
  public void addDirector(Director director) {
    Optional<Director> optionalDirector = getDirector(director);
    if (optionalDirector.isPresent()) {
      throw new IllegalArgumentException("Director had already exist!");
    }
    director.setId(idGenerator++);
    directorsList.add(director);
    try {
      DataCollector.updateFileD(directorsList, FILE_PATH);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void removeDirector(Director director) {
    Optional<Director> optionalDirector = getDirector(director);
    if (optionalDirector.isEmpty()) {
      throw new IllegalArgumentException("The director does not exist!");
    }
    directorsList.remove(optionalDirector.get());
    try {
      DataCollector.updateFileD(directorsList, FILE_PATH);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Director updateDirector(Director director) {
    Optional<Director> optionalDirector = getDirector(director);
    if (optionalDirector.isEmpty()) {
      throw new IllegalArgumentException("The director does not exist");
    }
    directorsList.remove(optionalDirector.get());
    directorsList.add(director);
    try {
      DataCollector.updateFileD(directorsList, FILE_PATH);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return director;
  }

  @Override
  public Optional<Director> searchDirector(String name) {
    return directorsList
      .stream()
      .filter(aux -> aux.getName().equalsIgnoreCase(name))
      .findFirst();
  }

  @Override
  public List<Director> getAllDirectors() {
    return directorsList;
  }

  private Optional<Director> getDirector(Director director) {
    return directorsList
      .stream()
      .filter(aux -> aux.equals(director))
      .findFirst();
  }
}
