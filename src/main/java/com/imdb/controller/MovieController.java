package com.imdb.controller;

import com.imdb.appServices.ValidationService;
import com.imdb.model.Genre;
import com.imdb.model.Movie;
import com.imdb.repository.MovieRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MovieController {

  public static Movie movieParameter(Scanner sc) {
    System.out.print("Título: ");
    String titulo = ValidationService.isValidMovieName(sc);

    System.out.print("Data de Lançamento (Formato: AAAA-MM-DD): ");
    String dataString = sc.nextLine();
    String dataLancamento = dataString;

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

    return new Movie(
      titulo,
      dataLancamento,
      orcamento,
      "$",
      sinopse,
      null,
      null
    );
  }

  public static void registerNewMovie(Scanner sc) {
    System.out.println("\n----- Adicionar Filme -----");
    MovieRepository.addMovie(movieParameter(sc));
    System.out.println("Filme adicionado com sucesso!");
  }

  public static void editMovie(Scanner sc) {
    System.out.println("\n----- Editar Filme -----");
    System.out.print(
      "Digite o ID do filme que deseja editar (ou 0 para cancelar): "
    );
    int idFilme = sc.nextInt();
    sc.nextLine(); // Limpar o buffer do scanner

    if (idFilme == 0) {
      System.out.println("Operação de edição cancelada.");
      return;
    }

    Movie movieAux = MovieRepository.searchMovieById(idFilme).orElse(null);

    if (movieAux != null) {
      boolean continuarEdicao = true;
      while (continuarEdicao) {
        System.out.println("Filme encontrado: " + movieAux);
        System.out.println("O que você deseja editar?");
        System.out.println("1. Título");
        System.out.println("2. Data de Lançamento");
        System.out.println("3. Orçamento");
        System.out.println("4. Sinopse");
        System.out.println("5. Gênero");
        System.out.println("6. Duração");
        System.out.println("7. Idioma");
        System.out.println("0. Sair"); // Opção para sair do menu

        System.out.print("Escolha uma opção: ");
        int opcao = ValidationService.isValidOption(sc,7,0);

        switch (opcao) {
          case 1:
            System.out.print("Novo título: ");
            movieAux.setTitle(ValidationService.isValidMovieName(sc));
            break;
          case 2:
            System.out.print("Novo Data de Lançamento (Formato: AAAA-MM-DD): ");
            movieAux.setReleaseDate(sc.nextLine());
            break;
          case 3:
            System.out.print("Novo valor de Orçamento:");
            movieAux.setBudget(sc.nextDouble());
            sc.nextLine();
            break;
          case 4:
            System.out.print("Nova Descrição do Filme: ");
            movieAux.setDescription(sc.nextLine());
            break;
              case 0:
            System.out.println("Saindo do menu de edição.");
            continuarEdicao = false;
            break;
          default:
            System.out.println("Opção inválida.");
        }
      }
    } else {
      System.out.println("Filme não encontrado.");
    }

    System.out.println("Filme Editado: " + movieAux);
    System.out.print(
      "Deseja confirmar a edição deste filme? (S para Sim, N para Não):"
    );

    String resposta = sc.nextLine().toUpperCase();

    if ("S".equals(resposta)) {
      MovieRepository.editMovie(idFilme, movieAux);
      System.out.println("Filme editado com sucesso!");
    } else {
      System.out.println("Edição cancelada.");
    }
  }

  public static void deleteMovie(Scanner sc) {
    System.out.println("Digite o Id do filme que deseja excluir");
    int id = sc.nextInt();
    sc.nextLine();

    MovieRepository.removeMovie(id);
    System.out.println("Filme removido com sucesso!");
  }

  public static void showListOfMovies() {
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
      MovieRepository
        .searchMovieById(searchId)
        .ifPresentOrElse(
          System.out::println,
          () ->
            System.out.println(
              "Nenhum filme encontrado com o ID fornecido: " + searchId
            )
        );
    } catch (NumberFormatException e) {
      // Não é um número, então deve ser uma busca por nome ou gênero
      // Pesquisar por Nome
      System.out.println("\nResultados da Busca por Nome:\n");
      MovieRepository
        .searchMovieByName(search)
        .ifPresentOrElse(
          movies -> movies.forEach(System.out::println),
          () ->
            System.out.println(
              "Nenhum filme encontrado com o nome fornecido: " + search
            )
        );

      /* Pesquisar por Gênero
      System.out.println("\nResultados da Busca Gênero:\n");
      MovieRepository
        .searchMovieByGenre(search)
        .ifPresentOrElse(
          movies -> movies.forEach(System.out::println),
          () ->
            System.out.println(
              "Nenhum filme encontrado com o gênero fornecido: " + search
            )
        );*/
    }
  }
}
