package com.imdb.repository;

import com.imdb.DTO.MovieDTO;

/**
 * Interface for movie repository operations.
 * This extends the CRUDRepository interface to define CRUD operations specifically for MovieDTO objects.
 */

public interface IMovieRepository extends CRUDRepository<MovieDTO> {
}
