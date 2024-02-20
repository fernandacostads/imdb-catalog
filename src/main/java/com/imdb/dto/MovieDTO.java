package com.imdb.dto;

public record MovieDTO(
        String title,
        int releaseDate,
        double budget,
        String currency,
        String description,
        List<ActorDto> actors,
        List<DirectorDto> directors
) { }
