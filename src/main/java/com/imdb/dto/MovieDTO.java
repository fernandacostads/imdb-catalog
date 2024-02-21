package com.imdb.dto;

import java.util.List;

public record MovieDTO(
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
        return "\nMovie Details\n" +
                "Title: " + title +
                ", Release date: " + releaseDate +
                ", Budget: " + budget +
                ", Currency: " + currency +
                ", Description: " + description +
                "\nActors List\n " + actors +
                "\nDirectors List\n" + directors + "\n";
    }
}



