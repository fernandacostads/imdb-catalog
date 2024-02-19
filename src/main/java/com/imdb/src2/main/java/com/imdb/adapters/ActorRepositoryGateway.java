package com.imdb.src2.main.java.com.imdb.adapters;

import com.imdb.model.Actor;
import com.imdb.src2.main.java.com.imdb.core.cases.actor.ActorUseCase;
import com.imdb.infra.repository.ActorRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ActorRepositoryGateway implements ActorUseCase {
    private final ActorRepository actorRepository;
    private static ActorRepositoryGateway instance;

    private ActorRepositoryGateway() {
        actorRepository = ActorRepository.getInstance();
    }

    public static ActorRepositoryGateway getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ActorRepositoryGateway();
        }
        return instance;
    }

    @Override
    public void addActor(Actor actor) {
        actorRepository.addActor(actor);
    }

    @Override
    //remove um ator
    public void removeActor(Actor actor) {
        actorRepository.removeActor(actor);
    }

    @Override
    //atualiza ator
    public Actor updateActor(Actor actor) {
        return actorRepository.updateActor(actor);
    }

    @Override
    //pesquisa ator por nome
    public Optional<Actor> searchActor(String name) {
        return actorRepository.searchActor(name);
    }

    @Override
    //pega todos os atores
    public List<Actor> getAllActors() {
        return actorRepository.getAllActors();
    }
}
