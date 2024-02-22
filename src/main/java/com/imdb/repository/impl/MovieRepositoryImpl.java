package com.imdb.repository.impl;

import com.imdb.dto.ActorDTO;
import com.imdb.dto.DirectorDTO;
import com.imdb.dto.MovieDTO;
import com.imdb.model.Movie;
import com.imdb.repository.IMovieRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieRepositoryImpl implements IMovieRepository {

  private static MovieRepositoryImpl instance;
  private static List<Movie> moviesList;
  private int idGenerator;

  private MovieRepositoryImpl() {
    moviesList = new ArrayList<>(10);
    idGenerator = moviesList.isEmpty() ? 1 : moviesList.getLast().getId() + 1;
  }

  public static synchronized MovieRepositoryImpl getInstance() {
    if (instance == null) {
      instance = new MovieRepositoryImpl();
    }
    return instance;
  }

  @Override
  public void create(MovieDTO entry) {
    Movie newMovie = new Movie(
            idGenerator++, // Gere um novo ID
            entry.title(),
            entry.releaseDate(),
            entry.budget(),
            entry.currency(),
            entry.description(),
            entry.actors().stream()
                    .map(ActorDTO::toActor)
                    .collect(Collectors.toList()),
            entry.directors().stream()
                    .map(DirectorDTO::toDirector)
                    .collect(Collectors.toList())
    );

    // Adicione o novo filme à lista
    moviesList.add(newMovie);
  }

  @Override
  public MovieDTO update(MovieDTO entry, MovieDTO entry2) {
    // Encontre o filme pelo ID
    Movie existingMovie = findMovieById(entry.id());

    if (existingMovie != null) {
      // Atualize os campos relevantes com os dados de entry2
      existingMovie.setTitle(entry2.title());
      existingMovie.setReleaseDate(entry2.releaseDate());
      existingMovie.setBudget(entry2.budget());
      existingMovie.setCurrency(entry2.currency());
      existingMovie.setDescription(entry2.description());
      // ... (atualize outros campos)

      // Não é necessário salvar explicitamente, pois estamos trabalhando com a lista em memória
    }

    return MovieDTO.fromMovie(existingMovie);
  }

  @Override
  public void delete(MovieDTO entry) {
    Movie movieToDelete = findMovieById(entry.id());

    if (movieToDelete != null) {
      // Remova o filme da lista
      moviesList.remove(movieToDelete);
    }
  }

  @Override
  public List<MovieDTO> getAll() {
    // Retorne uma lista de MovieDTOs mapeados a partir dos filmes na lista
    return moviesList.stream()
            .map(MovieDTO::fromMovie)
            .collect(Collectors.toList());
  }

  @Override
  public MovieDTO readById(MovieDTO id) {
    // Encontre o filme pelo ID e retorne como MovieDTO
    Movie movie = findMovieById(id.id());
    return MovieDTO.fromMovie(movie);
  }

  @Override
  //ReadByTitle
  public List<MovieDTO> readByName(MovieDTO entry) {
    // Encontre filmes pelo título e retorne como uma lista de MovieDTOs
    List<Movie> movies = findMoviesByTitle(entry.title());
    return movies.stream()
            .map(MovieDTO::fromMovie)
            .collect(Collectors.toList());
  }

  private Movie findMovieById(int id) {
    return moviesList.stream()
            .filter(movie -> movie.getId() == id)
            .findFirst()
            .orElse(null);
  }

  private List<Movie> findMoviesByTitle(String title) {
    return moviesList.stream()
            .filter(movie -> movie.getTitle().toLowerCase().contains(title.toLowerCase()))
            .collect(Collectors.toList());
  }

  private List<Movie> findMoviesByReleaseData(int releaseDate) {
    return moviesList.stream()
            .filter(movie -> movie.getReleaseDate() == releaseDate)
            .collect(Collectors.toList());
  }
}
