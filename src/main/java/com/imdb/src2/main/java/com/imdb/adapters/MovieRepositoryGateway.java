package com.imdb.adapters;

import com.imdb.core.cases.movie.MovieUseCase;
import com.imdb.infra.repository.ImdbCatalogRepository;
import com.imdb.infra.repository.MovieRepository;

import java.util.Objects;

public class MovieRepositoryGateway implements MovieUseCase {
    private final  MovieRepository movieRepository;
    private static MovieRepositoryGateway instance;
    private MovieRepositoryGateway(){
        movieRepository = MovieRepository.getInstance();
    }

    public static MovieRepositoryGateway getInstance() {
        if (Objects.isNull(instance)) {
            instance = new MovieRepositoryGateway();
        }
        return instance;
    }

    @Override
    public void delete(Long id) {

    }
}
