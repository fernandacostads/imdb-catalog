package com.imdb.adapters;

import com.imdb.core.cases.actor.ActorUseCase;
import com.imdb.infra.repository.ActorRepository;

import java.util.Objects;

public class ActorRepositoryGateway implements ActorUseCase {
    private final ActorRepository actorRepository;
    private static ActorRepositoryGateway instance;
    private ActorRepositoryGateway(){
        actorRepository = ActorRepository.getInstance();
    }

    public static ActorRepositoryGateway getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ActorRepositoryGateway();
        }
        return instance;
    }

    @Override
    public void delete(Long id) {
        //actorRepository.delete();
    }
}
