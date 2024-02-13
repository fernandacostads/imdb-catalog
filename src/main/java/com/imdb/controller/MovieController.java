package com.imdb.controller;

import com.imdb.model.Genre;
import com.imdb.model.Movie;
import com.imdb.repository.MovieRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieController {
        public static void teste() {
            Movie movie1 = new Movie("Rambo I", LocalDate.of(1990, 10, 23), 1250.10, "filme bem legalzin", List.of(Genre.Ação, Genre.Fantasia), 50, "inglês");
            Movie movie2 = new Movie("Rambo II", LocalDate.of(1991, 11, 24), 1350.50, "filme muito legal", List.of(Genre.Ação, Genre.Outros), 60, "inglês");
            Movie movie3 = new Movie("Rambo III", LocalDate.of(1992, 12, 25), 1450.75, "filme incrível", List.of(Genre.Ação, Genre.Outros), 70, "inglês");
            Movie movie4 = new Movie("Rambo IV", LocalDate.of(1993, 1, 26), 1550.95, "filme sensacional", List.of(Genre.Ação, Genre.Drama), 80, "inglês");
            Movie movie5 = new Movie("Spider-Man", LocalDate.of(1994, 2, 27), 1650.25, "filme espetacular", List.of(Genre.Ação, Genre.Fantasia), 90, "inglês");
            Movie movie6 = new Movie("The Shawshank Redemption", LocalDate.of(1994, 3, 28), 1750.35, "filme incrível", List.of(Genre.Drama), 120, "inglês");
            Movie movie7 = new Movie("Inception", LocalDate.of(1995, 4, 29), 1850.45, "filme alucinante", List.of(Genre.Ficção_científica, Genre.Ação), 130, "inglês");
            Movie movie8 = new Movie("The Godfather", LocalDate.of(1996, 5, 30), 1950.55, "filme clássico", List.of(Genre.Outros, Genre.Drama), 140, "inglês");
            Movie movie9 = new Movie("Pulp Fiction", LocalDate.of(1997, 6, 1), 2050.65, "filme cult", List.of(Genre.Outros, Genre.Comédia), 150, "inglês");
            Movie movie10 = new Movie("The Dark Knight", LocalDate.of(1998, 7, 2), 2150.75, "filme épico", List.of(Genre.Ação, Genre.Drama), 160, "inglês");

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
    public static void mainMenu() {
        System.out.println("\n----- Menu -----");
        System.out.println("1. Adicionar Filme");
        System.out.println("2. Imprimir Lista de Filmes");
        System.out.println("3. Deletar Filme");
        System.out.println("4. Editar Filme");
        System.out.println("5. Pesquisar Filme");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }
    public static Movie movieParameter (Scanner sc){
        System.out.print("Título: ");
        String titulo = sc.nextLine();

        System.out.print("Data de Lançamento (Formato: AAAA-MM-DD): ");
        String dataString = sc.nextLine();
        LocalDate dataLancamento = LocalDate.parse(dataString);

        System.out.print("Orçamento: ");
        double orcamento = sc.nextDouble();
        sc.nextLine(); // Limpar o buffer do scanner

        System.out.print("Sinopse: ");
        String sinopse = sc.nextLine();

        System.out.print("Gênero: ");
        List<Genre> genero = new ArrayList<>();
        genero.add(Genre.valueOf(sc.nextLine()));

        System.out.print("Duração: ");
        int duracao = sc.nextInt();
        sc.nextLine(); // Limpar o buffer do scanner

        System.out.print("Idioma: ");
        String idioma = sc.nextLine();

        return new Movie (titulo, dataLancamento, orcamento, sinopse, genero, duracao, idioma);
    }
    public static void addMovie(Scanner sc) {
        System.out.println("\n----- Adicionar Filme -----");
        MovieRepository.addMovie(movieParameter(sc));
        System.out.println("Filme adicionado com sucesso!");
    }

    public static void editMovie(Scanner sc) {
        System.out.println("\n----- Editar Filme -----");
        System.out.print("Digite o ID do filme que deseja editar (ou 0 para cancelar): ");
        int idFilme = sc.nextInt();
        sc.nextLine(); // Limpar o buffer do scanner

        if (idFilme == 0) {
            System.out.println("Operação de edição cancelada.");
            return;
        }

        MovieRepository.searchMovieById(idFilme).ifPresentOrElse(
                movie -> {
                    System.out.println(movie);
                    System.out.print("Deseja editar este filme? (S para Sim, N para Não): ");
                    String resposta = sc.nextLine().toUpperCase();
                    if ("S".equals(resposta)) {
                        MovieRepository.editMovie(idFilme, movieParameter(sc));
                        System.out.println("Filme editado com sucesso!");
                    } else {
                        System.out.println("Edição cancelada.");
                    }
                },
                () -> System.out.println("Id não encontrado!")
        );
    }
    public static void removeMovie(Scanner sc){
        System.out.println("Digite o Id do filme que deseja excluir");
        int id = sc.nextInt();
        sc.nextLine();

        MovieRepository.removeMovie(id);
        System.out.println("Filme removido com sucesso!");
    }

    public static void listAllMovies() {
        List<Movie> movies = MovieRepository.getAllMovies();
        if (movies.isEmpty()) {
            System.out.println("Nenhum filme cadastrado.");
        } else {
            movies.forEach(System.out::println);
        }
    }

    public static void searchMovie(Scanner sc) {
        System.out.println("Digite o Nome, Id ou Gênero");
        String search = sc.nextLine();

        try {
            int searchId = Integer.parseInt(search);
            // Pesquisar por ID
            System.out.println("\nResultados da Busca ID:\n");
            MovieRepository.searchMovieById(searchId).ifPresentOrElse(
                    System.out::println,
                    () -> System.out.println("Nenhum filme encontrado com o ID fornecido: " + searchId));

        } catch (NumberFormatException e) {
            // Não é um número, então deve ser uma busca por nome ou gênero
            // Pesquisar por Nome
            System.out.println("\nResultados da Busca por Nome:\n");
            MovieRepository.searchMovieByName(search)
                    .ifPresentOrElse(
                            movies -> movies.forEach(System.out::println),
                            () -> System.out.println("Nenhum filme encontrado com o nome fornecido: " + search)
                    );

            // Pesquisar por Gênero
            System.out.println("\nResultados da Busca Gênero:\n");
            MovieRepository.searchMovieByGenre(search)
                    .ifPresentOrElse(
                            movies -> movies.forEach(System.out::println),
                            () -> System.out.println("Nenhum filme encontrado com o gênero fornecido: " + search)
                    );
        }
    }
}
