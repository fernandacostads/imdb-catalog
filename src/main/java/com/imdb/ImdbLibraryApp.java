package com.imdb;

import com.imdb.appServices.ActorService;
import com.imdb.appServices.DirectorService;
import com.imdb.appServices.MovieService;
import com.imdb.appServices.ValidationService;
import com.imdb.controller.MovieController;
import com.imdb.controller.TesteController;
import com.imdb.repository.ActorRepository;
import com.imdb.repository.DirectorRepository;
import com.imdb.repository.MovieRepository;

import java.io.IOException;
import java.util.Scanner;

public class ImdbLibraryApp {
  public static void main(String[] args) throws IOException {
    ActorRepository actorRepository = new ActorRepository();
    DirectorRepository directorRepository = new DirectorRepository();
    ActorService actorService = new ActorService(actorRepository);
    DirectorService directorService = new DirectorService(directorRepository);
    MovieController movieController = new MovieController(new MovieService(), actorService, directorService, new Scanner(System.in));

    MovieRepository.loadData();
    int choice;

    try (Scanner scanner = new Scanner(System.in)) {
      do {
        System.out.println("Main Menu - CRUD (Create, Read, Update, Delete) + Search");
        System.out.println("1 - Show list of movies");
        System.out.println("2 - Register a new movie");
        System.out.println("3 - Edit movie");
        System.out.println("4 - Delete movie");
        System.out.println("5 - Search");
        System.out.println("6 - Close the program");
        System.out.print("Enter your choice: ");

        choice = ValidationService.isValidOption(scanner, 6, 1);

        switch (choice) {
          case 1 -> movieController.showListOfMovies();
          case 2 -> movieController.registerNewMovie();
          case 3 -> movieController.editMovie();
          case 4 -> movieController.deleteMovie();
          case 5 -> movieController.searchMovie();
          case 6 -> System.out.println("Closing the program...");
          default -> System.out.println("Invalid choice. Please enter a number between 1 and 6.");
        }
      } while (choice != 6);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
