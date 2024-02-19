package com.imdb.application.impl;

import com.imdb.adapters.ActorRepositoryGateway;
import com.imdb.model.Actor;
import com.imdb.src2.main.java.com.imdb.core.cases.actor.ActorUseCase;

import java.util.List;
import java.util.Optional;

public class ActorUseCaseImpl implements ActorUseCase {
    private final ActorRepositoryGateway actorGateway = ActorRepositoryGateway.getInstance();

    @Override
    public void addActor(Actor actor) {

    }

    @Override
    public void removeActor(Actor actor) {

    }

    @Override
    public Actor updateActor(Actor actor) {
        return null;
    }

    @Override
    public Optional<Actor> searchActor(String name) {
        return Optional.empty();
    }

    @Override
    public List<Actor> getAllActors() {
        return null;
    }
}
