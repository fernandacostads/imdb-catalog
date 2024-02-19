package com.imdb.src2.main.java.com.imdb.core.cases.actor;

import com.imdb.model.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorUseCase {
    void addActor(Actor actor);

    //remove um ator
    void removeActor(Actor actor);

    //atualiza ator
    Actor updateActor(Actor actor);

    //pesquisa ator por nome
    Optional<Actor> searchActor(String name);

    //pega todos os atores
    List<Actor> getAllActors();
}
