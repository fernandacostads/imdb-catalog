package com.imdb.repository;

import Model.Movie;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MovieRepository {
    private static List<Movie> Movies = new ArrayList(10);
    private static int cont = 0;

    private MovieRepository() {
    }

    public static Movie insere(Movie Movie) {
        Movie.id = ++cont;
        Movies.add(Movie);
        System.out.println("inserido com sucesso!");
        return Movie;
    }

    public static Movie edita(int id, String nome) {
        Optional<Movie> MoviePorId = Movies.stream().filter((f) -> {
            return f.id == id;
        }).findFirst();
        if (MoviePorId.isEmpty()) {
            new Exception("Id não encontrado");
        }

        Movie Movie = (Movie)MoviePorId.get();
        Movie.nome = nome;
        return Movie;
    }

    public static Movie deleta() {
        return null;
    }

    public static List<Movie> obterLista() {
        return Movies;
    }

    public static List<Movie> pesquisarPorNome(String search) {
        return (List)Movies.stream().filter((i) -> {
            return i.nome.toLowerCase().contains(search.toLowerCase());
        }).collect(Collectors.toList());
    }

    public static List<Movie> pesquisarPorCategoria(String categoria) {
        return null;
    }
}
