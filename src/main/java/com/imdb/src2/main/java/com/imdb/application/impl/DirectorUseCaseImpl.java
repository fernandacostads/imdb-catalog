package com.imdb.application.impl;

import com.imdb.src2.main.java.com.imdb.adapters.DirectorRepositoryGateway;
import com.imdb.core.cases.director.DirectorUseCase;

public class DirectorUseCaseImpl implements DirectorUseCase {
    private final DirectorRepositoryGateway directorGateway = DirectorRepositoryGateway.getInstance();

    @Override
    public void delete(Long id) {
        directorGateway.delete(id);
    }
}
