package com.imdb.application.controller;

import com.imdb.application.impl.ActorUseCaseImpl;

public class ActorController {
    private final ActorUseCaseImpl actorUseCaseImpl;

    private ActorController(ActorUseCaseImpl actorUseCaseImpl) {
        this.actorUseCaseImpl = actorUseCaseImpl;
    }

}
