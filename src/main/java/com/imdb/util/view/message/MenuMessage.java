package com.imdb.util.view.message;

/**
 * Enumerates menu messages for different sections of the application, incorporating
 * ANSI colors to distinguish different menu options and actions. Enhances user
 * navigation and interaction within the application.
 */

public enum MenuMessage {
  MAIN_MENU(Colors.YELLOW + " - Main Menu - \n" + Colors.RESET +
            "1 - Create a new movie\n" +
            "2 - Read lists (Movies, Actors, Directors)\n" +
            "3 - Update movie\n" +
            "4 - Delete movie\n" +
            "5 - Search\n" +
            "0 - Close the program\n" +
            "Please enter your choice: "),

  LIST_MENU(Colors.YELLOW + "Which list do you want to display?\n" + Colors.RESET +
            "1 - Show list of movies\n" +
            "2 - Show list of actors\n" +
            "3 - Show list of directors\n" +
            "0 - Return to main view\n" +
            "Please enter your choice: "),

  SEARCH_MENU(Colors.YELLOW + "What do you want to search for?\n" + Colors.RESET +
              "1 - Movies by title or release date\n" +
              "2 - Actors\n" +
              "3 - Directors\n" +
              "0 - Return to main view\n" +
              "Please enter your choice: "),
  EXITING_PROGRAM(Colors.YELLOW + "Exiting program..." + Colors.RESET),
  INVALID_CHOICE_LIST(Colors.RED + "Invalid choice. Please enter a number between 1 and 3." + Colors.RESET),
  INVALID_CHOICE_MAIN(Colors.RED + "Invalid choice. Please enter a number between 1 and 5." + Colors.RESET),
  INVALID_INPUT("Invalid Input. Please enter an integer!");

  private final String message;

  MenuMessage(String message) {
    this.message = message;
  }

  /**
   * Retrieves the menu message.
   *
   * @return The formatted menu message string.
   */

  public String get() {
    return message;
  }

}

