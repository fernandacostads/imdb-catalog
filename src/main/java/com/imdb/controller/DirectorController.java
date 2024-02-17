package com.imdb.controller;

import com.imdb.appServices.DirectorService;
import com.imdb.model.Director;
import com.imdb.model.Movie;
import java.util.List;
import java.util.Scanner;

public class DirectorController {

  private final DirectorService directorService;
  private final Scanner scanner;
  private final MovieController movieController;

  public DirectorController(
    DirectorService directorService,
    Scanner scanner,
    MovieController movieController
  ) {
    this.directorService = directorService;
    this.scanner = scanner;
    this.movieController = movieController;
  }

  public void editDirectors(Movie selectedMovie) {
    List<Director> directors = selectedMovie.getDirectors();
    if (directors.isEmpty()) {
      System.out.println("No directors available to edit.");
      return;
    } else {
      System.out.println("List of Directors:");
      for (int i = 0; i < directors.size(); i++) {
        System.out.println((i + 1) + " - " + directors.get(i).getName());
      }
    }

    System.out.println("Do you want to edit or remove?");
    System.out.println("1 - Edit");
    System.out.println("2 - Remove");
    System.out.print("Enter your choice: ");
    int choice = scanner.nextInt();
    scanner.nextLine();

    switch (choice) {
      case 1:
        editDirectorDetails(selectedMovie);
        break;
      case 2:
        removeDirector(selectedMovie);
        break;
      default:
        System.out.println("Invalid choice. Please enter either 1 or 2.");
    }
  }

  private void editDirectorDetails(Movie selectedMovie) {
    System.out.println("List of Directors:");
    List<Director> directors = selectedMovie.getDirectors();
    for (int i = 0; i < directors.size(); i++) {
      System.out.println(
        (i + 1) +
        " - " +
        directors.get(i).getName() +
        " (ID: " +
        directors.get(i).getId() +
        ")"
      );
    }

    System.out.print("Which director do you want to edit? Enter the ID: ");
    int directorId = scanner.nextInt();
    scanner.nextLine();

    Director directorToUpdate = directorService.findDirectorById(directorId);
    if (directorToUpdate == null) {
      System.out.println("Director with ID " + directorId + " not found.");
      return;
    }

    System.out.print("Add new name: ");
    String newName = scanner.nextLine();
    System.out.print("Add new nationality: ");
    String newNationality = scanner.nextLine();

    if (
      directorService.findDirectorByName(newName) != null &&
      directorService.findDirectorByNationality(newNationality) != null
    ) {
      System.out.println(
        "Director with the same name and nationality already exists."
      );
      return;
    }

    directorToUpdate.setName(newName);
    directorToUpdate.setNationality(newNationality);

    if (directorService.updateDirector(directorToUpdate)) {
      System.out.println("Director updated successfully!");
    } else {
      System.out.println("Failed to update director.");
    }
  }

  private void removeDirector(Movie selectedMovie) {
    if (selectedMovie.getDirectors().size() == 1) {
      System.out.println(
        "You cannot remove the only director of the movie. The movie must have at least one director."
      );
      return;
    }

    System.out.print("Which director do you want to remove? Enter the ID: ");
    int directorIdToRemove = scanner.nextInt();
    scanner.nextLine();

    if (directorService.deleteDirector(directorIdToRemove)) {
      System.out.println("Director removed successfully!");
    } else {
      System.out.println("Failed to remove director.");
    }
  }

  public MovieController getMovieController() {
    return movieController;
  }
}
