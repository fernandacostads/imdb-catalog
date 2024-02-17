package com.imdb.appServices;

import com.imdb.model.Movie;
import com.imdb.repository.MovieRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MovieService {

  private List<Movie> movies;

  public MovieService() {
    movies = new ArrayList<>();
  }

  private int nextId = 1;
  private final MovieRepository movieRepository = new MovieRepository();

  public List<Movie> getAllMovies() {
    return movieRepository.getAllMovies();
  }

  public Movie getMovieByName(String name) {
    List<Movie> movies = getAllMovies();
    for (Movie movie : movies) {
      if (movie.getTitle().equalsIgnoreCase(name)) {
        return movie;
      }
    }
    return null;
  }

  /*public void addMovie(Movie movie) {
        movie.setId(nextId++);
        movieRepository.addMovie(movie);
    }

    public void deleteMovie(Movie movie) {
        movieRepository.deleteMovie(movie.getId());
    }*/

  public boolean deleteMovieById(int movieId) {
    for (Iterator<Movie> iterator = movies.iterator(); iterator.hasNext();) {
      Movie movie = iterator.next();
      if (movie.getId() == movieId) {
        iterator.remove();
        return true;
      }
    }
    return false;
  }
}
