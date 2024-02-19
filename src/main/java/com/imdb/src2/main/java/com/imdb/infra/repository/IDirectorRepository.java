package com.imdb.src2.main.java.com.imdb.infra.repository;

import com.imdb.model.Director;

import java.util.List;
import java.util.Optional;

public interface IDirectorRepository {
  void addDirector(Director director);
  void removeDirector(Director director);
  Director updateDirector(Director director);
  Optional<Director> searchDirector(String name);
  List<Director> getAllDirectors();
}
