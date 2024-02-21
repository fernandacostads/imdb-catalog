package com.imdb.repository;

import com.imdb.dto.MovieDTO;

import java.util.List;

public interface IMovieRepository extends CRUDRepository<MovieDTO> {
    List<MovieDTO> searchByTitle(String title);
}
