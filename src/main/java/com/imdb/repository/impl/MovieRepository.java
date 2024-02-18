package com.imdb.repository.impl;

import com.imdb.model.Movie;
import com.imdb.repository.IMovieRepository;
import com.imdb.resources.DataCollector;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieRepository implements IMovieRepository {

  private static final String FILE_PATH =
    "src/main/java/com/imdb/resources/movies.txt";
  private static MovieRepository instance;
  private static List<Movie> moviesList;

  private MovieRepository() {
    moviesList = new ArrayList<>(10);
    /*try {
      moviesList = DataCollector.loadFile(FILE_PATH);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }*/
  }

  public static synchronized MovieRepository getInstance() {
    if (instance == null) {
      instance = new MovieRepository();
    }
    return instance;
  }

  private int idGenerator = 1;

  @Override
  public void addMovie(Movie movie) {
    Optional<Movie> optionalMovie = getMovie(movie);
    if (optionalMovie.isPresent()) {
      throw new IllegalArgumentException("Movie had already exist!");
    }
    movie.setId(idGenerator++);
    moviesList.add(movie);
    try {
      DataCollector.updateFile(moviesList, FILE_PATH);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void removeMovie(Movie movie) {
    Optional<Movie> optionalMovie = getMovie(movie);
    if (optionalMovie.isEmpty()) {
      throw new IllegalArgumentException("The movie does not exist!");
    }
    moviesList.remove(optionalMovie.get());
    try {
      DataCollector.updateFile(moviesList, FILE_PATH);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Movie updateMovie(Movie movie) {
    Optional<Movie> optionalMovie = getMovie(movie);
    if (optionalMovie.isEmpty()) {
      throw new IllegalArgumentException("The movie does not exist!");
    }
    moviesList.remove(optionalMovie.get());
    moviesList.add(movie);
    try {
      DataCollector.updateFile(moviesList, FILE_PATH);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return movie;
  }

  @Override
  public Optional<Movie> searchMovie(String title) {
    return moviesList
      .stream()
      .filter(aux -> aux.getTitle().equalsIgnoreCase(title))
      .findFirst();
  }

  @Override
  public List<Movie> getAllMovies() {
    return moviesList;
  }

  private Optional<Movie> getMovie(Movie movie) {
    return moviesList.stream().filter(aux -> aux.equals(movie)).findFirst();
  }
}
