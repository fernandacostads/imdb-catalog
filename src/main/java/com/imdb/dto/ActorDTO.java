package com.imdb.dto;

public record ActorDTO(
        String name,
        String nationality
) {
    @Override
    public String toString () {
        return "Name: " + name +
                ", Nationality: " + nationality;
    }
}
