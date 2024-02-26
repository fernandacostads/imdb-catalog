package com.imdb.repository.impl;

import com.imdb.DTO.ActorDTO;
import com.imdb.model.Actor;
import com.imdb.repository.IActorRepository;
import com.imdb.util.exceptions.ActorException;
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

  private static ActorRepositoryImpl instance;
  private static List<Actor> actorsList;
  private int idGenerator;

  private ActorRepositoryImpl() {
    actorsList = new ArrayList<>(10);
    idGenerator = 1;
  }

  public static synchronized ActorRepositoryImpl getInstance() {
    if (instance == null) {
      instance = new ActorRepositoryImpl();
    }
    return instance;
  }

  /**
   * Creates a new actor with the provided details, adds it to the list of actors, and returns a DTO representing the new actor.
   *
   * @param entry The details of the actor to be created.
   * @return A DTO representing the newly created actor.
   */
  @Override
  public ActorDTO create(ActorDTO entry) {
    Actor actorExist = getActorIfExists(entry);
    if (actorExist == null) {
      Actor newActor = new Actor(
        idGenerator++,
        entry.name(),
        entry.nationality(),
        entry.birthDate()
      );
      actorsList.add(newActor);
      return ActorDTO.fromActor(newActor);
    }
    return ActorDTO.fromActor(actorExist);
  }

  /**
   * Retrieves a list of DTOs representing all actors.
   *
   * @return A list of DTOs representing all actors.
   */
  @Override
  public List<ActorDTO> read() {
    List<ActorDTO> actorDTOList = actorsList
      .stream()
      .map(ActorDTO::fromActor)
      .collect(Collectors.toList());
    checkEmptyListException(actorDTOList);

    return actorDTOList;
  }

  /**
   * Updates the details of an existing actor with the information provided and returns a DTO representing the updated actor.
   *
   * @param entry  The DTO representing the actor to be updated.
   * @param entry2 The DTO with the updated information of the actor.
   */
  @Override
  public void update(ActorDTO entry, ActorDTO entry2) {
    Actor actor = ActorDTO.toActor(entry);

    actor.setName(entry2.name());
    actor.setNationality(entry2.nationality());
    actor.setBirthDate(entry2.birthDate());

    ActorDTO.fromActor(actor);
  }

  /**
   * Deletes an actor from the list based on the provided DTO.
   *
   * @param entry The DTO representing the actor to be deleted.
   */
  @Override
  public void delete(ActorDTO entry) {
    Actor actor = foundActorId(entry.id());

    actorsList.remove(actor);
  }

  /**
   * Searches for actors with the given name and returns a list of DTOs representing the matching actors.
   *
   * @param entry The DTO containing the name of the actor to search for.
   * @return A list of DTOs representing the found actors.
   */
  @Override
  public List<ActorDTO> search(ActorDTO entry) {
    return searchActorName(entry.name());
  }

  /**
   * Retrieves a DTO representing the actor with the provided ID.
   *
   * @param actorDTO The DTO containing the ID of the actor to retrieve.
   * @return A DTO representing the found actor.
   */
  @Override
  public ActorDTO readById(ActorDTO actorDTO) {
    Actor actor = foundActorId(actorDTO.id());

    checkActorNotFoundException(actor);

    return ActorDTO.fromActor(actor);
  }

  /**
   * Finds an actor in the list based on the provided ID.
   *
   * @param id The ID of the actor to find.
   * @return The found actor, or null if not found.
   */
  private Actor foundActorId(int id) {
    return actorsList
      .stream()
      .filter(actor1 -> actor1.getId() == id)
      .findFirst()
      .orElse(null);
  }

  /**
   * Searches for actors by name and returns a list of DTOs representing the matching actors.
   *
   * @param name The name to search for.
   * @return A list of DTOs representing the found actors.
   */
  private List<ActorDTO> searchActorName(String name) {
    return actorsList
      .stream()
      .filter(actor ->
        actor.getName().toLowerCase().contains(name.toLowerCase())
      )
      .map(ActorDTO::fromActor)
      .collect(Collectors.toList());
  }

  /**
   * Checks if the list of actor DTOs is empty and throws an exception if it is.
   *
   * @param list The list of actor DTOs to check.
   */
  private void checkEmptyListException(List<ActorDTO> list) {
    if (list.isEmpty()) {
      throw new ActorException.ActorListIsEmpty();
    }
  }

  /**
   * Checks if an actor was not found and throws an exception if it wasn't.
   *
   * @param actor The actor to check.
   */
  private void checkActorNotFoundException(Actor actor) {
    if (actor == null) {
      throw new ActorException.ActorNotFoundException();
    }
  }

  /**
   * Checks if an actor with the provided name, nationality, and birthDate already exists and throws an exception if it does.
   *
   * @param actor The object of the actor.
   */
  protected Actor getActorIfExists(ActorDTO actor) {
    return actorsList
      .stream()
      .filter(existActor ->
        existActor.getName().equalsIgnoreCase(actor.name()) &&
        existActor.getNationality().equalsIgnoreCase(actor.nationality()) &&
        existActor.getBirthDate().equals(actor.birthDate())
      )
      .findFirst()
      .orElse(null);
  }
}
