package com.imdb.util.view;

import com.imdb.controller.ActorController;
import com.imdb.controller.DirectorController;
import com.imdb.controller.MovieController;
import com.imdb.repository.IActorRepository;
import com.imdb.repository.IDirectorRepository;
import com.imdb.repository.IMovieRepository;
import com.imdb.repository.impl.ActorRepositoryImpl;
import com.imdb.repository.impl.DirectorRepositoryImpl;
import com.imdb.repository.impl.MovieRepositoryImpl;
import com.imdb.util.view.message.MenuMessage;

import java.util.Scanner;

public class Menu {
    IActorRepository actorRepository = ActorRepositoryImpl.getInstance();
    IDirectorRepository directorRepository = DirectorRepositoryImpl.getInstance();
    IMovieRepository movieRepository = MovieRepositoryImpl.getInstance();
    Scanner scanner = new Scanner(System.in);

    ActorController actorController = new ActorController(
            actorRepository,
            scanner
    );
    DirectorController directorController = new DirectorController(
            directorRepository,
            scanner
    );
    MovieController movieController = new MovieController(
            movieRepository,
            actorController,
            directorController,
            scanner
    );

    public void displayMainMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println(MenuMessage.MAIN_MENU.get());

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> displayLists();
                case 2 -> movieController.registerMovie();
                case 3 -> movieController.updateMovie();
                case 4 -> movieController.deleteMovie();
                case 5 -> movieController.searchMovies();
                case 0 -> {
                    exit = true;
                    System.out.println("Exiting program...");
                }
                default -> System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
        scanner.close();
    }

    public void displayLists() {
        {
            while (true) {
                System.out.println(MenuMessage.LIST_MENU.get());
                int op = scanner.nextInt();
                scanner.nextLine();
                switch (op) {
                    case 1 -> movieController.showListOfMovies();
                    case 2 -> actorController.showListOfActors();
                    case 3 -> directorController.showListOfDirectors();
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            }
        }
    }
}

