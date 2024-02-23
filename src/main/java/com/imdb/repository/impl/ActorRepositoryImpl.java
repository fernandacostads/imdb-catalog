package com.imdb.repository.impl;

import com.imdb.DTO.ActorDTO;
import com.imdb.model.Actor;
import com.imdb.repository.IActorRepository;
import com.imdb.util.FileHandler;
import com.imdb.util.exceptions.ActorException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implements the IActorRepository interface to manage actor details,
 * providing a file-based persistence mechanism. Supports operations such as creating,
 * updating, deleting, and querying actor records. Adopts the Singleton design pattern
 * to ensure a single instance manages actor details throughout the application.
 */

public class ActorRepositoryImpl implements IActorRepository {
  private static final String FILE_PATH =
          "src/main/java/com/imdb/util/resources/actors.txt";
  private static ActorRepositoryImpl instance;
  private static List<Actor> actorsList;
  private int idGenerator;

  /**
   * Initializes the actor list from a file and sets up the ID generator. This private
   * constructor prevents direct instantiation from outside the class to implement the
   * Singleton pattern.
   */

  private ActorRepositoryImpl() {
    actorsList = new ArrayList<>(10);
    actorsList = FileHandler.readActorsFromFile(FILE_PATH);
    idGenerator = actorsList.isEmpty() ? 1 : actorsList.getLast().getId() + 1;
  }

  /**
   * Ensures that only one instance of the ActorRepositoryImpl is created. If the
   * instance does not exist, it initializes a new one.
   *
   * @return The single instance of ActorRepositoryImpl.
   */

  public static synchronized ActorRepositoryImpl getInstance() {
    if (instance == null) {
      instance = new ActorRepositoryImpl();
    }
    return instance;
  }

  /**
   * Creates a new actor record based on the provided information within ActorDTO.
   * Checks for existing actors to prevent duplicates. Updates the actor list and persists
   * it to the file.
   *
   * @param entry The ActorDTO containing information for creating a new actor.
   * @return The ActorDTO representing the created actor.
   * @throws ActorException.ActorAlreadyExist if an actor with the same identifying information already exists.
   */

  @Override
  public ActorDTO create(ActorDTO entry) {
    if (existingActor(entry.name(), entry.nationality(), entry.birthDate())) {
      System.out.println("The actor is already on the list!");
      return entry;
    } else {
      Actor newActor = new Actor(
              idGenerator++,
              entry.name(),
              entry.nationality(),
              entry.birthDate(),
              entry.movies()
      );
      actorsList.add(newActor);
      //   FileHandler.updateFileA(actorsList, FILE_PATH);
      return ActorDTO.fromActor(newActor);
    }
  }

  /**
   * Retrieves all actor records as a list of ActorDTOs.
   *
   * @return A list of all actors in ActorDTO form.
   */

  @Override
  public List<ActorDTO> read() {
    List<ActorDTO> actorDTOList = actorsList.stream()
            .map(ActorDTO::fromActor)
            .collect(Collectors.toList());
    checkEmptyListException(actorDTOList);
    return actorDTOList;
  }


  /**
   * Updates an existing actor record with new information provided in an ActorDTO.
   *
   * @param entry  The original actor details to identify the actor to be updated.
   * @param entry2 The new actor details for the update.
   * @return The updated ActorDTO.
   * @throws ActorException.ActorNotFoundException if no actor is found with the provided ID.
   */

  @Override
  public ActorDTO update(ActorDTO entry, ActorDTO entry2) {
    Actor actor = ActorDTO.toActor(entry);
    actor.setName(entry2.name());
    actor.setNationality(entry2.nationality());
    actor.setBirthDate(entry2.birthDate());
    actor.setMovies(entry2.movies());

    //   FileHandler.updateFileA(actorsList, FILE_PATH);
    return ActorDTO.fromActor(actor);
  }

