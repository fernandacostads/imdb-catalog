package com.imdb.repository.impl;

import com.imdb.DTO.ActorDTO;
import com.imdb.DTO.DirectorDTO;
import com.imdb.DTO.MovieDTO;
import com.imdb.model.Movie;
import com.imdb.repository.IMovieRepository;
import com.imdb.util.exceptions.MovieException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A repository implementation for managing movies within the application,
 * utilizing a file-based storage system. It supports creating, updating, deleting,
 * and querying movie records, ensuring data persistence across application sessions.
 */

public class MovieRepositoryImpl implements IMovieRepository {
  private static final String FILE_PATH =
          "src/main/java/com/imdb/util/resources/movies.txt";
  private static MovieRepositoryImpl instance;
  private static List<Movie> moviesList;
  private int idGenerator;

  /**
   * Initializes the movie list from the file and sets up the ID generator.
   * This constructor is private to enforce the Singleton pattern.
   */

  private MovieRepositoryImpl() {
    moviesList = new ArrayList<>(10);
    //moviesList = FileHandler.readMoviesFromFile(FILE_PATH);
    idGenerator = moviesList.isEmpty() ? 1 : moviesList.getLast().getId() + 1;
  }

  /**
   * Accesses the Singleton instance of the MovieRepositoryImpl. Creates the instance
   * if it does not exist.
   *
   * @return The single, shared instance of the MovieRepositoryImpl.
   */

  public static synchronized MovieRepositoryImpl getInstance() {
    if (instance == null) {
      instance = new MovieRepositoryImpl();
    }
    return instance;
  }

  /**
   * Creates and persists a new movie record from the provided MovieDTO data.
   *
   * @param entry The MovieDTO containing information for the new movie.
   * @return A new MovieDTO with an assigned ID, reflecting the created movie.
   */

  @Override
  public MovieDTO create(MovieDTO entry) {
    if (moviesList.stream().anyMatch(movie -> movie.getTitle().equalsIgnoreCase(entry.title()))) {
      throw new MovieException.MovieAlreadyExists(entry.title());
    } else if (entry.actors().isEmpty()) {
      throw new IllegalArgumentException("The movie needs to have at least one actor");
    } else if (entry.directors().isEmpty()) {
      throw new IllegalArgumentException("The movie needs to have at least one director");
    } else {
      Movie newMovie = new Movie(
              idGenerator++,
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

      moviesList.add(newMovie);
      //  FileHandler.updateFile(moviesList, FILE_PATH);
      return MovieDTO.fromMovie(newMovie);
    }
  }

  /**
   * Retrieves all existing movie records as a list of MovieDTOs.
   *
   * @return A list of MovieDTOs representing all stored movies.
   */

  @Override
  public List<MovieDTO> read() {
    List<MovieDTO> movieDTOList = moviesList.stream()
            .map(MovieDTO::fromMovie)
            .collect(Collectors.toList());
    checkEmptyListException(movieDTOList);
    return movieDTOList;
  }

  /**
   * Updates an existing movie record with new information.
   *
   * @param entry  The original MovieDTO.
   * @param entry2 The MovieDTO containing updated information.
   * @return The updated MovieDTO.
   */

  @Override
  public MovieDTO update(MovieDTO entry, MovieDTO entry2) {
    Movie existingMovie = foundMovieId(entry.id());

      existingMovie.setTitle(entry2.title());
      existingMovie.setReleaseDate(entry2.releaseDate());
      existingMovie.setBudget(entry2.budget());
      existingMovie.setCurrency(entry2.currency());
      existingMovie.setDescription(entry2.description());

      //  FileHandler.updateFile(moviesList, FILE_PATH);
      return MovieDTO.fromMovie(existingMovie);
  }

  /**
   * Deletes a movie record based on the provided ID.
   *
   * @param entry The MovieDTO of the movie to delete.
   */

  @Override
  public void delete(MovieDTO entry) {
    Movie movieToDelete = foundMovieId(entry.id());
      moviesList.remove(movieToDelete);
      //FileHandler.updateFile(moviesList, FILE_PATH);
  }

  /**
   * Retrieves a list of movies matching the given title.
   */

  @Override
  public List<MovieDTO> search(MovieDTO movie) {
    try {
      int date = Integer.parseInt(movie.currency());
      return searchByReleaseDate(date);
    } catch (NumberFormatException e) {
      return searchMovieTitle(movie.currency()).stream()
              .map(MovieDTO::fromMovie)
              .collect(Collectors.toList());
    }
  }

  /**
   * Finds and returns a movie by its ID.
   *
   * @param id The MovieDTO containing the ID of the movie to find.
   * @return The found MovieDTO.
   */

  @Override
  public MovieDTO readById(MovieDTO id) {
    Movie movie = foundMovieId(id.id());
    if (movie == null) {
      throw new MovieException.MovieNotFoundException();
    }
    return MovieDTO.fromMovie(movie);
  }

  /**
   * Finds a movie by its ID.
   *
   * @param id The ID of the movie to find.
   * @return The found Movie, or null if no movie is found.
   */

  private Movie foundMovieId(int id) {
    Movie movie1 = moviesList.stream()
            .filter(movie -> movie.getId() == id)
            .findFirst()
            .orElse(null);
    checkMovieNotFoundException(movie1);
    return movie1;
    }

  /**
   * Finds movies by their title.
   *
   * @param title The title or partial title to search for.
   * @return A list of Movies matching the search criteria.
   */

  private List<Movie> searchMovieTitle(String title) {
    return moviesList.stream()
            .filter(movie -> movie.getTitle().toLowerCase().contains(title.toLowerCase()))
            .collect(Collectors.toList());
  }

  public List<MovieDTO> searchByReleaseDate(int releaseDate) {
    List<Movie> filteredMovies = moviesList.stream()
            .filter(movie -> movie.getReleaseDate() == releaseDate)
            .toList();
    if (filteredMovies.isEmpty()) {
      throw new MovieException("No movies found for the date: " + releaseDate);
    }
    return filteredMovies.stream().map(MovieDTO::fromMovie).collect(Collectors.toList());
  }

  private void checkEmptyListException(List<MovieDTO> list) {
      if (list.isEmpty()) {
        throw new MovieException.MovieListIsEmpty();
      }
  }

  /**
   * Checks if a movie was not found and throws a {@link MovieException.MovieNotFoundException} if it wasn't.
   * @param movie The movie to check.
   * @throws MovieException.MovieNotFoundException if the movie is null.
   */
  private void checkMovieNotFoundException(Movie movie)  {
    if (movie == null) {
      throw new MovieException.MovieNotFoundException();
    }
  }
}

