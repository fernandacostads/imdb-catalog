package com.imdb.DTO;

import com.imdb.model.Movie;
import com.imdb.util.view.message.Colors;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Data Transfer Object (DTO) for Movie entities.
 * This class is used to transfer movie data between layers without exposing the domain model.
 */

public record MovieDTO(
        int id,
        String title,
        int releaseDate,
        double budget,
        String currency,
        String description,
        List<ActorDTO> actors,
        List<DirectorDTO> directors
) {

    /**
     * Converts a Movie model to a MovieDTO instance.
     *
     * @param movie The Movie model to convert.
     * @return A MovieDTO instance populated with data from the Movie model.
     */

    public static MovieDTO fromMovie(Movie movie) {
        return new MovieDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getReleaseDate(),
                movie.getBudget(),
                movie.getCurrency(),
                movie.getDescription(),
                movie.getActors().stream()
                        .map(ActorDTO::fromActor)
                        .collect(Collectors.toList()),
                movie.getDirectors().stream()
                        .map(DirectorDTO::fromDirector)
                        .collect(Collectors.toList())
        );
    }

    /**
     * Converts a MovieDTO instance to a Movie model.
     *
     * @param movieDTO The MovieDTO to convert.
     * @return A Movie model populated with data from the MovieDTO.
     */

    public static Movie toMovie(MovieDTO movieDTO) {
        return new Movie(
                movieDTO.id(),
                movieDTO.title(),
                movieDTO.releaseDate(),
                movieDTO.budget(),
                movieDTO.currency(),
                movieDTO.description(),
                movieDTO.actors().stream()
                        .map(ActorDTO::toActor)
                        .collect(Collectors.toList()),
                movieDTO.directors().stream()
                        .map(DirectorDTO::toDirector)
                        .collect(Collectors.toList())
        );
    }

    /**
     * Provides a string representation of the MovieDTO instance.
     *
     * @return A string containing the MovieDTO's details, including actors and directors.
     */

    @Override
    public String toString() {
        return Colors.YELLOW + "\n - Movie's Details - " + Colors.RESET +
                "\nID: " + id +
                "\nTitle: " + title +
                "\nRelease date: " + releaseDate +
                "\nBudget: " + budget +
                "\nCurrency: " + currency +
                "\nDescription: " + description +
                "\nList of actors: " + actors +
                "\nList of directors: " + directors + "\n";
    }
}