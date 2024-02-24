package com.imdb.util.exceptions;

import com.imdb.util.view.Menu;
import com.imdb.util.view.message.Colors;


/**
 * Custom exception class for handling director-related errors within the application.
 * Extends RuntimeException to allow for unchecked exceptions specific to director operations.
 */

public class DirectorException extends RuntimeException {
  Menu menu = new Menu();

  /**
   * Constructor for DirectorException with a detailed error message.
   *
   * @param message Detailed error message explaining the cause of the exception.
   */

  public DirectorException(String message) {
    super(message);
    System.out.println(Colors.RED + message + Colors.RESET);
    menu.displayMainMenu();
  }

  /**
   * Subclass representing the situation where the director list is empty.
   */

  public static class DirectorListIsEmpty extends DirectorException {

    /**
     * Constructor for DirectorListIsEmpty, indicating no directors were found.
     */

    public DirectorListIsEmpty() {
      super("No directors found on the list.");
      System.out.println(Colors.RED + "No directors found on the list." + Colors.RESET);
      menu.displayMainMenu();
    }
  }

  /**
   * Subclass representing the situation where a director already exists in a list.
   */

  public static class DirectorAlreadyExist extends DirectorException {

    /**
     * Constructor for DirectorAlreadyExist, indicating a director already exists.
     *
     * @param name The name of the director that already exists.
     */

    public DirectorAlreadyExist(String name) {
      super(name + " is already included on the list.");
      System.out.println(Colors.RED + name + " is already included on the list." + Colors.RESET);
      menu.displayMainMenu();
    }
  }

  /**
   * Subclass representing the situation where a director is not found in a list.
   */

  public static class DirectorNotFoundException extends DirectorException {

    /**
     * Constructor for DirectorNotFoundException, indicating a director was not found.
     */

    public DirectorNotFoundException() {
      super("Director not found on the list.");
      System.out.println(Colors.RED + "Director not found on the list." + Colors.RESET);
      menu.displayMainMenu();
    }
  }
}

