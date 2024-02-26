package com.imdb.controller;

import com.imdb.DTO.ActorDTO;
import com.imdb.repository.IActorRepository;
import com.imdb.util.view.message.ActorMessage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Manages actor-related operations within the IMDb system.
 * Provides functionalities for registering, updating, deleting, and displaying actors,
 * as well as searching for actors by name. Utilizes an actor repository for data persistence
 * and a scanner for user input collection.
 */

public final class ActorController {

  private final IActorRepository actorRepository;
  private final Scanner scanner;

  /**
   * Initializes the ActorController with a specific actor repository and scanner.
   *
   * @param actorRepository The repository used for actor data persistence.
   * @param scanner         The scanner used for collecting user input.
   */

  public ActorController(IActorRepository actorRepository, Scanner scanner) {
    this.actorRepository = actorRepository;
    this.scanner = scanner;
  }

  /**
   * Registers a specified number of new actors based on user input.
   *
   * @return A list of ActorDTO objects representing the newly registered actors.
   */

  public List<ActorDTO> createActor() {
    System.out.print(ActorMessage.HOW_MANY_ACTORS.get());
    int qntActors = scanner.nextInt();
    scanner.nextLine();
    List<ActorDTO> actors = new ArrayList<>();

    for (int i = 1; i <= qntActors; i++) {
      ActorDTO newActorDTO = inputActor();
      actors.add(actorRepository.create(newActorDTO));
      System.out.println(ActorMessage.REGISTERED.get());
    }
    return actors;
  }

  /**
   * Displays a list of all registered actors to the user.
   */

  public void readListOfActors() {
    List<ActorDTO> actors = actorRepository.read();
    String formattedActors = ActorDTO.formatActors(actors);
    System.out.println(formattedActors);
    System.out.println("Would you like to see details of an Actor? (yes/no)");

    if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
      System.out.println("Enter actor ID:");
      String id = scanner.nextLine();

      Optional<ActorDTO> actorToSee = actors
        .stream()
        .filter(actor -> actor.id() == Integer.parseInt(id))
        .findFirst();

      if (actorToSee.isPresent()) {
        ActorDTO actorDetails = actorToSee.get();
        System.out.println(ActorDTO.actorDetailed(actorDetails));
      } else {
        System.out.println("Actor Id: " + id + " is not on the list");
      }
    }
  }

  /**
   * Updates the information of an existing actor based on their ID.
   */

  public void updateActor(List<ActorDTO> actors) {
    System.out.println("Current actors in the movie:");
    actors.forEach(actor ->
      System.out.println(actor.id() + " - " + actor.name())
    );

    System.out.println("Do you want to add or remove actors? (add/remove)");
    String action = scanner.nextLine().trim();

    switch (action) {
      case "add":
        List<ActorDTO> addedActors = createActor();
        actors.addAll(addedActors);
        break;
      case "remove":
        deleteActor(actors);
        break;
    }
  }

  /**
   * Deletes an actor from the system based on their ID.
   */

  public void deleteActor(List<ActorDTO> actors) {
    System.out.println("Enter actor IDs to remove (comma separated):");
    String[] idsToRemove = scanner.nextLine().split(",");
    for (String idStr : idsToRemove) {
      int idToRemove = Integer.parseInt(idStr.trim());
      actors.removeIf(actor -> actor.id() == idToRemove);
    }
  }

  /**
   * Searches for and displays actors whose names match a given keyword.
   */

  public void searchActors() {
    System.out.print(ActorMessage.ENTER_SEARCH_KEYWORD.get());
    String keyword = scanner.nextLine();
    ActorDTO actorName = new ActorDTO(0, keyword, null, null, List.of());
    List<ActorDTO> actorDTOList = actorRepository.search(actorName);
    if (actorDTOList.isEmpty()) {
      System.out.println(ActorMessage.ACTOR_FOUND_NAME);
    } else {
      actorDTOList.forEach(actorDTO ->
        System.out.println(ActorDTO.actorDetailed(actorDTO))
      );
    }
  }

  /**
   * Collects user input for a new actor, including their name and nationality, and creates a new ActorDTO object.
   *
   * @return A new ActorDTO object based on user input.
   */

  public ActorDTO inputActor() {
    System.out.print(ActorMessage.ENTER_ACTOR_NAME.get());
    String name = scanner.nextLine();
    System.out.print(ActorMessage.ENTER_ACTOR_NATIONALITY.get());
    String nationality = scanner.nextLine();
    System.out.print(ActorMessage.ENTER_ACTOR_BIRTHDATE.get());
    LocalDate birthdate = LocalDate.parse(scanner.nextLine());
    return new ActorDTO(0, name, nationality, birthdate, List.of());
  }
}
