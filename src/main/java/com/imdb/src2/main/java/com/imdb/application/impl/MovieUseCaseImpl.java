package com.imdb.application.impl;

import com.imdb.adapters.MovieRepositoryGateway;
import com.imdb.core.cases.movie.MovieUseCase;

public class MovieUseCaseImpl implements MovieUseCase {
    private final MovieRepositoryGateway movieGateway = MovieRepositoryGateway.getInstance();

    @Override
    public void delete(Long id) {
        movieGateway.delete(id);
    }
}
