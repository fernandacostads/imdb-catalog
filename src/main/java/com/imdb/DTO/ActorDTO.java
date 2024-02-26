package com.imdb.DTO;

import com.imdb.model.Actor;
import com.imdb.util.view.message.ActorMessage;
import com.imdb.util.view.message.Colors;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a data transfer object for Actor entities.
 * Facilitates transferring actor information between application layers,
 * encapsulating actor data away from the domain logic.
 */

public record ActorDTO(
  int id,
  String name,
  String nationality,
  LocalDate birthDate,
  List<MovieDTO> movies
) {
  /**
   * Converts this DTO to its corresponding domain model, Actor.
   *
   * @param actorDTO ActorDTO instance to convert.
   * @return Actor model populated with DTO data.
   */

  public static Actor toActor(ActorDTO actorDTO) {
    return new Actor(
      actorDTO.id(),
      actorDTO.name(),
      actorDTO.nationality(),
      actorDTO.birthDate()
    );
  }

  /**
   * Creates an ActorDTO from an Actor domain model.
   *
   * @param actor Actor model to convert.
   * @return ActorDTO instance representing the Actor model.
   */

  public static ActorDTO fromActor(Actor actor) {
    return new ActorDTO(
      actor.getId(),
      actor.getName(),
      actor.getNationality(),
      actor.getBirthDate(),
      actor
        .getMovies()
        .stream()
        .map(movie ->
          new MovieDTO.MovieDTOBuilder()
            .id(movie.getId())
            .title(movie.getTitle())
            .releaseDate(movie.getReleaseDate())
            .build()
        )
        .collect(Collectors.toList())
    );
  }

  /**
   * Formats a list of ActorDTOs into a string for display.
   *
   * @param actors List of ActorDTO objects.
   * @return Formatted string representation of actors.
   */

  public static String formatActors(List<ActorDTO> actors) {
    StringBuilder stringBuilder = new StringBuilder();
    if (actors.isEmpty()) {
      stringBuilder.append(ActorMessage.LIST_NOT_FOUND.get());
    } else {
      stringBuilder
        .append(Colors.YELLOW)
        .append("List of actors\n")
        .append(Colors.RESET);
      int index = 1;
      for (ActorDTO actor : actors) {
        stringBuilder
          .append(Colors.YELLOW)
          .append(index)
          .append(" - ")
          .append(Colors.RESET)
          .append(actor.toString());
        index++;
      }
    }
    return stringBuilder.toString();
  }

  /**
   * Generates a string representation of this ActorDTO.
   *
   * @return String containing actor's ID, name, and nationality.
   */
  public static String actorDetailed(ActorDTO actor) {
    StringBuilder directorString = new StringBuilder(
      "\nId: " +
      actor.id +
      ", Name: " +
      actor.name +
      ", Nationality: " +
      actor.nationality +
      ", Age: " +
      Period.between(actor.birthDate, LocalDate.now()).getYears() +
      " y/o\n" +
      "Movies:\n"
    );

    for (MovieDTO movie : actor.movies) {
      directorString
        .append("\tId: ")
        .append(movie.id())
        .append(", Title: ")
        .append(movie.title())
        .append(", Release Date: ")
        .append(movie.releaseDate())
        .append("\n");
    }
    return directorString.toString();
  }
  @Override
  public String toString() {
    return (
      "ID: " +
      id +
      ", Name: " +
      name +
      ", Nationality: " +
      nationality +
      ", Age: " +
      Period.between(birthDate, LocalDate.now()).getYears() +
      " y/o\n"
    );
  }
}
