package com.imdb.src2.main.java.com.imdb.core.cases.movie;

import com.imdb.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieUseCase {
    void addMovie(com.imdb.model.Movie movie);

    //remove um filme
    void removeMovie(com.imdb.model.Movie movie);

    //atualiza um filme
    Movie updateMovie(com.imdb.model.Movie movie);

    //pesquisa um filme pelo titulo
    Optional<Movie> searchMovie(String Title);

    //pesquisa um filme pelo id
    Optional<Movie> searchMovieById(int id);

    //pega todos os filmes
    List<Movie> getAllMovies();
}
