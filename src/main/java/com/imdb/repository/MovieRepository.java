package com.imdb.repository;


import com.imdb.model.Movie;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MovieRepository {
    private static final List<Movie> movies = new ArrayList<>(10);
    private static int cont = 0;

    private MovieRepository() {
    }

    public static void insere(Movie Movie) {
        Movie.id = ++cont;
        movies.add(Movie);
        System.out.println("inserido com sucesso!");
    }

    public static void edita(int id, String nome) {
        Optional<Movie> moviePorId = movies.stream().filter((f) -> f.id == id).findFirst();
        if (moviePorId.isEmpty()) {
            new Exception("Id não encontrado");
        }

        Movie movie = moviePorId.get();
        movie.nome = nome;
    }

    public static Movie deleta() {
        return null;
    }

    public static List<Movie> obterLista() {
        return movies;
    }

    public static List pesquisarPorNome(String search) {
        return movies.stream().filter((i) -> i.nome.toLowerCase().contains(search.toLowerCase())).collect(Collectors.toList());
    }

    public static List<Movie> pesquisarPorCategoria(String categoria) {
        return null;
    }
}