  /**
   * Deletes an actor based on the information provided in an ActorDTO.
   *
   * @param entry The ActorDTO identifying the actor to be deleted.
   * @throws ActorException.ActorNotFoundException if the actor to be deleted cannot be found.
   */

  @Override
  public void delete(ActorDTO entry) {
    Actor actor = foundActorId(entry.id());

    if (actor != null) {
      actorsList.remove(actor);
      //   FileHandler.updateFileA(actorsList, FILE_PATH);
    } else {
      throw new ActorException(entry.name() + "Do not existe");
    }
  }

  /**
   * Searches for actors matching a specific name.
   *
   * @param entry The ActorDTO containing the name to search for.
   * @return A list of ActorDTOs matching the search criteria.
   */

  @Override
  public List<ActorDTO> search(ActorDTO entry) {
    List<ActorDTO> list = actorsList.stream()
            .filter(actor -> actor.getName().equalsIgnoreCase(entry.name()))
            .map(ActorDTO::fromActor)
            .collect(Collectors.toList());
    checkEmptyListException(list);
    return list;
  }

  /**
   * Finds an actor by their unique ID and returns their details as an ActorDTO.
   *
   * @param actorDTO The ActorDTO containing the ID of the actor to read.
   * @return The ActorDTO of the read actor.
   * @throws ActorException.ActorNotFoundException if no actor is read with the specified ID.
   */

  @Override
  public ActorDTO readById(ActorDTO actorDTO) {
    Actor actor = foundActorId(actorDTO.id());
    checkActorNotFoundException(actor);
    return ActorDTO.fromActor(actor);
  }

  /**
   * Searches for an actor by ID within the list of actors.
   *
   * @param id The ID of the actor to find.
   * @return The actor if found, or null otherwise.
   */

  private Actor foundActorId(int id) {
    return actorsList.stream()
            .filter(actor1 -> actor1.getId() == id)
            .findFirst()
            .orElse(null);
  }

  /**
   * Searches for actors by their name.
   * This method filters all actors whose names contain the specified name substring, ignoring case.
   *
   * @param name The name or partial name to search for.
   * @return A list of actors whose names match the search criteria.
   */

  private List<ActorDTO> searchActorName(String name) {
    return actorsList.stream()
            .filter(actor -> actor.getName().toLowerCase().contains(name.toLowerCase()))
            .map(ActorDTO::fromActor)
            .collect(Collectors.toList());
  }

  /**
   * Checks if the provided list of ActorDTOs is empty and throws an {@link ActorException.ActorListIsEmpty} exception if it is.
   * This method is used to ensure that a list of actors is not empty before proceeding with further operations.
   *
   * @param list The list of ActorDTOs to check for emptiness.
   */
  private void checkEmptyListException(List<ActorDTO> list) {
    try {
      if (list.isEmpty()) {
        throw new ActorException.ActorListIsEmpty();
      }
    } catch (ActorException.ActorListIsEmpty e) {
      return;
    }
  }

  /**
   * Checks if the provided actor is null and throws an {@link ActorException.ActorNotFoundException} exception if it is.
   * This method is used to ensure that an actor is found before further processing.
   *
   * @param actor The actor object to check for null.
   */

  private void checkActorNotFoundException(Actor actor) {
    try {
      if (actor == null) {
        throw new ActorException.ActorNotFoundException();
      }
    } catch (ActorException.ActorNotFoundException e) {
      return;
    }
  }

  /**
   * Checks if an actor already exists in the repository based on name, nationality, and birthdate.
   *
   * @param name        The name of the actor.
   * @param nationality The nationality of the actor.
   * @param birthDate   The birthdate of the actor.
   * @return True if an actor with the same name, nationality, and birthdate exists; false otherwise.
   */

  private boolean existingActor(String name, String nationality, LocalDate birthDate) {
    return actorsList.stream()
            .anyMatch(actor -> actor.getName().equalsIgnoreCase(name) &&
                               actor.getNationality().equalsIgnoreCase(nationality) &&
                               actor.getBirthDate().equals(birthDate));
  }
}

