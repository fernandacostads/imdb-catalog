package com.imdb.application.controller;

import com.imdb.application.impl.ImdbCatalogUseCaseImpl;
import com.imdb.core.cases.actor.Actor;
import com.imdb.core.cases.director.Director;
import com.imdb.core.cases.movie.Movie;

import java.util.List;

public class ImdbCatalogController {
    private final ImdbCatalogUseCaseImpl imdbCatalogUseCaseImpl;

    private ImdbCatalogController(ImdbCatalogUseCaseImpl imdbCatalogUseCaseImpl) {
        this.imdbCatalogUseCaseImpl = imdbCatalogUseCaseImpl;
    }



}
