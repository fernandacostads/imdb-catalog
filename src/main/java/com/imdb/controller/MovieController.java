package com.imdb.controller;

import com.imdb.DTO.ActorDTO;
import com.imdb.DTO.DirectorDTO;
import com.imdb.DTO.MovieDTO;
import com.imdb.repository.IMovieRepository;
import com.imdb.util.exceptions.MovieException;
import com.imdb.util.view.message.MovieMessage;

import java.util.ArrayList;
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

  public MovieController(IMovieRepository movieRepository, ActorController actorController, DirectorController directorController, Scanner scanner) {
    this.movieRepository = movieRepository;
    this.actorController = actorController;
    this.directorController = directorController;
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
    System.out.println("Available movies for update:");
    movieRepository.read().forEach(movie -> System.out.println(movie.id() + ": " + movie.title()));

    System.out.print("Enter the ID of the movie you want to update: ");
    int movieId = scanner.nextInt();
    scanner.nextLine();

    MovieDTO selectedMovie = movieRepository.readById(new MovieDTO.MovieDTOBuilder().id(movieId).build());
    if (selectedMovie == null) {
      System.out.println("Movie not found.");
      return;
    }

    System.out.println("You are updating: " + selectedMovie.title());

    System.out.println("Updating movie details...");

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

    System.out.println("Do you want to update actors? (yes/no)");

    if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
      updateActorsInMovie(selectedMovie);
    }

    System.out.println("Do you want to update directors? (yes/no)");

    if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
      updateDirectorsInMovie(selectedMovie);
    }

    MovieDTO updatedMovie = new MovieDTO.MovieDTOBuilder()
            .title(title)
            .releaseDate(releaseDate).build();


    movieRepository.update(selectedMovie,updatedMovie);
    System.out.println("Movie updated successfully.");
  }

  private void updateActorsInMovie(MovieDTO movie) {
    System.out.println("Current actors in the movie:");
    movie.actors().forEach(actor -> System.out.println(actor.id() + " - " + actor.name()));

    System.out.println("Do you want to add or remove actors? (add/remove)");
    String action = scanner.nextLine().trim();

    switch (action) {
      case "add":
        List<ActorDTO> addedActors = actorController.createActor();
        movie.actors().addAll(addedActors);
        break;
      case "remove":
        System.out.println("Enter actor IDs to remove (comma separated):");
        String[] idsToRemove = scanner.nextLine().split(",");
        for (String idStr : idsToRemove) {
          int idToRemove = Integer.parseInt(idStr.trim());
          movie.actors().removeIf(actor -> actor.id() == idToRemove);
        }
        break;
    }
  }

  private List<DirectorDTO> updateDirectorsInMovie(MovieDTO movie) {
    System.out.println("Current directors in the movie:");
    movie.directors().forEach(director -> System.out.println(director.id() + " - " + director.name()));

    System.out.println("Do you want to add or remove directors? (add/remove)");
    String action = scanner.nextLine().trim();

    switch (action) {
      case "add":
        List<DirectorDTO> addedDirectors = directorController.createDirector();
        movie.directors().addAll(addedDirectors);
        break;
      case "remove":
        System.out.println("Enter director IDs to remove:");
        String[] idsToRemove = scanner.nextLine().split(",");
        for (String idStr : idsToRemove) {
          int idToRemove = Integer.parseInt(idStr.trim());
          movie.directors().removeIf(director -> director.id() == idToRemove);
        }
        break;
    }
    return new ArrayList<>(movie.directors());
  }


/**
 * Handles the deletion of a movie record identified by its unique ID.
 */

  public void deleteMovie() {
    System.out.println("Available movies for deletion:");
    movieRepository.read().forEach(movie -> System.out.println(movie.id() + ": " + movie.title()));

    System.out.print("Enter the ID of the movie you want to delete: ");
    int movieId = scanner.nextInt();
    scanner.nextLine();

    MovieDTO movieToDelete = movieRepository.readById(new MovieDTO.MovieDTOBuilder().id(movieId).build());
    if (movieToDelete == null) {
      System.out.println("Movie not found.");
      return;
    }

    movieRepository.delete(movieToDelete);
    System.out.println("Movie deleted successfully.");
  }

/**
 * Supports searching for movies by title or specific attributes through user input.
 */

    public void searchMovies () {
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