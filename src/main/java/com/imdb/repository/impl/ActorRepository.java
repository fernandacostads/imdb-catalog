package com.imdb.repository.impl;

import com.imdb.model.Actor;
import com.imdb.repository.IActorRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ActorRepository implements IActorRepository {


  private static List<Actor> ActorsList;
  private static final String FILE_PATH = "src/main/java/com/imdb/resources/actors.txt";
  private static int idGenerator = 1;

  public ActorRepository() {
    this.ActorsList = loadData();
  }


  @Override
  public void addActor(Actor actor) {
    actor.setId(idGenerator++);
    ActorsList.add(actor);
    updateFile();
    System.out.println("Actor saved successfully!");
  }

  @Override
  public Actor search(String name) {
    for (Actor filme : ActorsList) {
      if (filme.getName().equalsIgnoreCase(name)) {
        return filme;
      }
    }
    return null; // Filme não encontrado
  }

  @Override
  public List<Actor> getAllActors() {
    return new ArrayList<>(ActorsList);
  }

  @Override
  public void updateActor(Actor actor) {
    for (int i = 0; i < ActorsList.size(); i++) {
      if (ActorsList.get(i).getId() == actor.getId()) {
        ActorsList.set(i, actor);
        updateFile();
        System.out.println("Actor updated successfully.");
        return;
      }
    }
    System.out.println("Actor not found for update.");
  }

  @Override
  public void removeActor(String name) {
    ActorsList.removeIf(actor -> actor.getName().equalsIgnoreCase(name));
    updateFile();
    System.out.println("Actor deleted successfully.");
  }

  @Override
  public void updateFile() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
      oos.writeObject(ActorsList);
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Error saving to file.");
    }
  }

  @Override
  public List<Actor> loadData() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
      return (List<Actor>) ois.readObject();
    } catch (IOException | ClassNotFoundException | ClassCastException e) {
      // Se ocorrer uma exceção, cria uma nova lista
      return new ArrayList<>();
    }
  }
}
