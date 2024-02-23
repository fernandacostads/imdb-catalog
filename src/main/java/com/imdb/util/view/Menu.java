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

/**
 * This class represents the main menu of the IMDb application, providing a user interface
 * for navigating through the application's functionalities. It allows users to interact
 * with the system by performing operations such as listing, creating, updating, and deleting
 * movies, actors, and directors, as well as conducting searches within these categories.
 */

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

  /**
   * Displays the main menu to the user and processes the user's choice to navigate
   * to various parts of the application. It loops continuously until the user chooses
   * to exit the application.
   */

  public void displayMainMenu() {
    String choice;
    while (true) {
      System.out.println(MenuMessage.MAIN_MENU.get());
      choice = scanner.nextLine();

      switch (choice) {
        case "1" -> movieController.createMovie();
        case "2" -> readDisplay();
        case "3" -> movieController.updateMovie();
        case "4" -> movieController.deleteMovie();
        case "5" -> searchDisplay();
        case "0" -> {
          System.out.println(MenuMessage.EXITING_PROGRAM.get());
          return;
        }
        default -> System.out.println(MenuMessage.INVALID_CHOICE_MAIN.get());
      }
    }
  }

  /**
   * Displays a submenu for listing entities such as movies, actors, and directors.
   * The user can choose which category of entities to list. This method continues
   * to display the list menu and process choices until the user decides to exit.
   */

  public void readDisplay() {
    String choice;
    while (true) {
      System.out.println(MenuMessage.LIST_MENU.get());
      choice = scanner.nextLine();
      switch (choice) {
        case "1" -> movieController.readListOfMovies();
        case "2" -> actorController.readListOfActors();
        case "3" -> directorController.readListOfDirectors();
        case "0" -> {
          System.out.println(MenuMessage.EXITING_PROGRAM.get());
          return;
        }
        default -> System.out.println(MenuMessage.INVALID_CHOICE_MAIN.get());
      }
    }
  }

  /**
   * Displays a submenu for conducting searches within the application. Users can search
   * for movies, actors, or directors based on specified criteria. This method continues
   * to display the search menu and process choices until the user decides to exit.
   */

  public void searchDisplay() {
    String choice;
    while (true) {
      System.out.println(MenuMessage.SEARCH_MENU.get());
      choice = scanner.nextLine();
      switch (choice) {
        case "1" -> movieController.searchMovies();
        case "2" -> actorController.searchActors();
        case "3" -> directorController.searchDirectors();
        case "0" -> {
          System.out.println(MenuMessage.EXITING_PROGRAM.get());
          return;
        }
        default -> System.out.println(MenuMessage.INVALID_CHOICE_LIST.get());
      }
    }
  }
}

