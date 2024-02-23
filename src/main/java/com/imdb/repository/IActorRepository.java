package com.imdb.repository;

import com.imdb.DTO.ActorDTO;

/**
 * Interface for actor repository operations.
 * This extends the CRUDRepository interface to define CRUD operations specifically for ActorDTO objects.
 */

public interface IActorRepository extends CRUDRepository<ActorDTO> {
}
