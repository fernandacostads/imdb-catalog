package com.imdb.appServices;

import com.imdb.model.Actor;
import com.imdb.repository.impl.ActorRepository;
import java.util.List;
import java.util.Optional;

public class ActorService {

  private final ActorRepository actorRepository;

  public ActorService() {
    actorRepository = ActorRepository.getInstance();
  }

  public void addActor(Actor actor) {
    actorRepository.addActor(actor);
  }

  public void removeActor(Actor actor) {
    actorRepository.removeActor(actor);
  }

  public Actor updateActor(Actor actor) {
    return actorRepository.updateActor(actor);
  }

  public Optional<Actor> searchActor(String name) {
    return actorRepository.searchActor(name);
  }

  public List<Actor> getAllActors() {
    return actorRepository.getAllActors();
  }
}
