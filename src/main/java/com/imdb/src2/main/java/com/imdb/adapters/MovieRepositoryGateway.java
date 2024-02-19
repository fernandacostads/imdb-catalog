package com.imdb.adapters;

import com.imdb.model.Movie;
import com.imdb.src2.main.java.com.imdb.core.cases.movie.MovieUseCase;
import com.imdb.infra.repository.MovieRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public void addMovie(Movie movie) {
        movieRepository.addMovie(movie);
    }

    //remove um filme
    @Override
    public void removeMovie(Movie movie) {
        movieRepository.removeMovie(movie);
    }

    //atualiza um filme
    @Override
    public Movie updateMovie(Movie movie) {
        return movieRepository.updateMovie(movie);
    }

    //pesquisa um filme pelo titulo
    @Override
    public Optional<Movie> searchMovie(String Title) {
        return movieRepository.searchMovie(Title);
    }

    //pesquisa um filme pelo id
    @Override
    public Optional<Movie> searchMovieById(int id) {
        return getAllMovies()
                .stream()
                .filter(movie -> movie.getId() == id)
                .findFirst();
    }

    //pega todos os filmes
    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.getAllMovies();
    }
}
