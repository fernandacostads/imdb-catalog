package com.imdb.controller;

import com.imdb.appServices.DirectorService;
import com.imdb.model.Director;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class DirectorController {
  //mesma lógica do actorController

  private final DirectorService directorService;

  public DirectorController() {
    directorService = new DirectorService();
  }

  public void start() {
    Scanner scanner = new Scanner(System.in);
    int choice;

    do {
      System.out.println("1. Create Director");
      System.out.println("2. View Director");
      System.out.println("3. View All Directors");
      System.out.println("4. Update Director");
      System.out.println("5. Delete Director");
      System.out.println("0. Exit");
      System.out.print("Enter your choice: ");

      choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1 -> createDirector(scanner, 1);
        case 2 -> viewDirector(scanner);
        case 3 -> viewAllDirectors();
        case 4 -> updateDirector(scanner);
        case 5 -> deleteDirector(scanner);
        case 0 -> System.out.println("Exiting...");
        default -> System.out.println("Invalid choice. Please try again.");
      }
    } while (choice != 0);
  }

  public void createDirector(Scanner scanner, int qnt) {
    System.out.print("Enter the name of director " + (qnt + 1) + ": ");
    String name = scanner.nextLine();
    Optional<Director> director = directorService.searchDirector(name);

    if (director.isEmpty()) {
      System.out.print("Director not found, add nationality: ");
      String nationality = scanner.nextLine();
      Director newdirector = new Director(name, nationality);
      directorService.addDirector(newdirector);
      System.out.println("successfully");
    }
    System.out.println("Error...");
  }

  private void viewDirector(Scanner scanner) {
    System.out.print("Enter Director Name: ");
    String name = scanner.nextLine();

    Optional<Director> director = directorService.searchDirector(name);

    if (director.isPresent()) {
      System.out.println("Director Details:");
      System.out.println("ID: " + director.get().getId());
      System.out.println("Name: " + director.get().getName());
      System.out.println("Nationality: " + director.get().getNationality());
    } else {
      System.out.println("Director not found.");
    }
  }

  private void viewAllDirectors() {
    List<Director> directors = directorService.getAllDirectors();
    System.out.println("All Directors:");

    for (Director director : directors) {
      System.out.println(
        "ID: " +
        director.getId() +
        ", Name: " +
        director.getName() +
        ", Nationality: " +
        director.getNationality()
      );
    }
  }

  private void updateDirector(Scanner scanner) {
    System.out.print("Enter Director Name: ");
    String name = scanner.nextLine();
    Optional<Director> existingDirector = directorService.searchDirector(name);

    if (existingDirector.isPresent()) {
      System.out.print("Enter New Director Name: ");
      String newName = scanner.nextLine();

      System.out.print("Enter New Director Nationality: ");
      String newNationality = scanner.nextLine();

      Director updatedDirector = new Director(newName, newNationality);
      updatedDirector.setId(existingDirector.get().getId());
      directorService.updateDirector(updatedDirector);
    } else {
      System.out.println("Director not found.");
    }
  }

  private void deleteDirector(Scanner scanner) {
    System.out.print("Enter Director ID: ");
    String name = scanner.nextLine();
    directorService.removeDirector(directorService.searchDirector(name).get());
  }
  /* private final DirectorService directorService;
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
  }*/
}
