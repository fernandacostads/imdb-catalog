package com.imdb.controller;

import com.imdb.DTO.ActorDTO;
import com.imdb.DTO.DirectorDTO;
import com.imdb.DTO.MovieDTO;
import com.imdb.repository.IMovieRepository;
import com.imdb.util.exceptions.MovieException;
import com.imdb.util.view.message.MovieMessage;

import java.util.List;
import java.util.Scanner;

/**
 * Controls movie-related operations, acting as the interface between the movie repository
 * and the application's user interface. It handles the creation, updating, deletion,
 * and searching of movie records, coordinating with actor and director sub-controllers
 * to manage related entities.
 */

public class MovieController {
  private final IMovieRepository movieRepository;
  private final ActorController actorController;
  private final DirectorController directorController;
  private final Scanner scanner;

  /**
   * Constructs a MovieController with dependencies on repositories and utilities for input.
   *
   * @param movieRepository    The repository for accessing and persisting movie data.
   * @param actorController    Controller for managing actor-related operations.
   * @param directorController Controller for managing director-related operations.
   * @param scanner            Scanner for reading user input from the console.
   */

  public MovieController(IMovieRepository movieRepository,
                         ActorController actorController,
                         DirectorController directorController,
                         Scanner scanner) {

    this.actorController = actorController;
    this.directorController = directorController;
    this.movieRepository = movieRepository;
    this.scanner = scanner;
  }

  /**
   * Initiates the process to create a new movie entry, collecting information from the user.
   */

  public void createMovie() {
    System.out.println("Creating a new movie.. Please enter all details here: ");
    System.out.print("Title: ");
    String title = scanner.nextLine();
    System.out.print("Release Date: ");
    int releaseDate = scanner.nextInt();
    System.out.print("Budget: ");
    double budget = scanner.nextDouble();
    System.out.print("Currency: ");
    String currency = scanner.next();
    scanner.nextLine();
    System.out.print("Description: ");
    String description = scanner.nextLine();

    List<ActorDTO> actors = actorController.createActor();
    List<DirectorDTO> directors = directorController.createDirector();

    MovieDTO newMovie = new MovieDTO(0, title, releaseDate, budget, currency, description, actors, directors);
    movieRepository.create(newMovie);
    System.out.println(MovieMessage.REGISTERED.get());
  }

  /**
   * Displays the complete list of movies currently stored in the repository.
   */

  public void readListOfMovies() {
    movieRepository.read().forEach(System.out::println);
  }

  /**
   * Facilitates the updating of an existing movie record, allowing modifications to its details.
   */

  public void updateMovie() {
    System.out.println(MovieDTO.formatMovies(movieRepository.read()));
    System.out.println("Enter the ID of the movie to update:");
    int id = scanner.nextInt();
    scanner.nextLine();
    MovieDTO movieId = new MovieDTO.MovieDTOBuilder().id(id).build();
    MovieDTO movieToUpdate = movieRepository.readById(movieId);
    if (movieToUpdate != null) {
      System.out.println("Enter the new title:");
      String title = scanner.nextLine();

      System.out.println("Enter the new release date:");
      int releaseDate = scanner.nextInt();

      System.out.println("Enter the new budget:");
      double budget = scanner.nextDouble();

      scanner.nextLine();
      System.out.println("Enter the new currency:");
      String currency = scanner.nextLine();

      System.out.println("Enter the new description:");
      String description = scanner.nextLine();

      List<ActorDTO> actors = movieToUpdate.actors();
      List<DirectorDTO> directors = movieToUpdate.directors();

      MovieDTO updatedMovie = new MovieDTO(
              movieToUpdate.id(),
              title,
              releaseDate,
              budget,
              currency,
              description,
              actors,
              directors
      );

      movieRepository.update(movieToUpdate, updatedMovie);
      System.out.println(MovieMessage.UPDATED.get());
    } else {
      System.out.println(MovieMessage.LIST_NOT_FOUND.get());
    }
  }

  /**
   * Handles the deletion of a movie record identified by its unique ID.
   */

  public void deleteMovie() {
    System.out.println(MovieDTO.formatMovies(movieRepository.read()));
    System.out.println("Enter the ID of the movie to delete:");
    int id = scanner.nextInt();
    scanner.nextLine();
    MovieDTO movieId = new MovieDTO.MovieDTOBuilder().id(id).build();
    MovieDTO idMovie = movieRepository.readById(movieId);
    movieRepository.delete(idMovie);
    System.out.println(MovieMessage.DELETED.get());
  }

  /**
   * Supports searching for movies by title or specific attributes through user input.
   */

  public void searchMovies() {
    System.out.println("Enter a title keyword or release date to search for movies:");
    String query = scanner.nextLine();

    try {
      MovieDTO movieQuery = new MovieDTO.MovieDTOBuilder().query(query).build();
      List<MovieDTO> movies = movieRepository.search(movieQuery);
      if (movies.isEmpty()) {
        System.out.println(MovieMessage.LIST_NOT_FOUND.get());
      } else {
        movies.forEach(movie -> System.out.println(movie.toString()));
      }
    } catch (MovieException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println(MovieMessage.SEARCH_ERROR.get());
    }
  }

}
