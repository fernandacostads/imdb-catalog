package com.imdb.appServices;

import com.imdb.model.Movie;
import com.imdb.repository.impl.MovieRepository;
import java.util.List;
import java.util.Optional;

public class MovieService {

  private final MovieRepository movieRepository;

  public MovieService() {
    movieRepository = MovieRepository.getInstance();
  }

  public void addMovie(Movie movie) {
    movieRepository.addMovie(movie);
  }

  public void removeMovie(Movie movie) {
    movieRepository.removeMovie(movie);
  }

  public Movie updateMovie(Movie movie) {
    return movieRepository.updateMovie(movie);
  }

  public Optional<Movie> searchMovie(String Title) {
    return movieRepository.searchMovie(Title);
  }

  public Optional<Movie> searchMovieById(int id) {
    return getAllMovies()
      .stream()
      .filter(movie -> movie.getId() == id)
      .findFirst();
  }

  public List<Movie> getAllMovies() {
    return movieRepository.getAllMovies();
  }
}
