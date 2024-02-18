package com.imdb.repository.impl;

import com.imdb.model.Director;
import com.imdb.repository.IDirectorRepository;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DirectorRepository implements IDirectorRepository{
  private static DirectorRepository instance;
  private static List<Director> directorsList;

  private DirectorRepository() {
    directorsList = new ArrayList<>(10);
  }
  public static synchronized DirectorRepository getInstance() {
    if (instance == null) {
      instance = new DirectorRepository();
    }
    return instance;
  }
  private int idGenerator = 1;
  @Override
  public void addDirector(Director director) {
    Optional<Director> optionalDirector = getDirector(director);
    if (optionalDirector.isPresent()) {
      throw new IllegalArgumentException("Director had already exist!");
    }
    director.setId(idGenerator++);
    directorsList.add(director);
    updateFile();
  }

  @Override
  public void removeDirector(Director director) {
    Optional<Director> optionalDirector = getDirector(director);
    if (optionalDirector.isEmpty()) {
      throw new IllegalArgumentException("The director does not exist!");
    }
    directorsList.remove(optionalDirector.get());
    updateFile();
  }

  @Override
  public Director updateDirector(Director director) {
    Optional<Director> optionalDirector = getDirector(director);
    if (optionalDirector.isEmpty()) {
      throw new IllegalArgumentException("The director does not exist");
    }
    directorsList.remove(optionalDirector.get());
    directorsList.add(director);
    updateFile();
    return director;
  }

  @Override
  public Optional<Director> searchDirector(String name) {
    return directorsList.stream()
            .filter(aux -> aux.getName().equalsIgnoreCase(name))
            .findFirst();
  }

  @Override
  public List<Director> getAllDirectors() {
    return directorsList;
  }

  @Override
  public void updateFile() {
    try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
      outputStream.writeObject(directorsList);
    } catch (IOException e) {
      System.err.println("Error saving to file: " + e.getMessage());
    }
  }
  private static final String FILE_PATH = "src/main/java/com/imdb/resources/directors.txt";
  @SuppressWarnings("unchecked")
  private List<Director> loadData() {
    try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
      return (List<Director>) inputStream.readObject();
    } catch (IOException | ClassNotFoundException e) {
      System.err.println("Error loading data from file: " + e.getMessage());
      return new ArrayList<>();
    }
  }

  private Optional<Director> getDirector(Director director) {
    return directorsList.stream().filter(aux -> aux.equals(director)).findFirst();
  }
}
