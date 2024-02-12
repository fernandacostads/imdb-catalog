package com.imdb.app;

import com.imdb.model.Movie;
import com.imdb.repository.MovieRepository;
import java.util.List;

public class Teste {
    public static void main(String[] args) {

        String nome = "Rambo I";
        Movie movie = new Movie();
        movie.nome = nome;
        MovieRepository.insere(movie);

        String nome2 = "Rambo II";
        Movie movie2 = new Movie();
        movie2.nome = nome2;
        MovieRepository.insere(movie2);

        String nome3 = "Rambo III";
        Movie movie3 = new Movie();
        movie3.nome = nome3;
        MovieRepository.insere(movie3);

        String nome4 = "Spider-man returns";
        Movie movie4 = new Movie();
        movie4.nome = nome4;
        MovieRepository.insere(movie4);

        List<Movie> movies = MovieRepository.obterLista();
        
        movies.forEach(System.out::println);
        System.out.println("");

        MovieRepository.edita(3, "Rambo IV");

        movies.forEach(System.out::println);

        System.out.println("vvvvvvv\n");

        movies = MovieRepository.pesquisarPorNome("Man");
        movies.forEach(System.out::println);
    }
}
