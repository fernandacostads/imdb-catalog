package com.imdb.controller;

import com.imdb.dto.ActorDTO;
import com.imdb.dto.DirectorDTO;
import com.imdb.dto.MovieDTO;
import com.imdb.repository.IMovieRepository;
import java.util.Arrays;
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
        System.out.println("Enter the details of the movie:");
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

        // Obtenha os atores e diretores chamando os controladores correspondentes
        List<ActorDTO> actors = actorController.registerActor();
        List<DirectorDTO> directors = Arrays.asList(directorController.registerDirector());

        // Crie um novo MovieDTO com os detalhes fornecidos
        MovieDTO newMovie = new MovieDTO(0, title, releaseDate, budget, currency, description, actors, directors);
        movieRepository.create(newMovie);
    }

    public void updateMovie() {
        System.out.println("Enter the ID of the movie to update:");
        int id = scanner.nextInt();
        scanner.nextLine();
        MovieDTO movieId = new MovieDTO( id,null,0,0,null,null,null,null);
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
            System.out.println("Movie updated successfully.");
        } else {
            System.out.println("Movie not found.");
        }
    }

    public void delete() {
        System.out.println("Enter the ID of the movie to delete:");
        int id = scanner.nextInt();
        MovieDTO movieId = new MovieDTO(id, null, 0, 0, null, null, null, null);
        MovieDTO idMovie = movieRepository.readById(movieId);
        movieRepository.delete(idMovie);
    }

    public void searchMovies() {
        System.out.println("Enter the title keyword to search for:");
        String keyword = scanner.next();
        MovieDTO movieTitle = new MovieDTO( 0,keyword,0,0,null,null,null,null);
        movieRepository.readByName(movieTitle).forEach(System.out::println);
        //Reavaliar a questão do DTO de pesquisa, pois envia uma string ao invés de um DTO
    }
}
