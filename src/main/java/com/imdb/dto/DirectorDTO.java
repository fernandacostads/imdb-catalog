package com.imdb.dto;

import com.imdb.model.Director;

public record DirectorDTO(
        int id,
        String name,
        String nationality
) {
  public static Director toDirector(DirectorDTO directorDTO) {
    return new Director(
            directorDTO.id(),
            directorDTO.name(),
            directorDTO.nationality()
            // ... outros campos relevantes
    );
  }

  public static DirectorDTO fromDirector(Director director) {
    return new DirectorDTO(
            director.getId(),
            director.getName(),
            director.getNationality()
            // ... outros campos relevantes
    );
  }
  @Override
  public String toString() {
    return "\nDirector: " +
            "\nName:"+ name +", Nationality: " + nationality ;
  }
}
