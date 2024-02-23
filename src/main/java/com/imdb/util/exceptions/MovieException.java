package com.imdb.util.exceptions;

/**
 * Custom exception class for movie-related errors within the application.
 * Extends RuntimeException to allow for unchecked exceptions tailored to movie operations.
 */


public class MovieException extends RuntimeException {

  /**
   * Constructor for MovieException with a detailed error message.
   *
   * @param message Detailed error message explaining the cause of the exception.
   */

  public MovieException(String message) {
    super(message);
  }

  /**
   * Subclass representing the situation where no movies are found on the list.
   */

  public static class MovieListIsEmpty extends MovieException {

    /**
     * Constructor for MovieListIsEmpty, indicating no movies were found.
     */

    public MovieListIsEmpty() {
      super("No movies found on the list.");
    }
  }

  /**
   * Subclass representing the situation where a movie already exists in the repository.
   */

  public static class MovieAlreadyExists extends MovieException {

    /**
     * Constructor for MovieAlreadyExists, indicating a movie already exists.
     *
     * @param title The title of the movie that already exists.
     */

    public MovieAlreadyExists(String title) {
      super("The movie '" + title + "' already exists in the repository.");
    }
  }

  /**
   * Subclass representing the situation where a movie is not found in the repository.
   */

  public static class MovieNotFoundException extends MovieException {

    /**
     * Constructor for MovieNotFoundException, indicating a movie was not found.
     */

    public MovieNotFoundException() {
      super("Movie not found on the list.");
    }
  }
}

