package com.imdb.dto;

import com.imdb.model.Movie;

import java.util.List;
import java.util.stream.Collectors;

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
}