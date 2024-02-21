package com.imdb.repository;

import com.imdb.model.Actor;
import com.imdb.dto.ActorDTO;

import java.util.List;
import java.util.Optional;

public interface IActorRepository {
  void addActor(ActorDTO actor);

  void removeActor(Actor actor);

  Actor updateActor(Actor actor);

  Optional<Actor> searchActorByName(String name);

  List<ActorDTO> getAllActors();
}
