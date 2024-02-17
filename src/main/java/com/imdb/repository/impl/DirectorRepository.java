package com.imdb.repository.impl;

import com.imdb.model.Director;
import com.imdb.model.Director;
import com.imdb.repository.IDirectorRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DirectorRepository implements IDirectorRepository{
  
  private final List<Director> DirectorsList;
  private static final String FILE_PATH = "src/main/java/com/imdb/resources/directors.txt";
  private int idGenerator = 1;

  public DirectorRepository() {
    this.DirectorsList = loadData();
  }


  @Override
  public void addDirector(Director director) {
    director.setId(idGenerator++);
    DirectorsList.add(director);
    updateFile();
    System.out.println("Director saved successfully!");
  }

  @Override
  public Director search(String name) {
    for (Director filme : DirectorsList) {
      if (filme.getName().equalsIgnoreCase(name)) {
        return filme;
      }
    }
    return null; // Filme não encontrado
  }

  @Override
  public List<Director> getAllDirectors() {
    return new ArrayList<>(DirectorsList);
  }

  @Override
  public void updateDirector(Director director) {
    for (int i = 0; i < DirectorsList.size(); i++) {
      if (DirectorsList.get(i).getId() == director.getId()) {
        DirectorsList.set(i, director);
        updateFile();
        System.out.println("Director updated successfully.");
        return;
      }
    }
    System.out.println("Director not found for update.");
  }

  @Override
  public void removeDirector(String name) {
    DirectorsList.removeIf(director -> director.getName().equalsIgnoreCase(name));
    updateFile();
    System.out.println("Director deleted successfully.");
  }

  @Override
  public void updateFile() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
      oos.writeObject(DirectorsList);
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Error saving to file.");
    }
  }

  @Override
  public List<Director> loadData() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
      return (List<Director>) ois.readObject();
    } catch (IOException | ClassNotFoundException | ClassCastException e) {
      // Se ocorrer uma exceção, cria uma nova lista
      return new ArrayList<>();
    }
  }
}
