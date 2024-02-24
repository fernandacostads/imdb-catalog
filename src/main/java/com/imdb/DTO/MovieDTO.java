package com.imdb.DTO;

import com.imdb.model.Movie;
import com.imdb.util.view.message.Colors;
import com.imdb.util.view.message.MovieMessage;

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

public record
MovieDTO(
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
            movie.getActors().stream()
                    .map(ActorDTO::fromActor)
                    .collect(Collectors.toList()),
            movie.getDirectors().stream()
                    .map(DirectorDTO::fromDirector)
                    .collect(Collectors.toList())
    );
  }

  public static String formatMovies(List<MovieDTO> movies) {
    StringBuilder stringBuilder = new StringBuilder();
    if (movies.isEmpty()) {
      stringBuilder.append(MovieMessage.LIST_NOT_FOUND.get());
    } else {
      stringBuilder.append(Colors.YELLOW).append("Lista de filmes\n").append(Colors.RESET);
      for (MovieDTO movie : movies) {
        stringBuilder.append(Colors.YELLOW).append(movie.id).append(" - ").append(Colors.RESET).append(movie.title).append("\n");
      }
    }
    return stringBuilder.toString();
  }

  /**
   * Converts this MovieDTO to a Movie domain model.
   * This method is useful when transferring data from a DTO to a domain model,
   * typically before persisting the data in a database.
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
   * Provides a comprehensive string representation of this MovieDTO,
   * including details about its title, release date, budget, description,
   * actors, and directors. Useful for logging or displaying movie information.
   *
   * @return A string representation of the MovieDTO.
   */

  @Override
  public String toString() {
    return Colors.YELLOW + "\n - Movie's Details - " + Colors.RESET +
           "\nID: " + id +
           "\nTitle: " + title +
           "\nRelease date: " + releaseDate +
           "\nBudget: " + budget +
           "\nCurrency: " + currency +
           "\nDescription: " + description + "\n" +
           ActorDTO.formatActors(actors) +
           DirectorDTO.formatDirectors(directors);
  }

  /**
   * Inner builder class for creating instances of MovieDTO.
   * Provides a way to set properties of MovieDTO and build it in a step-by-step manner.
   */

  public static class MovieDTOBuilder {
    private int id;
    private String title;
    private int releaseDate;
    private String description;
    private String query;

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
     * Sets the movie release date.
     *
     * @param description The movie release date.
     * @return The builder instance.
     */

    public MovieDTOBuilder description(String description) {
      this.description = description;
      return this;
    }

    /**
     * Sets a search query used for filtering movies based on certain criteria, such as title or release date.
     * This method is particularly useful when constructing a MovieDTO for search operations where the query
     * might not directly map to a specific movie property but is used to filter the list of movies.
     *
     * @param query The search query string.
     * @return The builder instance with the query set, allowing for fluent chaining of builder methods.
     */

    public MovieDTOBuilder query(String query) {
      this.query = query;
      return this;
    }

    /**
     * Builds and returns a MovieDTO instance based on the previously set properties.
     *
     * @return A new instance of MovieDTO.
     */

    public MovieDTO build() {
      return new MovieDTO(id, title, releaseDate, 0, query, description, List.of(), List.of());
    }
  }
}