package com.imdb.controller;

import com.imdb.DTO.ActorDTO;
import com.imdb.repository.IActorRepository;
import com.imdb.util.view.message.ActorMessage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    List<ActorDTO> actors = new ArrayList<>(10);

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
  }

  /**
   * Updates the information of an existing actor based on their ID.
   */

  public void updateActor() {
    System.out.print(ActorMessage.ENTER_ACTOR_ID_UPDATE.get());
    int id = scanner.nextInt();
    scanner.nextLine();
    ActorDTO actorDTOId = new ActorDTO(id, null, null, null);
    ActorDTO actorToUpdate = actorRepository.readById(actorDTOId);

    if (actorToUpdate == null) {
      System.out.println(ActorMessage.ACTOR_ID_NOT_FOUND.get());
    } else {
      ActorDTO updatedActorDTO = inputActor();
      actorRepository.update(actorToUpdate, updatedActorDTO);
      System.out.println(ActorMessage.UPDATED.get());
    }
  }

  /**
   * Deletes an actor from the system based on their ID.
   */

  public void deleteActor() {
    System.out.print(ActorMessage.ENTER_ACTOR_ID_DELETE.get());
    int id = scanner.nextInt();
    scanner.nextLine();
    ActorDTO actorDTO = new ActorDTO(id, null, null, null);

    ActorDTO actorToDelete = actorRepository.readById(actorDTO);

    if (actorToDelete == null) {
      System.out.println(ActorMessage.ACTOR_ID_NOT_FOUND.get());
    } else {
      actorRepository.delete(actorToDelete);
      System.out.println(ActorMessage.DELETED.get());
    }
  }

  /**
   * Searches for and displays actors whose names match a given keyword.
   */

  public void searchActors() {
    System.out.print(ActorMessage.ENTER_SEARCH_KEYWORD.get());
    String keyword = scanner.next();
    ActorDTO actorName = new ActorDTO(0, keyword, null, null);
    List<ActorDTO> actorDTOList = actorRepository.search(actorName);
    System.out.println(actorDTOList.isEmpty() ? ActorMessage.ACTOR_FOUND_NAME : actorDTOList);
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
    return new ActorDTO(0, name, nationality, birthdate);
  }
}
