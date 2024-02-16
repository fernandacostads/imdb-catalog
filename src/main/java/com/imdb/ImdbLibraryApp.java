package com.imdb;

import com.imdb.appServices.ValidationService;
import com.imdb.controller.MovieController;
import com.imdb.controller.TesteController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ImdbLibraryApp {
  public static void main(String[] args) {
    TesteController.teste(); //inicia 10 movies pra teste

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

        int choice = ValidationService.isValidOption(scanner, 6, 1);

        switch (choice) {
          case 1:
            MovieController.showListOfMovies();
            break;
          case 2:
            MovieController.registerNewMovie(scanner);
            break;
          case 3:
            MovieController.editMovie(scanner);
            break;
          case 4:
            MovieController.deleteMovie(scanner);
            break;
          case 5:
            MovieController.searchMovie(scanner);
            break;
          case 6:
            System.out.println("Closing the program...");
            scanner.close();
            System.exit(0);
            break;
           }
      }
    } catch (InputMismatchException e) {
      System.out.println("Invalid input. Please enter a number.");
    }
  }
}
