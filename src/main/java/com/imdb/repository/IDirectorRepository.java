package com.imdb.repository;

import com.imdb.dto.DirectorDTO;
import com.imdb.model.Director;

import java.util.List;
import java.util.Optional;

public interface IDirectorRepository {
  void addDirector(DirectorDTO director);

  void removeDirector(Director director);

  Director updateDirector(Director director);

  Optional<Director> searchDirector(String name);

  List<Director> getAllDirectors();
}
