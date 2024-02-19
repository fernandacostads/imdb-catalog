package com.imdb.appServices;

import com.imdb.model.Actor;
import com.imdb.repository.impl.ActorRepository;
import java.util.List;
import java.util.Optional;

public class ActorService {

  //ficaria em gateway
  private final ActorRepository actorRepository;

  public ActorService() {
    actorRepository = ActorRepository.getInstance();
  }

  //adiciona um ator
  public void addActor(Actor actor) {
    actorRepository.addActor(actor);
  }

  //remove um ator
  public void removeActor(Actor actor) {
    actorRepository.removeActor(actor);
  }

  //atualiza ator
  public Actor updateActor(Actor actor) {
    return actorRepository.updateActor(actor);
  }

  //pesquisa ator por nome
  public Optional<Actor> searchActor(String name) {
    return actorRepository.searchActor(name);
  }

  //pega todos os atores
  public List<Actor> getAllActors() {
    return actorRepository.getAllActors();
  }
}
