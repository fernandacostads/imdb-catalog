package com.imdb.application.scanner;

import java.util.Scanner;

public class Input {
    Scanner scanner = new Scanner(System.in);

    public void cadastrarFilme() {
        scanner.nextLine();
        System.out.println("Digite um nome");
        String name = scanner.nextLine();
        System.out.println("Digite a data de lancamento");
        String dataDeLancamento = scanner.nextLine();
        System.out.println("Digite a descrição");
        String descricao = scanner.nextLine();
    }

    public int option() {
        return scanner.nextInt();
    }
}
