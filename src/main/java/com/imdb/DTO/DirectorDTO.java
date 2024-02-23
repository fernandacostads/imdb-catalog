package com.imdb.DTO;

import com.imdb.model.Director;

/**
 * Data Transfer Object (DTO) for Director entities.
 * This class is used to transfer director data between layers without exposing the domain model.
 */

public record DirectorDTO(
        int id,
        String name,
        String nationality
) {

    /**
     * Converts a DirectorDTO instance to a Director model.
     *
     * @param directorDTO The DirectorDTO instance to convert.
     * @return A Director model populated with data from the DirectorDTO.
     */

    public static Director toDirector(DirectorDTO directorDTO) {
        return new Director(
                directorDTO.id(),
                directorDTO.name(),
                directorDTO.nationality()
        );
    }

    /**
     * Converts a Director model to a DirectorDTO instance.
     *
     * @param director The Director model to convert.
     * @return A DirectorDTO instance populated with data from the Director model.
     */

    public static DirectorDTO fromDirector(Director director) {
        return new DirectorDTO(
                director.getId(),
                director.getName(),
                director.getNationality()
        );
    }

    /**
     * Provides a string representation of the DirectorDTO instance.
     *
     * @return A string containing the DirectorDTO's id, name, and nationality.
     */

    @Override
    public String toString() {
        return "ID: " + id +
                ", Name: " + name +
                ", Nationality: " + nationality + "\n";
    }
}
