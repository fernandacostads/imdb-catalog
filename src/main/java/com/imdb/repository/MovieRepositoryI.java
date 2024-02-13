package com.imdb.repository;

import com.imdb.model.Movie;
import java.util.List;

public interface MovieRepositoryI {
  Movie create(Movie movie);

  List<Movie> readByName(String name);

  Movie update(Movie movie);

  void delete(Movie movie);
}
