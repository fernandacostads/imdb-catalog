package com.imdb.core.cases.catalog;

import com.imdb.core.cases.actor.Actor;
import com.imdb.core.cases.director.Director;
import com.imdb.core.cases.movie.Movie;

import java.util.List;

public interface ImdbCatalogUseCase {
    void cadastrarFilme(String name, String dataDeLancamento, String descricao);
    void cadastrarAtor(Actor actor);
    void cadastrarDiretor(Director director);
    void associafilmeComAtoresDiretores(Movie movie, List<Actor> actors, List<Director> directors);
    void pesquisarFilme(String nameMovie);
}
