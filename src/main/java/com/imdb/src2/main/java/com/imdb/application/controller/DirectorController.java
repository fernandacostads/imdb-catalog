package com.imdb.application.controller;

import com.imdb.application.impl.DirectorUseCaseImpl;

public class DirectorController {
    private final DirectorUseCaseImpl directorUseCaseImpl;

    private DirectorController(DirectorUseCaseImpl directorUseCaseImpl) {
        this.directorUseCaseImpl = directorUseCaseImpl;
    }

}
