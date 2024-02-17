package com.imdb.controller;

import com.imdb.appServices.ActorService;
import com.imdb.model.Actor;
import com.imdb.model.Movie;

import java.util.List;
import java.util.Scanner;

public class ActorController {

    private final ActorService actorService;
    private final Scanner scanner;
    private final MovieController movieController;

    public ActorController(ActorService actorService, Scanner scanner, MovieController movieController) {
        this.actorService = actorService;
        this.scanner = scanner;
        this.movieController = movieController;
    }

    public void editActors(Movie selectedMovie) {
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

    private void editActorDetails(Movie selectedMovie) {
        System.out.println("List of Actors:");
        List<Actor> actors = selectedMovie.getActors();
        for (int i = 0; i < actors.size(); i++) {
            System.out.println((i + 1) + " - " + actors.get(i).getName() + " (ID: " + actors.get(i).getId() + ")");
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

        if (actorService.findActorByName(newName) != null && actorService.findActorByNationality(newNationality) != null) {
            System.out.println("Actor with the same name and nationality already exists.");
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
            System.out.println("You cannot remove the only actor of the movie. The movie must have at least one actor.");
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
    }

    public MovieController getMovieController() {
        return movieController;
    }
}