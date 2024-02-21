package com.imdb.repository;

import com.imdb.dto.ShowMovieDTO;
import com.imdb.model.Movie;
import com.imdb.dto.MovieDTO;

import java.util.List;
import java.util.Optional;

public interface IMovieRepository {
  boolean addMovie(MovieDTO movie);

  void removeMovie(Movie movie);

  Movie updateMovie(Movie movie);

  Optional<Movie> searchMovie(String title);

  List<ShowMovieDTO> getAllMovies();
  String printAllMovies();
  String detailsMovie(MovieDTO movieDTO);

  Optional<MovieDTO> searchMovieById(int id);
}
