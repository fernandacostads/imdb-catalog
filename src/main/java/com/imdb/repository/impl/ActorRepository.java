package com.imdb.repository.impl;

import com.imdb.model.Actor;
import com.imdb.repository.IActorRepository;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActorRepository implements IActorRepository {

  private static final String FILE_PATH =
    "src/main/java/com/imdb/resources/actors.txt";
  private static ActorRepository instance;
  private static List<Actor> actorsList;

  private ActorRepository() {
    actorsList = new ArrayList<>(10);
    //actorsList = loadData();
  }

  public static synchronized ActorRepository getInstance() {
    if (instance == null) {
      instance = new ActorRepository();
    }
    return instance;
  }

  private int idGenerator = 1;

  @Override
  public void addActor(Actor actor) {
    Optional<Actor> optionalActor = getActor(actor);
    if (optionalActor.isPresent()) {
      throw new IllegalArgumentException("Actor had already exist!");
    }
    actor.setId(idGenerator++);
    actorsList.add(actor);
    // updateFile();
  }

  @Override
  public void removeActor(Actor actor) {
    Optional<Actor> optionalActor = getActor(actor);
    if (optionalActor.isEmpty()) {
      throw new IllegalArgumentException("The actor does not exist!");
    }
    actorsList.remove(optionalActor.get());
    //updateFile();
  }

  @Override
  public Actor updateActor(Actor actor) {
    Optional<Actor> optionalActor = getActor(actor);
    if (optionalActor.isEmpty()) {
      throw new IllegalArgumentException("The actor does not exist");
    }
    actorsList.remove(optionalActor.get());
    actorsList.add(actor);
    //updateFile();
    return actor;
  }

  @Override
  public Optional<Actor> searchActor(String name) {
    return actorsList
      .stream()
      .filter(aux -> aux.getName().equalsIgnoreCase(name))
      .findFirst();
  }

  @Override
  public List<Actor> getAllActors() {
    return actorsList;
  }

  private Optional<Actor> getActor(Actor actor) {
    return actorsList.stream().filter(aux -> aux.equals(actor)).findFirst();
  }
}
