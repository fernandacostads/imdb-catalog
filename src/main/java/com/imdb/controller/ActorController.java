package com.imdb.controller;

import com.imdb.appServices.ActorService;
import com.imdb.model.Actor;
import com.imdb.model.Movie;
import java.util.List;
import java.util.Scanner;

public class ActorController {

  private static ActorService actorService;

  public ActorController(ActorService actorService) {
    this.actorService = actorService;
  }

  public void start() {
    Scanner scanner = new Scanner(System.in);
    int choice;

    do {
      System.out.println("1. Create Actor");
      System.out.println("2. View Actor");
      System.out.println("3. View All Actors");
      System.out.println("4. Update Actor");
      System.out.println("5. Delete Actor");
      System.out.println("0. Exit");
      System.out.print("Enter your choice: ");

      choice = scanner.nextInt();
      scanner.nextLine(); // Consume newline

      switch (choice) {
        case 1 -> createActor(scanner);
        case 2 -> viewActor(scanner);
        case 3 -> viewAllActors();
        case 4 -> updateActor(scanner);
        case 5 -> deleteActor(scanner);
        case 0 -> System.out.println("Exiting...");
        default -> System.out.println("Invalid choice. Please try again.");
      }

    } while (choice != 0);
  }

  public static Actor createActor(Scanner scanner) {
    System.out.print("Enter Actor Name: ");
    String name = scanner.nextLine();

    System.out.print("Enter Actor Nationality: ");
    String nationality = scanner.nextLine();

    Actor actor = new Actor(name, nationality);
    actorService.addActor(actor);
    return actor;
  }

  private void viewActor(Scanner scanner) {
    System.out.print("Enter Actor Name: ");
    String name = scanner.nextLine();

    Actor actor = actorService.searchActor(name);

    if (actor != null) {
      System.out.println("Actor Details:");
      System.out.println("ID: " + actor.getId());
      System.out.println("Name: " + actor.getName());
      System.out.println("Nationality: " + actor.getNationality());
    } else {
      System.out.println("Actor not found.");
    }
  }

  private void viewAllActors() {
    List<Actor> actors = actorService.getAllActors();
    System.out.println("All Actors:");

    for (Actor actor : actors) {
      System.out.println("ID: " + actor.getId() + ", Name: " + actor.getName() + ", Nationality: " + actor.getNationality());
    }
  }

  private void updateActor(Scanner scanner) {
    System.out.print("Enter Actor Name: ");
    String name = scanner.nextLine();
    Actor existingActor = actorService.searchActor(name);

    if (existingActor != null) {
      System.out.print("Enter New Actor Name: ");
      String newName = scanner.nextLine();

      System.out.print("Enter New Actor Nationality: ");
      String newNationality = scanner.nextLine();

      Actor updatedActor = new Actor(newName, newNationality);
      actorService.updateActor(updatedActor);
    } else {
      System.out.println("Actor not found.");
    }
  }

  private void deleteActor(Scanner scanner) {
    System.out.print("Enter Actor ID: ");
    String name = scanner.nextLine();
    actorService.removeActor(name);
  }

/* public void editActors(Movie selectedMovie, Scanner scanner) {
    List<Actor> actors = selectedMovie.getActors();
    if (actors.isEmpty()) {
      System.out.println("No actors available to edit.");
      return;
    } else {
      System.out.println("List of Actors:");
      for (int i = 0; i < actors.size(); i++) {
        System.out.println((i + 1) + " - " + actors.get(i).getName());
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
        editActorDetails(selectedMovie);
        break;
      case 2:
        removeActor(selectedMovie);
        break;
      default:
        System.out.println("Invalid choice. Please enter either 1 or 2.");
    }
  }

  private void editActorDetails(Movie selectedMovie, Scanner scanner) {
    System.out.println("List of Actors:");
    List<Actor> actors = selectedMovie.getActors();
    for (int i = 0; i < actors.size(); i++) {
      System.out.println(
        (i + 1) +
        " - " +
        actors.get(i).getName() +
        " (ID: " +
        actors.get(i).getId() +
        ")"
      );
    }

    System.out.print("Which actor do you want to edit? Enter the ID: ");
    int actorId = scanner.nextInt();
    scanner.nextLine();

    Actor actorToUpdate = actorService.findActorById(actorId);
    if (actorToUpdate == null) {
      System.out.println("Actor with ID " + actorId + " not found.");
      return;
    }

    System.out.print("Add new name: ");
    String newName = scanner.nextLine();
    System.out.print("Add new nationality: ");
    String newNationality = scanner.nextLine();

    if (
      actorService.findActorByName(newName) != null &&
      actorService.findActorByNationality(newNationality) != null
    ) {
      System.out.println(
        "Actor with the same name and nationality already exists."
      );
      return;
    }

    actorToUpdate.setName(newName);
    actorToUpdate.setNationality(newNationality);

    if (actorService.updateActor(actorToUpdate)) {
      System.out.println("Actor updated successfully!");
    } else {
      System.out.println("Failed to update actor.");
    }
  }

  private void removeActor(Movie selectedMovie) {
    if (selectedMovie.getActors().size() == 1) {
      System.out.println(
        "You cannot remove the only actor of the movie. The movie must have at least one actor."
      );
      return;
    }

    System.out.print("Which actor do you want to remove? Enter the ID: ");
    int actorIdToRemove = scanner.nextInt();
    scanner.nextLine();

    if (actorService.deleteActor(actorIdToRemove)) {
      System.out.println("Actor removed successfully!");
    } else {
      System.out.println("Failed to remove actor.");
    }
  }*/
}
