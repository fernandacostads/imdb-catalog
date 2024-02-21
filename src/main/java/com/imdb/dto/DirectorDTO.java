package com.imdb.dto;

public record DirectorDTO(
        String name,
        String nationality
) {
    @Override
    public String toString () {
        return "Name: " + name +
                ", Nationality: " + nationality;
    }
}
