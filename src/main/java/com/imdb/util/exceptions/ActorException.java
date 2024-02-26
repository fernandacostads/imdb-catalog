package com.imdb.util.exceptions;

/**
 * Custom exception class for handling actor-related errors within the application.
 * This class extends RuntimeException, allowing for unchecked exceptions tailored to
 * specific actor operations and scenarios.
 */

public class ActorException extends RuntimeException {

  /**
   * Constructor for ActorException that takes a detailed error message.
   *
   * @param message Detailed error message explaining the cause of the exception.
   */

  public ActorException(String message) {
    super(message);
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
    }
  }
}
