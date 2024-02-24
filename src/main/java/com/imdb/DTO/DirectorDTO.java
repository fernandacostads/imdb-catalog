package com.imdb.DTO;

import com.imdb.model.Director;
import com.imdb.util.view.message.Colors;
import com.imdb.util.view.message.DirectorMessage;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a data transfer object for Director entities.
 * Allows for the safe transfer of director information between application layers,
 * abstracting away from the domain models.
 */

public record DirectorDTO(
        int id,
        String name,
        String nationality,
        LocalDate birthDate
) {

  /**
   * Converts this DTO to a Director domain model.
   *
   * @param directorDTO DirectorDTO to convert.
   * @return Director model with data from the DTO.
   */

  public static Director toDirector(DirectorDTO directorDTO) {
    return new Director(
            directorDTO.id(),
            directorDTO.name(),
            directorDTO.nationality(),
            directorDTO.birthDate()
    );
  }

  /**
   * Constructs a DirectorDTO from a Director domain model.
   *
   * @param director Director model to convert.
   * @return DirectorDTO instance.
   */

  public static DirectorDTO fromDirector(Director director) {
    return new DirectorDTO(
            director.getId(),
            director.getName(),
            director.getNationality(),
            director.getBirthDate()
    );
  }

  /**
   * Formats a list of DirectorDTOs for display.
   *
   * @param directors List of DirectorDTOs.
   * @return String representation of the director list.
   */

  public static String formatDirectors(List<DirectorDTO> directors) {
    StringBuilder stringBuilder = new StringBuilder();
    if (directors.isEmpty()) {
      stringBuilder.append(DirectorMessage.LIST_NOT_FOUND.get());
    } else {
      stringBuilder.append(Colors.YELLOW).append("List of directors\n").append(Colors.RESET);
      int index = 1;
      for (DirectorDTO director : directors) {
        stringBuilder.append(Colors.YELLOW).append(index).append(" - ").append(Colors.RESET).append(director.toString());
        index++;
      }
    }
    return stringBuilder.toString();
  }

  /**
   * Returns a string representation of this DirectorDTO.
   *
   * @return String containing director's ID, name, and nationality.
   */

  @Override
  public String toString() {
    return "ID: " + id +
           ", Name: " + name +
           ", Nationality: " + nationality + "\n";
  }
}
