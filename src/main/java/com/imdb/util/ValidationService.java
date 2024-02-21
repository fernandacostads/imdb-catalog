package com.imdb.util;

import com.imdb.model.Genre;

import java.util.EnumSet;
import java.util.Scanner;

public class ValidationService {
    /*essa classe serviria de base pra toda vida que eu tiver um input
    eu veificar se o int é valido, se a string é valida e apagar espaços desnecessários de uma string*/

  //função geral que recebe todas as entradas com a entrada de linha
  private static String getInput(Scanner sc) {
    return sc.nextLine();
  }

  //vefica se a entrada é um int
  public static int isInputInt(Scanner sc) {
    // Checks if the entered input is an integer number

    //converte a entrada string em um int; permite a entrada de numeros negativos.
    try {
      return Integer.parseInt(getInput(sc));
      //se a string for vazia ou nula; caracteres não numéricos; numero não for um int;
      //se tiver espaço em branco.
    } catch (NumberFormatException e) {
      System.out.println("This option is invalid, must be a integer number.");
      return -1;
    }
  }

  //faz a mesma coisa que isInputInt(), mas com double
  private static double isInputDouble(Scanner sc) {
    // Checks if the entered input is a fractional number
    try {
      return Double.parseDouble(getInput(sc));
    } catch (NumberFormatException e) {
      System.out.println("This input is invalid, must be a number.");
      return -1;
    }
  }


  public static int isValidOption(Scanner sc, int rangeMax, int rangeMin) {
    // Checks if the entered option is a number and if it is within the range of options

    //não precisa ser uma constante
    //isInputInt retorna um -1 caso entre no catch
    final int userChoice = isInputInt(sc);

    //se for -1, ou seja, se não foi um inteiro válido
    if (userChoice == -1) {
      return -1;
      //verifica se é alguma das opções do menu
    } else if (userChoice >= rangeMin && userChoice <= rangeMax) {
      //retorna a opção caso ela esteja no intervalo
      return userChoice;
      //era um número inteiro válido, mas não estava dentro do intervalo das opções
    } else {
      System.out.println("This option is invalid, you must choose a number from " + rangeMin + " to " + rangeMax);
      return -1;
    }
  }

  public static String isValidMovieName(Scanner sc) {

    //recebe uma string como sc.NextLine();
    final String userInput = getInput(sc);

        /*Check if the movie title is not empty,
         if there are no unnecessary spaces,
         and if it has more than 3 letters*/

        /* Pi é um filme com 2 letras;
        Q é um filme com 1 letra;
        Resolver problema, pois pode recebr um título com 1 letra;
        Se houver espaços vazios desnecessários, elimina eles e formada a entrada;
         */

    if (userInput != null && !userInput.trim().isEmpty() && userInput.length() >= 3) {
      return userInput;
    } else {
      System.out.println("Invalid title! The movie name must be at least 3 characters long.");
      return isValidMovieName(sc);
    }
  }

  public static int isValidId(Scanner sc) {
    try {
      return Integer.parseInt(getInput(sc));
    } catch (NumberFormatException e) {
      System.out.println("Dígito inválido. Tente novamente!");
      return -1;
    }
  }

  public static boolean isValidMovieId(int id) {
    // Verifica se o ID é positivo
    return id > 0;
  }

  // Verifica se o gênero está presente no enum
  public static boolean isValidGenre(Genre genre) {
    return EnumSet.allOf(Genre.class).contains(genre);
  }
}
