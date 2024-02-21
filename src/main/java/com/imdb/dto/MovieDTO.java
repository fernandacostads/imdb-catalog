package com.imdb.dto;

import java.util.List;

public record MovieDTO(
        int id,
        String title,
        int releaseDate,
        double budget,
        String currency,
        String description,
        List<ActorDTO> actors,
        List<DirectorDTO> directors
) {}