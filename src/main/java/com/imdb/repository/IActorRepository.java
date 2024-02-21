package com.imdb.repository;

import com.imdb.model.Actor;

import java.util.Optional;

public interface IActorRepository {
    void addActor(Actor actor);

    void actorPresent(String name);

    void actorNotFound(String name);

    void removeActor(Actor actor);

    Actor updateActor(Actor actor, String name, String nationality);

    Optional<Actor> searchActor(String name);

    String getAllActors();
}
