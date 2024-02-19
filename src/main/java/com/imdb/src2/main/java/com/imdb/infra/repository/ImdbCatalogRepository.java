package com.imdb.infra.repository;

import com.imdb.core.cases.movie.Movie;
import com.imdb.infra.database.ImdbCatalog;

import java.util.Objects;

public class ImdbCatalogRepository  extends AbstractRepository{
    private static final ImdbCatalog imdbCatalog = ImdbCatalog.getInstance();
    private static ImdbCatalogRepository instance;

    public ImdbCatalogRepository() {
        super(imdbCatalog);
    }

    public static ImdbCatalogRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ImdbCatalogRepository();
        }
        return instance;
    }

    @Override
    protected Class<?> modelClass() {
        return com.imdb.core.cases.catalog.ImdbCatalog.class;
    }

    public void create(Movie movie){
        imdbCatalog.create(movie);
    }
}
