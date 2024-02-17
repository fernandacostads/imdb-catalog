package com.imdb.repository.impl;


import com.imdb.model.Movie;
import com.imdb.repository.IMovieRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository implements IMovieRepository {

  private final List<Movie> moviesList;
  private static final String FILE_PATH = "src/main/resources/movies.txt";
  private int idGenerator = 1;

  public MovieRepository() {
    this.moviesList = loadData();
  }


  @Override
  public void addMovie(Movie movie) {
    movie.setId(idGenerator++);
    moviesList.add(movie);
    updateFile();
    System.out.println("Movie saved successfully!");
  }

  @Override
  public Movie search(int id) {
    for (Movie filme : moviesList) {
      if (filme.getId() == id) {
        return filme;
      }
    }
    return null; // Filme não encontrado
  }

  @Override
  public List<Movie> getAllMovies() {
    return new ArrayList<>(moviesList);
  }

  @Override
  public void updateMovie(Movie movie) {
    for (int i = 0; i < moviesList.size(); i++) {
      if (moviesList.get(i).getId() == movie.getId()) {
        moviesList.set(i, movie);
        updateFile();
        System.out.println("Movie updated successfully.");
        return;
      }
    }
    System.out.println("Movie not found for update.");
  }

  @Override
  public void removeMovie(int id) {
    moviesList.removeIf(movie -> movie.getId() == id);
    updateFile();
    System.out.println("Movie deleted successfully.");
  }

  @Override
  public void updateFile() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
      oos.writeObject(moviesList);
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Error saving to file.");
    }
  }

  @Override
  public List<Movie> loadData() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
      return (List<Movie>) ois.readObject();
    } catch (IOException | ClassNotFoundException | ClassCastException e) {
      // Se ocorrer uma exceção, cria uma nova lista
      return new ArrayList<>();
    }
  }
}