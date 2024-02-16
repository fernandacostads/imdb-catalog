package com.imdb.appServices;

import com.imdb.model.Genre;

import java.util.EnumSet;
import java.util.Scanner;

public class ValidationService {

  private static String getInput(Scanner sc) {
    return sc.nextLine();
  }

  private static int isInputInt(Scanner sc) {
    // Checks if the entered input is an integer number
    try {
      return Integer.parseInt(getInput(sc));
    } catch (NumberFormatException e) {
      System.out.println("This option is invalid, must be a integer number.");
      return -1;
    }
  }

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
    final int userChoice = isInputInt(sc);

    if (userChoice == -1) {
      return -1;
    } else if (userChoice >= rangeMin && userChoice <= rangeMax) {
      return userChoice;
    } else {
      System.out.println("This option is invalid, you must choose a number from " +
              rangeMin + " to " + rangeMax);
      return -1;
    }
  }

  public static String isValidMovieName(Scanner sc) {
  /*Check if the movie title is not empty,
     if there are no unnecessary spaces,
     and if it has more than 3 letters*/
    final String userInput = getInput(sc);

    if (userInput != null && !userInput.trim().isEmpty() && userInput.length() >= 3) {
      return userInput;
    } else {
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


}
