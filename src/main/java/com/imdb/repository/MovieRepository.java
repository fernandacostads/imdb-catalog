package com.imdb.repository;

import com.imdb.model.Movie;
import java.util.*;
import java.util.stream.Collectors;

public class MovieRepository {

  private static final Map<Integer, Movie> movieMap = new HashMap<>(); // Mapa para armazenar os filmes por ID
  private static int nextId = 1; // Contador para gerar IDs

  public static void addMovie(Movie movie) {
    movie.setId(nextId); // Atribua o próximo ID ao filme
    movieMap.put(nextId++, movie); // Adicione o filme ao mapa
  }

  public static void editMovie(int id, Movie updatedMovie) {
    movieMap.put(id, updatedMovie); // Atualize o filme no mapa
  }

  public static void removeMovie(int id) {
    movieMap.remove(id); // Remova o filme do mapa
  }

  public static List<Movie> getAllMovies() {
    return new ArrayList<>(movieMap.values()); // Retorne todos os filmes
  }

  public static Optional<Movie> searchMovieById(int id) {
    return Optional.ofNullable(movieMap.get(id)); //Retorna o filme pesquisado pelo ID
  }

  public static Optional<List<Movie>> searchMovieByName(String search) {
    List<Movie> collect = movieMap
      .values()
      .stream()
      .filter(movie ->
        movie.getTitle().toLowerCase().contains(search.toLowerCase())
      )//Verifica se a string de busca está presente em algum título de filme.
      .collect(Collectors.toList());

    return Optional.ofNullable(collect.isEmpty() ? null : collect);
  }

  public static Optional<List<Movie>> searchMovieByGenre(String genre) {
    List<Movie> collect = movieMap
      .values()
      .stream()
      .filter(movie ->
        movie
          .getGenres()
          .stream()
          .map(Enum::name)
          .anyMatch(genre::equalsIgnoreCase)
      ) // Verifica se há algum gênero que corresponde à entrada, ignorando maiúsculas e minúsculas
      .collect(Collectors.toList());
    return Optional.ofNullable(collect.isEmpty() ? null : collect);
  }
}
