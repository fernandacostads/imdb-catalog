package com.imdb;

import com.imdb.controller.MovieController;
import java.util.Scanner;

public class ImdbLibraryApp {
    public static void main(String[] args) {

        MovieController.teste();//inicia 10 movies pra teste

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            MovieController.mainMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1 -> MovieController.addMovie(scanner);
                case 2 -> MovieController.listAllMovies();
                case 3 -> MovieController.removeMovie(scanner);
                case 4 -> MovieController.editMovie(scanner);
                case 5 -> MovieController.searchMovie(scanner);
                case 0 -> System.out.println("Saindo do programa. Até mais!");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}