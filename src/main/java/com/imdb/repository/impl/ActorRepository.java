package com.imdb.repository.impl;

import com.imdb.model.Actor;
import com.imdb.repository.IActorRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActorRepository implements IActorRepository {
  private static ActorRepository instance;
  private static List<Actor> actorsList;

    private ActorRepository () {
    actorsList = new ArrayList<>(10);
  }
  public static synchronized ActorRepository getInstance(){
    if(instance == null){
     instance = new ActorRepository();
    }
    return instance;
  }
  private int idGenerator = 1;

  @Override
  public void addActor(Actor actor) {
    Optional<Actor> optionalActor = getActor(actor);
    if(optionalActor.isPresent()) {
      throw new IllegalArgumentException("Actor had already exist!");
    }
    actor.setId(idGenerator++);
    actorsList.add(actor);
    updateFile();
  }

   @Override
  public void removeActor(Actor actor) {
    Optional<Actor> optionalActor = getActor(actor);
    if(optionalActor.isEmpty()) {
      throw new IllegalArgumentException("The actor does not exist!");
    }
    actorsList.remove(optionalActor.get());
     updateFile();
  }

  @Override
  public Actor updateActor(Actor actor) {
    Optional<Actor> optionalActor = getActor(actor);
    if(optionalActor.isEmpty()){
      throw new IllegalArgumentException("The actor does not exist");
    }
    actorsList.remove(optionalActor.get());
    actorsList.add(actor);
    updateFile();
    return actor;
  }

  @Override
  public Optional<Actor> searchActor(String name){
    return actorsList.stream()
            .filter(aux -> aux.getName()
                    .equalsIgnoreCase(name)).findFirst();
  }

  @Override
  public List<Actor> getAllActors() {
    return actorsList;
  }

  private static final String FILE_PATH = "src/main/java/com/imdb/resources/actors.txt";
  @Override
  public void updateFile() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
      oos.writeObject(actorsList);
    } catch (IOException e) {
      System.out.println("Error saving to file: " + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  private List<Actor> loadData() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
      return (List<Actor>) ois.readObject();
    } catch (IOException | ClassNotFoundException | ClassCastException e) {
      System.err.println("Error loading data from file: " + e.getMessage());
      return new ArrayList<>();
    }
    }

  private Optional<Actor> getActor(Actor actor) {
    return actorsList.stream().filter(aux -> aux.equals(actor)).findFirst();
  }
}

