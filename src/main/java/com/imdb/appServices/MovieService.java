package com.imdb.appServices;

import com.imdb.model.Movie;
import com.imdb.repository.impl.MovieRepository;

import java.util.ArrayList;
import java.util.List;

public class MovieService {

  private static MovieRepository movieRepository = null;

  public MovieService(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  public void addMovie(Movie movie) {
    movieRepository.addMovie(movie);
  }

  public static Movie searchMovie(int id) {
    return movieRepository.search(id);
  }

  public List<Movie> getAllMovies() {
    return movieRepository.getAllMovies();
  }

  public void updateMovie(Movie movie) {
    movieRepository.updateMovie(movie);
  }

  public void removeMovie(int id) {
    movieRepository.removeMovie(id);
  }

  public Movie getMovieByName(String name) {
    List<Movie> movieList = getAllMovies();

    for (Movie movie : movieList) {
      if (movie.getTitle().equalsIgnoreCase(name)) {
        return movie;
      }
    }
    // Se nenhum filme for encontrado com o nome especificado
    return null;
  }
}
