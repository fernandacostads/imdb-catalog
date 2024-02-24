package com.imdb.repository;

import java.util.List;

/**
 * A generic interface for CRUD (Create, Read, Update, Delete) operations.
 * This interface is designed to be implemented by repositories to manage basic operations for any entity type.
 *
 * @param <T> the type of the entity the repository will manage
 */

public interface CRUDRepository<T> {

  /**
   * Creates a new entity in the repository.
   *
   * @param entry the entity to be created
   * @return the created entity
   */

  T create(T entry);

  /**
   * Retrieves all entities from the repository.
   *
   * @return a list of all entities
   */

  List<T> read();

  /**
   * Updates an existing entity in the repository.
   *
   * @param entry  the entity to be updated
   * @param entry2 the entity containing the new values
   * @return the updated entity
   */

  T update(T entry, T entry2);

  /**
   * Deletes an entity from the repository.
   *
   * @param entry the entity to be deleted
   */

  void delete(T entry);

  /**
   * Searches for entities based on a name or partial name.
   * The exact search criteria are implementation-specific.
   *
   * @param entry an example entity containing the search criteria
   * @return a list of entities matching the search criteria
   */

  List<T> search(T entry);

  /**
   * Reads an entity by its identifier.
   *
   * @param entry the entity with its identifier set
   * @return the entity with the given id or null if not found
   */

  T readById(T entry);
}
