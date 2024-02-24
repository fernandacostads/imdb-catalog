package com.imdb.util.exceptions;

import com.imdb.util.view.Menu;
import com.imdb.util.view.message.Colors;

/**
 * Custom exception class for handling actor-related errors within the application.
 * This class extends RuntimeException, allowing for unchecked exceptions tailored to
 * specific actor operations and scenarios.
 */

public class ActorException extends RuntimeException {
  Menu menu = new Menu();

  /**
   * Constructor for ActorException that takes a detailed error message.
   *
   * @param message Detailed error message explaining the cause of the exception.
   */

  public ActorException(String message) {
    super(message);
    System.out.println(Colors.RED + message + Colors.RESET);
    menu.displayMainMenu();
  }

  /**
   * Subclass representing the situation where no actors are found in a list.
   */

  public static class ActorListIsEmpty extends ActorException {

    /**
     * Constructor for ActorListIsEmpty, indicating no actors were found.
     */

    public ActorListIsEmpty() {
      super("No actors found on the list.");
      System.out.println(Colors.RED + "No actors found on the list." + Colors.RESET);
      menu.displayMainMenu();
    }
  }

  /**
   * Subclass representing the situation where an actor already exists in a list.
   */

  public static class ActorAlreadyExist extends ActorException {

    /**
     * Constructor for ActorAlreadyExist, indicating an actor already exists.
     *
     * @param name The name of the actor that already exists.
     */

    public ActorAlreadyExist(String name) {
      super(name + " is already included on the list.");
      System.out.println(Colors.RED + name + " is already included on the list." + Colors.RESET);
      menu.displayMainMenu();
    }
  }

  /**
   * Subclass representing the situation where an actor is not found in a list.
   */

  public static class ActorNotFoundException extends ActorException {

    /**
     * Constructor for ActorNotFoundException, indicating an actor was not found.
     */

    public ActorNotFoundException() {
      super("Actor not found on the list.");
      System.out.println(Colors.RED + "Actor not found on the list." + Colors.RESET);
      menu.displayMainMenu();
    }
  }
}

