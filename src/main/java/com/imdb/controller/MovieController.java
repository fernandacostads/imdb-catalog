package com.imdb.controller;

import com.imdb.DTO.ActorDTO;
import com.imdb.DTO.DirectorDTO;
import com.imdb.DTO.MovieDTO;
import com.imdb.repository.IMovieRepository;
import com.imdb.util.view.message.MovieMessage;
import java.util.List;
import java.util.Scanner;

public class MovieController {
    private final IMovieRepository movieRepository;
    private final ActorController actorController;
    private final DirectorController directorController;
    private final Scanner scanner;

    public MovieController(IMovieRepository movieRepository,
                           ActorController actorController,
                           DirectorController directorController,
                           Scanner scanner) {

        this.actorController = actorController;
        this.directorController = directorController;
        this.movieRepository = movieRepository;
        this.scanner = scanner;
    }

    public void showListOfMovies() {
        movieRepository.getAll().forEach(System.out::println);
    }

    public void registerMovie() {
        System.out.println("Creating a new movie.. Please enter all details here: ");
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Release Date: ");
        int releaseDate = scanner.nextInt();
        System.out.print("Budget: ");
        double budget = scanner.nextDouble();
        System.out.print("Currency: ");
        String currency = scanner.next();
        scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();

        List<ActorDTO> actors = actorController.registerActor();
        List<DirectorDTO> directors = directorController.registerDirector();

        MovieDTO newMovie = new MovieDTO(0, title, releaseDate, budget, currency, description, actors, directors);
        movieRepository.create(newMovie);
        System.out.println(MovieMessage.REGISTERED.get());
    }

    public void updateMovie() {
        System.out.println("Enter the ID of the movie to update:");
        int id = scanner.nextInt();
        scanner.nextLine();
        MovieDTO movieId = new MovieDTO(id, null, 0, 0, null, null, null, null);
        MovieDTO movieToUpdate = movieRepository.readById(movieId);
        if (movieToUpdate != null) {
            System.out.println("Enter the new title:");
            String title = scanner.nextLine();

            System.out.println("Enter the new release date:");
            int releaseDate = scanner.nextInt();

            System.out.println("Enter the new budget:");
            double budget = scanner.nextDouble();

            scanner.nextLine();
            System.out.println("Enter the new currency:");
            String currency = scanner.nextLine();

            System.out.println("Enter the new description:");
            String description = scanner.nextLine();

            List<ActorDTO> actors = movieToUpdate.actors();
            List<DirectorDTO> directors = movieToUpdate.directors();

            MovieDTO updatedMovie = new MovieDTO(
                    movieToUpdate.id(),
                    title,
                    releaseDate,
                    budget,
                    currency,
                    description,
                    actors,
                    directors
            );

            movieRepository.update(movieToUpdate, updatedMovie);
            System.out.println(MovieMessage.UPDATED.get());
        } else {
            System.out.println(MovieMessage.LIST_NOT_FOUND.get());
        }
    }

    public void deleteMovie() {
        System.out.println("Enter the ID of the movie to delete:");
        int id = scanner.nextInt();
        MovieDTO movieId = new MovieDTO(id, null, 0, 0, null, null, null, null);
        MovieDTO idMovie = movieRepository.readById(movieId);
        movieRepository.delete(idMovie);
        System.out.println(MovieMessage.DELETED.get());
    }

    public void searchMovies() {
        System.out.println("Enter the title keyword to search for a movie:");
        String keyword = scanner.next();
        MovieDTO movieTitle = new MovieDTO(0, keyword, 0, 0, null, null, null, null);
        movieRepository.readByName(movieTitle).forEach(System.out::println);
    }
}
