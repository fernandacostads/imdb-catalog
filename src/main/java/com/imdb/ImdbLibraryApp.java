package com.imdb;

import com.imdb.controller.MovieController;
import java.util.Scanner;

public class ImdbLibraryApp {
    public static void main(String[] args) {

        MovieController.teste();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            mainMenu();
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
    private static void mainMenu() {
        System.out.println("\n----- Menu -----");
        System.out.println("1. Adicionar Filme");
        System.out.println("2. Imprimir Lista de Filmes");
        System.out.println("3. Deletar Filme");
        System.out.println("4. Editar Filme");
        System.out.println("5. Pesquisar Filme");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }
}