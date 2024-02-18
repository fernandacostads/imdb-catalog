package com.imdb.repository.impl;


import com.imdb.model.Movie;
import com.imdb.repository.IMovieRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieRepository implements IMovieRepository {
  private static MovieRepository instance;
  private static List<Movie> moviesList;
  private MovieRepository() {
    moviesList = new ArrayList<>(10);
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
    updateFile();
  }

  @Override
  public void removeMovie(Movie movie) {
    Optional<Movie> optionalMovie = getMovie(movie);
    if (optionalMovie.isEmpty()) {
      throw new IllegalArgumentException("The movie does not exist!");
    }
    moviesList.remove(optionalMovie.get());
    updateFile();
  }

  @Override
  public Movie updateMovie(Movie movie) {
    Optional<Movie> optionalMovie = getMovie(movie);
    if (optionalMovie.isEmpty()) {
      throw new IllegalArgumentException("The movie does not exist!");
    }
    moviesList.remove(optionalMovie.get());
    moviesList.add(movie);
    updateFile();
    return movie;
  }

  @Override
  public Optional<Movie> searchMovie(String title) {
    return moviesList.stream()
            .filter(aux -> aux.getTitle().equalsIgnoreCase(title))
            .findFirst();
  }

  @Override
  public List<Movie> getAllMovies() {
    return moviesList;
  }

  private static final String FILE_PATH = "src/main/java/com/imdb/resources/movies.txt";
  @Override
  public void updateFile() {
    try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
      outputStream.writeObject(moviesList);
    } catch (IOException e) {
      System.err.println("Error saving to file: " + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  private void loadData() {
    try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
      moviesList = (List<Movie>) inputStream.readObject();
    } catch (IOException | ClassNotFoundException e) {
      System.err.println("Error loading data from file: " + e.getMessage());
    }
  }

  private Optional<Movie> getMovie(Movie movie) {
    return moviesList.stream().filter(aux -> aux.equals(movie)).findFirst();
  }
}
