//package com.imdb.util;
//
//import com.imdb.controller.MovieController;
//import com.imdb.repository.IActorRepository;
//import com.imdb.repository.IDirectorRepository;
//import com.imdb.repository.IMovieRepository;
//import com.imdb.repository.impl.ActorRepositoryImpl;
//import com.imdb.repository.impl.DirectorRepositoryImpl;
//import com.imdb.repository.impl.MovieRepositoryImpl;
//
//import java.util.Scanner;
//
//public class Menu {
//    IActorRepository actorRepository = ActorRepositoryImpl.getInstance();
//    IDirectorRepository directorRepository = DirectorRepositoryImpl.getInstance();
//    IMovieRepository movieRepository = MovieRepositoryImpl.getInstance();
//
//    MovieController movieController = new MovieController(
//            movieRepository
//    );
//    private Scanner scanner = new Scanner(System.in);
//
//    public void displayMainMenu() {
//        boolean exit = false;
//        while (!exit) {
//            System.out.println("\nMain Menu - CRUD (Create, Read, Update, Delete) + Search");
//            System.out.println("1 - Show list of movies");
//            System.out.println("2 - Register a new movie");
//            System.out.println("3 - Edit movie");
//            System.out.println("4 - Delete movie");
//            System.out.println("5 - Search");
//            System.out.println("6 - Close the program");
//            System.out.print("Please enter your choice: ");
//
//            int choice = scanner.nextInt();
//            switch (choice) {
//                case 1:
//                    movieController.showListOfMovies();
//                    break;
//                case 2:
//                    movieController.registerMovie();
//                    break;
//                case 3:
//                    movieController.updateMovie();
//                    break;
//                case 4:
//                    movieController.delete();
//                    break;
//                case 5:
//                    movieController.searchMovies();
//                    break;
//                case 6:
//                    exit = true;
//                    System.out.println("Exiting program...");
//                    break;
//                default:
//                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
//            }
//        }
//    }
//}
//
