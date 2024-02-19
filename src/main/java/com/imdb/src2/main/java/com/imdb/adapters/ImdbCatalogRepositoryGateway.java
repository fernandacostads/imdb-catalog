package com.imdb.adapters;

import com.imdb.core.cases.actor.Actor;
import com.imdb.core.cases.director.Director;
import com.imdb.core.cases.movie.Movie;
import com.imdb.infra.repository.ImdbCatalogRepository;

import java.util.List;
import java.util.Objects;

public class ImdbCatalogRepositoryGateway  {
    private final ImdbCatalogRepository imdbCatalogRepository;
    private static ImdbCatalogRepositoryGateway instance;
    private ImdbCatalogRepositoryGateway(){
        imdbCatalogRepository = ImdbCatalogRepository.getInstance();
    }

    public static ImdbCatalogRepositoryGateway getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ImdbCatalogRepositoryGateway();
        }
        return instance;
    }

    public void cadastrarFilme(Movie movie) {
        imdbCatalogRepository.create(movie);
    }

    public void cadastrarAtor(Actor actor) {

    }

    public void cadastrarDiretor(Director director) {

    }

    public void associafilmeComAtoresDiretores(Movie movie, List<Actor> actors, List<Director> directors) {

    }

    public void pesquisarFilme(String nameMovie) {

    }
}
