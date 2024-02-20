package com.imdb.repository.impl;

import com.imdb.model.Director;
import com.imdb.repository.IDirectorRepository;
import com.imdb.util.FileHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DirectorRepositoryimpl implements IDirectorRepository {

  private static final String FILE_PATH =
          "src/main/java/com/imdb/util/resources/directors.txt";
  private static DirectorRepositoryimpl instance;
  private static List<Director> directorsList;

  private int idGenerator;

  private DirectorRepositoryimpl() {
    directorsList = new ArrayList<>(10);
    directorsList = FileHandler.loadDirectorsFromFile(FILE_PATH);
    idGenerator = directorsList.isEmpty() ? 1 : directorsList.getLast().getId() + 1;
  }

  public static synchronized DirectorRepositoryimpl getInstance() {
    if (instance == null) {
      instance = new DirectorRepositoryimpl();
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
    FileHandler.updateFileD(directorsList, FILE_PATH);
  }

  @Override
  public void removeDirector(Director director) {
    Optional<Director> optionalDirector = getDirector(director);
    if (optionalDirector.isEmpty()) {
      throw new IllegalArgumentException("The director does not exist!");
    }
    directorsList.remove(optionalDirector.get());
    FileHandler.updateFileD(directorsList, FILE_PATH);

  }

  @Override
  public Director updateDirector(Director director) {
    Optional<Director> optionalDirector = getDirector(director);
    if (optionalDirector.isEmpty()) {
      throw new IllegalArgumentException("The director does not exist");
    }
    directorsList.remove(optionalDirector.get());
    directorsList.add(director);
    FileHandler.updateFileD(directorsList, FILE_PATH);
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
    return directorsList.stream().filter(aux -> aux.equals(director)).findFirst();
  }
}
