package com.imdb.appServices;

import com.imdb.model.Movie;
import com.imdb.repository.impl.MovieRepository;
import java.util.List;
import java.util.Optional;

public class MovieService {

  private final MovieRepository movieRepository;
  //ficaria em gateway

  public MovieService() {
    movieRepository = MovieRepository.getInstance();
  }

  //adiciona um filme
  public void addMovie(Movie movie) {
    movieRepository.addMovie(movie);
  }

  //remove um filme
  public void removeMovie(Movie movie) {
    movieRepository.removeMovie(movie);
  }

  //atualiza um filme
  public Movie updateMovie(Movie movie) {
    return movieRepository.updateMovie(movie);
  }

  //pesquisa um filme pelo titulo
  public Optional<Movie> searchMovie(String Title) {
    return movieRepository.searchMovie(Title);
  }

  //pesquisa um filme pelo id
  public Optional<Movie> searchMovieById(int id) {
    return getAllMovies()
      .stream()
      .filter(movie -> movie.getId() == id)
      .findFirst();
  }

  //pega todos os filmes
  public List<Movie> getAllMovies() {
    return movieRepository.getAllMovies();
  }
}
