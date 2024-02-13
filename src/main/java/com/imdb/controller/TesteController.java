package com.imdb.controller;

import com.imdb.model.Genre;
import com.imdb.model.Movie;
import com.imdb.repository.MovieRepository;

import java.time.LocalDate;
import java.util.List;

public class TesteController {
    public static void teste() {
        Movie movie1 = new Movie(
                "Rambo I",
                LocalDate.of(1990, 10, 23),
                1250.10,
                "filme bem legalzin",
                List.of(Genre.Ação, Genre.Fantasia),
                50,
                "inglês"
        );
        Movie movie2 = new Movie(
                "Rambo II",
                LocalDate.of(1991, 11, 24),
                1350.50,
                "filme muito legal",
                List.of(Genre.Ação, Genre.Outros),
                60,
                "inglês"
        );
        Movie movie3 = new Movie(
                "Rambo III",
                LocalDate.of(1992, 12, 25),
                1450.75,
                "filme incrível",
                List.of(Genre.Ação, Genre.Outros),
                70,
                "inglês"
        );
        Movie movie4 = new Movie(
                "Rambo IV",
                LocalDate.of(1993, 1, 26),
                1550.95,
                "filme sensacional",
                List.of(Genre.Ação, Genre.Drama),
                80,
                "inglês"
        );
        Movie movie5 = new Movie(
                "Spider-Man",
                LocalDate.of(1994, 2, 27),
                1650.25,
                "filme espetacular",
                List.of(Genre.Ação, Genre.Fantasia),
                90,
                "inglês"
        );
        Movie movie6 = new Movie(
                "The Shawshank Redemption",
                LocalDate.of(1994, 3, 28),
                1750.35,
                "filme incrível",
                List.of(Genre.Drama),
                120,
                "inglês"
        );
        Movie movie7 = new Movie(
                "Inception",
                LocalDate.of(1995, 4, 29),
                1850.45,
                "filme alucinante",
                List.of(Genre.Ficção_científica, Genre.Ação),
                130,
                "inglês"
        );
        Movie movie8 = new Movie(
                "The Godfather",
                LocalDate.of(1996, 5, 30),
                1950.55,
                "filme clássico",
                List.of(Genre.Outros, Genre.Drama),
                140,
                "inglês"
        );
        Movie movie9 = new Movie(
                "Pulp Fiction",
                LocalDate.of(1997, 6, 1),
                2050.65,
                "filme cult",
                List.of(Genre.Outros, Genre.Comédia),
                150,
                "inglês"
        );
        Movie movie10 = new Movie(
                "The Dark Knight",
                LocalDate.of(1998, 7, 2),
                2150.75,
                "filme épico",
                List.of(Genre.Ação, Genre.Drama),
                160,
                "inglês"
        );

        // Adicionando filmes ao repositório
        MovieRepository.addMovie(movie1);
        MovieRepository.addMovie(movie2);
        MovieRepository.addMovie(movie3);
        MovieRepository.addMovie(movie4);
        MovieRepository.addMovie(movie5);
        MovieRepository.addMovie(movie6);
        MovieRepository.addMovie(movie7);
        MovieRepository.addMovie(movie8);
        MovieRepository.addMovie(movie9);
        MovieRepository.addMovie(movie10);
    }
}
