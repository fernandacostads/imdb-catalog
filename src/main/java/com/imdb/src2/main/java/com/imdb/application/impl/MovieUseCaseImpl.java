package com.imdb.application.impl;

import com.imdb.adapters.MovieRepositoryGateway;
import com.imdb.model.Movie;
import com.imdb.src2.main.java.com.imdb.core.cases.movie.MovieUseCase;

import java.util.List;
import java.util.Optional;

public class MovieUseCaseImpl implements MovieUseCase {
    private final MovieRepositoryGateway movieGateway = MovieRepositoryGateway.getInstance();

    @Override
    public void addMovie(Movie movie) {

    }

    @Override
    public void removeMovie(Movie movie) {

    }

    @Override
    public Movie updateMovie(Movie movie) {
        return null;
    }

    @Override
    public Optional<Movie> searchMovie(String Title) {
        return Optional.empty();
    }

    @Override
    public Optional<Movie> searchMovieById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Movie> getAllMovies() {
        return null;
    }
}
