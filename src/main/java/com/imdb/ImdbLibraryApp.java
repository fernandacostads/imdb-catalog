package com.imdb;

import com.imdb.AppServices.ValidationService;
import com.imdb.controller.MovieController;
import com.imdb.controller.TesteController;
import java.util.Scanner;

public class ImdbLibraryApp {
  public static void main(String[] args) {
    TesteController.teste(); //inicia 10 movies pra teste
    try (Scanner scanner = new Scanner(System.in)) {
      int opcao;

      do {
        MovieController.mainMenu();
        opcao = ValidationService.isValidOption(scanner, 5,0);

        switch (opcao) {
          case 1 -> MovieController.addMovie(scanner);
          case 2 -> MovieController.listAllMovies();
          case 3 -> MovieController.removeMovie(scanner);
          case 4 -> MovieController.editMovie(scanner);
          case 5 -> MovieController.searchMovie(scanner);
          case 0 -> System.out.println("Saindo do programa. Até mais!");
        }
      } while (opcao != 0);
    }// O Scanner será fechado automaticamente aqui
  }
}
