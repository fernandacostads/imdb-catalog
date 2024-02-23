package com.imdb.repository;

import com.imdb.DTO.DirectorDTO;

/**
 * Interface for director repository operations.
 * This extends the CRUDRepository interface to define CRUD operations specifically for DirectorDTO objects.
 */

public interface IDirectorRepository extends CRUDRepository<DirectorDTO> {
}