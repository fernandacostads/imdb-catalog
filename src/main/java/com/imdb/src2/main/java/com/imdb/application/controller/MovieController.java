package com.imdb.application.controller;

import com.imdb.application.impl.MovieUseCaseImpl;

public class MovieController {
    private final MovieUseCaseImpl movieUseCaseImpl;

    private MovieController(MovieUseCaseImpl movieUseCaseImpl) {
        this.movieUseCaseImpl = movieUseCaseImpl;
    }

}
