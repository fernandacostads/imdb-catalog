package com.imdb.util.view.message;


/**
 * Defines messages related to actor operations, incorporating ANSI colors for
 * enhanced user interface experience. Each message corresponds to different
 * outcomes or states encountered during actor operations.
 */

public enum ActorMessage {
  LIST_NOT_FOUND(Colors.RED + "No actors found. Returning to the view..." + Colors.RESET),
  REGISTERED(Colors.GREEN + "Actor registered successfully." + Colors.RESET),
  UPDATED(Colors.GREEN + "Actor updated successfully." + Colors.RESET),
  ACTOR_FOUND_NAME(Colors.RED + "No actor found with the specified name." + Colors.RESET),
  ACTOR_ID_NOT_FOUND(Colors.RED + "No actor found with the specified ID." + Colors.RESET),
  DELETED(Colors.RED + "Actor deleted successfully." + Colors.RESET),
  HOW_MANY_ACTORS("How many actors would you like to register? "),
  ENTER_ACTOR_ID_UPDATE("Enter the ID of the actor to update: "),
  ENTER_ACTOR_ID_DELETE("Enter the ID of the actor to delete: "),
  ENTER_SEARCH_KEYWORD("Enter the name keyword to search for an actor: "),
  ENTER_ACTOR_NAME("Enter the name of the actor: "),
  ENTER_ACTOR_NATIONALITY("Enter the nationality of the actor: "),
  ENTER_ACTOR_BIRTHDATE("Enter the birthdate of the director (XXXX-XX-XX): ");

  private final String message;

  ActorMessage(String message) {
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
