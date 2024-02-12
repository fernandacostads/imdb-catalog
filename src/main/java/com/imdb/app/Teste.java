package com.imdb.app;

import Model.Movie;
import Repository.MovieRepository;
import java.io.PrintStream;
import java.util.List;
import java.util.Objects;

public class Teste {
    public App() {
    }

    public static void main(String[] args) {
        String nome = "Rambo I";
        Movie Movie = new Movie();
        Movie.nome = nome;
        MovieRepository.insere(Movie);
        String nome2 = "Rambo II";
        Movie Movie2 = new Movie();
        Movie2.nome = nome2;
        MovieRepository.insere(Movie2);
        String nome3 = "Rambo III";
        Movie Movie3 = new Movie();
        Movie3.nome = nome3;
        MovieRepository.insere(Movie3);
        String nome4 = "Spider-man returns";
        Movie Movie4 = new Movie();
        Movie4.nome = nome4;
        MovieRepository.insere(Movie4);
        List<Movie> Movies = MovieRepository.obterLista();
        PrintStream var10001 = System.out;
        Objects.requireNonNull(var10001);
        Movies.forEach(var10001::println);
        System.out.println("");
        MovieRepository.edita(3, "Rambo IV");
        var10001 = System.out;
        Objects.requireNonNull(var10001);
        Movies.forEach(var10001::println);
        System.out.println("vvvvvvv\n");
        Movies = MovieRepository.pesquisarPorNome("Ram");
        var10001 = System.out;
        Objects.requireNonNull(var10001);
        Movies.forEach(var10001::println);
    }
}
