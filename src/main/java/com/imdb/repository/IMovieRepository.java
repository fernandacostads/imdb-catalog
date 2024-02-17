package com.imdb.repository;

import com.imdb.model.Movie;
import java.util.List;

public interface IMovieRepository {
  void addMovie(Movie movie);
  Movie search(int id);
  List<Movie> getAllMovies();
  void updateMovie(Movie movie);
  void removeMovie(int id);
  void updateFile();
  List<Movie> loadData();
}
