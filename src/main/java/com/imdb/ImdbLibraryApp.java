package com.imdb;

import com.imdb.appServices.ActorService;
import com.imdb.appServices.DirectorService;
import com.imdb.appServices.MovieService;
import com.imdb.controller.MovieController;
import com.imdb.appServices.ValidationService;
import com.imdb.repository.ActorRepository;
import com.imdb.repository.DirectorRepository;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ImdbLibraryApp {
    public static void main(String[] args) {
        ActorRepository actorRepository = new ActorRepository();
        DirectorRepository directorRepository = new DirectorRepository();

        ActorService actorService = new ActorService(actorRepository);
        DirectorService directorService = new DirectorService(directorRepository);
        MovieController movieController = new MovieController(new MovieService(), actorService, directorService, new Scanner(System.in));

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Main Menu - CRUD (Create, Read, Update, Delete) + Search");
                System.out.println("1 - Show list of movies");
                System.out.println("2 - Register a new movie");
                System.out.println("3 - Edit movie");
                System.out.println("4 - Delete movie");
                System.out.println("5 - Search");
                System.out.println("6 - Close the program");
                System.out.print("Enter your choice: ");

                int choice;
                try {
                    choice = ValidationService.isValidOption(scanner, 6, 1);
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();
                    continue;
                }

                switch (choice) {
                    case 1:
                        movieController.showListOfMovies();
                        break;
                    case 2:
                        movieController.registerNewMovie();
                        break;
                    case 3:
                        movieController.editMovie();
                        break;
                    case 4:
                        movieController.deleteMovie();
                        break;
                    case 5:
                        movieController.searchMovie();
                        break;
                    case 6:
                        System.out.println("Closing the program...");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                        break;
                }
            }
        }
    }
}
