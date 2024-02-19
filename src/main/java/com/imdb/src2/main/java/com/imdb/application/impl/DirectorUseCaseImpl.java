package com.imdb.application.impl;

import com.imdb.model.Director;
import com.imdb.src2.main.java.com.imdb.adapters.DirectorRepositoryGateway;
import com.imdb.src2.main.java.com.imdb.core.cases.director.DirectorUseCase;

import java.util.List;
import java.util.Optional;

public class DirectorUseCaseImpl implements DirectorUseCase {
    private final DirectorRepositoryGateway directorGateway = DirectorRepositoryGateway.getInstance();

    @Override
    public void addDirector(Director director) {

    }

    @Override
    public void removeDirector(Director director) {

    }

    @Override
    public Director updateDirector(Director director) {
        return null;
    }

    @Override
    public Optional<Director> searchDirector(String name) {
        return Optional.empty();
    }

    @Override
    public List<Director> getAllDirectors() {
        return null;
    }
}
