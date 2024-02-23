package com.imdb.util.view.message;

/**
 * Enumerates messages related to movie operations, using ANSI colors for
 * visual differentiation of success, error, and informational messages.
 * Each message is tailored to the outcome of movie-related operations.
 */

public enum MovieMessage {
  LIST_NOT_FOUND(Colors.RED + "No movies found. Returning to the view menu..." + Colors.RESET),
  MOVIE_NOT_FOUND(Colors.RED + "No movies found during this search. Returning to the menu..." + Colors.RESET),
  SEARCH_ERROR(Colors.RED + "An error occurred while searching..." + Colors.RESET),
  REGISTERED(Colors.GREEN + "Movie registered successfully." + Colors.RESET),
  UPDATED(Colors.GREEN + "Movie updated successfully." + Colors.RESET),
  DELETED(Colors.GREEN + "Movie deleted successfully." + Colors.RESET);

  private final String message;

  MovieMessage(String message) {
    this.message = message;
  }

  /**
   * Retrieves the message.
   *
   * @return The formatted message string.
   */

  public String get() {
    return message;
  }
}

