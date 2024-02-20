package com.imdb.repository.impl;

import com.imdb.model.Movie;
import com.imdb.repository.IMovieRepository;
import com.imdb.util.FileHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieRepositoryimpl implements IMovieRepository {

  private static final String FILE_PATH =
          "src/main/java/com/imdb/util/resources/movies.txt";
  private static MovieRepositoryimpl instance;
  private static List<Movie> moviesList;
  private int idGenerator;

  private MovieRepositoryimpl() {
    moviesList = new ArrayList<>(10);
    moviesList = FileHandler.loadMoviesFromFile(FILE_PATH);
    idGenerator = moviesList.isEmpty() ? 1 : moviesList.getLast().getId() + 1;
  }

  public static synchronized MovieRepositoryimpl getInstance() {
    if (instance == null) {
      instance = new MovieRepositoryimpl();
    }
    return instance;
  }

  @Override
  public void addMovie(Movie movie) {
    Optional<Movie> optionalMovie = getMovie(movie);
    if (optionalMovie.isPresent()) {
      throw new IllegalArgumentException("Movie had already exist!");
    }
    movie.setId(idGenerator++);
    moviesList.add(movie);
    FileHandler.updateFile(moviesList, FILE_PATH);
  }

  @Override
  public void removeMovie(Movie movie) {
    Optional<Movie> optionalMovie = getMovie(movie);
    if (optionalMovie.isEmpty()) {
      throw new IllegalArgumentException("The movie does not exist!");
    }
    moviesList.remove(optionalMovie.get());
    FileHandler.updateFile(moviesList, FILE_PATH);
  }

  @Override
  public Movie updateMovie(Movie movie) {
    Optional<Movie> optionalMovie = getMovie(movie);
    if (optionalMovie.isEmpty()) {
      throw new IllegalArgumentException("The movie does not exist!");
    }
    optionalMovie.get().setTitle(movie.getTitle());
    optionalMovie.get().setReleaseDate(movie.getReleaseDate());
    optionalMovie.get().setBudget(movie.getBudget());
    optionalMovie.get().setCurrency(movie.getCurrency());
    optionalMovie.get().setDescription(movie.getDescription());

    FileHandler.updateFile(moviesList, FILE_PATH);
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

  @Override
  public Optional<Movie> searchMovieById(int id) {
    return moviesList.stream().filter(movie -> movie.getId() == id).findFirst();
  }

  private Optional<Movie> getMovie(Movie movie) {
    return moviesList.stream().filter(aux -> aux.equals(movie)).findFirst();
  }
}
