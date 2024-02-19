package com.imdb.infra.repository;


import com.imdb.core.cases.movie.Movie;
import com.imdb.infra.database.ImdbCatalog;

import java.util.Objects;

public class MovieRepository  extends AbstractRepository{
    private static final ImdbCatalog imdbCatalog = ImdbCatalog.getInstance();
    private static MovieRepository instance;
    public MovieRepository() {
        super(imdbCatalog);
    }

    public static MovieRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new MovieRepository();
        }
        return instance;
    }

    @Override
    protected Class<?> modelClass() {
        return Movie.class;
    }
}
