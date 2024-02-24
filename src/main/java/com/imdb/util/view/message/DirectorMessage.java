package com.imdb.util.view.message;

/**
 * Defines messages related to director operations, utilizing ANSI colors for
 * improved visual feedback in the user interface. Messages correspond to various
 * outcomes or states encountered during director operations.
 */

public enum DirectorMessage {
  LIST_NOT_FOUND(Colors.RED + "No directors found. Returning to the view..." + Colors.RESET),
  REGISTERED(Colors.GREEN + "Director registered successfully." + Colors.RESET),
  UPDATED(Colors.GREEN + "Director updated successfully." + Colors.RESET),
  DELETED(Colors.GREEN + "Director deleted successfully." + Colors.RESET),
  DIRECTOR_ID_NOT_FOUND(Colors.RED + "No director found with the specified ID." + Colors.RESET),
  DIRECTOR_FOUND_NAME(Colors.RED + "No director found with the specified name." + Colors.RESET),
  HOW_MANY_DIRECTORS("How many directors would you like to register? "),
  ENTER_DIRECTOR_ID_UPDATE("Enter the ID of the director to update: "),
  ENTER_DIRECTOR_ID_DELETE("Enter the ID of the director to delete: "),
  ENTER_SEARCH_KEYWORD_DIRECTOR("Enter the name keyword to search for a director: "),
  ENTER_DIRECTOR_NAME("Enter the name of the director: "),
  ENTER_DIRECTOR_NATIONALITY("Enter the nationality of the director: "),
  ENTER_DIRECTOR_BIRTHDATE("Enter the birthdate of the director (XXXX-XX-XX): ");
  private final String message;

  DirectorMessage(String message) {
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


