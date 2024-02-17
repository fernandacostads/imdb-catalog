package com.imdb.appServices;

import com.imdb.model.Actor;
import com.imdb.model.Actor;
import com.imdb.model.Movie;
import com.imdb.repository.impl.ActorRepository;
import java.util.ArrayList;
import java.util.List;

public class ActorService {

  private static ActorRepository actorRepository = null;

  public ActorService(ActorRepository actorRepository) {
    this.actorRepository = actorRepository;
  }

  public static void addActor(Actor actor) {
    actorRepository.addActor(actor);
  }

  public Actor searchActor(String name) throws NullPointerException{
    try{
      return actorRepository.search(name);
    }catch(NullPointerException e) {
      return null;
    }
  }

  public List<Actor> getAllActors() {
    return actorRepository.getAllActors();
  }

  public void updateActor(Actor actor) {
    actorRepository.updateActor(actor);
  }

  public void removeActor(String name) {
    actorRepository.removeActor(name);
  }

  public void editActor(Movie movieToEdit) {
  }

  public Actor getActorByName(String name) {
    List<Actor> movieList = getAllActors();

    for (Actor actor : movieList) {
      if (actor.getName().equalsIgnoreCase(name)) {
        return actor;
      }
    }
    // Se nenhum filme for encontrado com o nome especificado
    return null;
  }
}
