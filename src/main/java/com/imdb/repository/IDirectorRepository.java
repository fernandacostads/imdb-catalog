package com.imdb.repository;

import com.imdb.dto.DirectorDTO;
import com.imdb.model.Director;

import java.util.Optional;

public interface IDirectorRepository {
  void addDirector(DirectorDTO directorDTO);

  void directorPresent(String name);

  void directorNotFound(String name);

  void removeDirector(Director director);

  void updateDirector(Director director, String name, String nationality);

  Optional<Director> searchDirector(String name);

  String getAllDirectors();
}
