package com.imdb;

import com.imdb.util.ValidationService;
import com.imdb.controller.MovieController;
import com.imdb.util.TesteController;

import java.util.Scanner;

public class IMDBApplication {

    public static void main(String[] args) {
        MovieController movieController = new MovieController();
       // TesteController testeController = new TesteController();
        //testeController.teste();
        try (var scanner = new Scanner(System.in)) {
            int choice;

            do {
                System.out.println(
                        "IMDBApplication Menu - CRUD (Create, Read, Update, Delete) + Search"
                );
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
                    default -> System.out.println(
                            "Invalid choice. Please enter a number between 1 and 6."
                    );
                }
            } while (choice != 6);
        }
    }
}
