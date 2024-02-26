package com.imdb.DTO;

import com.imdb.model.Movie;
import com.imdb.util.view.message.Colors;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a data transfer object (DTO) for Movie entities.
 * This class is designed to transfer movie data between the application layers,
 * such as from the service layer to the presentation layer, without exposing
 * internal details of the domain model. It simplifies the movie data for client-side
 * consumption and may also be used to aggregate or simplify data from multiple domain
 * models or services.
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
   * Transforms a Movie domain model into a MovieDTO.
   *
   * @param movie Movie domain model to convert.
   * @return MovieDTO instance.
   */

  public static MovieDTO fromMovie(Movie movie) {
    return new MovieDTO(
      movie.getId(),
      movie.getTitle(),
      movie.getReleaseDate(),
      movie.getBudget(),
      movie.getCurrency(),
      movie.getDescription(),
      movie
        .getActors()
        .stream()
        .map(ActorDTO::fromActor)
        .collect(Collectors.toList()),
      movie
        .getDirectors()
        .stream()
        .map(DirectorDTO::fromDirector)
        .collect(Collectors.toList())
    );
  }

  /**
   * Provides a comprehensive string representation of this MovieDTO,
   * including details about its title, release date, budget, description,
   * actors, and directors. Useful for logging or displaying movie information.
   *
   * @return A string representation of the MovieDTO.
   */

  @Override
  public String toString() {
    return String.format(
      Colors.YELLOW +
      "\n - Movie's Details - " +
      Colors.RESET +
      "%nId: %d%n" +
      "Title: %s%n" +
      "ReleaseDate: %s%n" +
      "Budget: %.2f %s%n" +
      "Description: %s%n" +
      ActorDTO.formatActors(actors) +
      DirectorDTO.formatDirectors(directors),
      id,
      title,
      releaseDate,
      budget,
      currency,
      description
    );
  }

  /**
   * Inner builder class for creating instances of MovieDTO.
   * Provides a way to set properties of MovieDTO and build it in a step-by-step manner.
   */

  public static class MovieDTOBuilder {

    private int id;
    private String title;
    private int releaseDate = 0;

    /**
     * Sets the movie ID.
     *
     * @param id The movie ID.
     * @return The builder instance.
     */

    public MovieDTOBuilder id(int id) {
      this.id = id;
      return this;
    }

    /**
     * Sets the movie title.
     *
     * @param title The movie title.
     * @return The builder instance.
     */

    public MovieDTOBuilder title(String title) {
      this.title = title;
      return this;
    }

    /**
     * Sets the movie release date.
     *
     * @param releaseDate The movie release date.
     * @return The builder instance.
     */

    public MovieDTOBuilder releaseDate(int releaseDate) {
      this.releaseDate = releaseDate;
      return this;
    }

    /**
     * Builds and returns a MovieDTO instance based on the previously set properties.
     *
     * @return A new instance of MovieDTO.
     */

    public MovieDTO build() {
      return new MovieDTO(
        id,
        title,
        releaseDate,
        0,
        null,
        null,
        List.of(),
        List.of()
      );
    }
  }
}
