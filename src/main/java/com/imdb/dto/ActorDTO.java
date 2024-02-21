package com.imdb.dto;

public record ActorDTO(
        String name,
        String nationality
) {
    @Override
    public String toString () {
        return "\nname= " + name + ", Nationality= " + nationality;
    }
}
