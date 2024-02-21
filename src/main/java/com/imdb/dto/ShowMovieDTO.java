package com.imdb.dto;

import java.util.List;

public record ShowMovieDTO(
        int id,
        String title,
        int releaseDate,
        double budget,
        String currency,
        String description,
        List<ActorDTO> actors,
        List<DirectorDTO> directors
) {
    @Override
    public String toString () {
        return "\nMovie Details:\n" +
                "\ntitle= " + title +
                "\nreleaseDate= " + releaseDate +
                "\nbudget= " + budget +
                "\ncurrency= " + currency +
                "\ndescription= " + description +
                "\nactors= " + actors +
                "\ndirectors= " + directors + "\n";
    }
}
