package com.imdb.AppServices;

import com.imdb.model.Genre;

import java.util.EnumSet;
import java.util.Scanner;

public class ValidationService {

  public static int isValidOption(Scanner sc, int rangeMax, int rangeMin) {
    // Verifica se a opção digitada é um número e se está dentro do range das opçoes
    try {
      final int userChoice = Integer.parseInt(getInput(sc));
      if (userChoice >= rangeMin && userChoice <= rangeMax) {
        return userChoice;
      } else {
        System.out.println("Opção invalida. Tente novamente!");
        return -1;
      }
    } catch (NumberFormatException e) {
      System.out.println("Dígito inválido. Tente novamente!");
      return -1;
    }
  }

  public static String isValidMovieName(Scanner sc) {

    final String userInput  = getInput(sc);

    if(userInput != null && !userInput.trim().isEmpty() && userInput.length() >= 3){
      return userInput;
    }else{
      System.out.println("Título inválido! O nome deve ter pelo menos 3 caracteres.");
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

  public static boolean isValidGenre(Genre genre) {
    // Verifica se o gênero está presente no enum
    return EnumSet.allOf(Genre.class).contains(genre);
  }

  public static String getInput(Scanner sc) {
    return sc.nextLine();
  }

}
