package com.imdb.application.impl;

import com.imdb.adapters.ImdbCatalogRepositoryGateway;
import com.imdb.core.cases.actor.Actor;
import com.imdb.core.cases.director.Director;
import com.imdb.core.cases.movie.Movie;
import com.imdb.core.cases.catalog.ImdbCatalogUseCase;

import java.util.List;

public class ImdbCatalogUseCaseImpl implements ImdbCatalogUseCase {
    private final ImdbCatalogRepositoryGateway imdbCatalogGateway = ImdbCatalogRepositoryGateway.getInstance();

    @Override
    public void cadastrarFilme(String name, String dataDeLancamento, String descricao) {
        Movie movie = new Movie();
        movie.setName(name);
        movie.setDataDeLancamento(dataDeLancamento);
        movie.setDescricao(descricao);
        imdbCatalogGateway.cadastrarFilme(movie);
    }

    @Override
    public void cadastrarAtor(Actor actor) {

    }

    @Override
    public void cadastrarDiretor(Director director) {

    }

    @Override
    public void associafilmeComAtoresDiretores(Movie movie, List<Actor> actors, List<Director> directors) {

    }

    @Override
    public void pesquisarFilme(String nameFilme) {
    }
}
