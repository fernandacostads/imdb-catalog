package com.imdb.repository;

import com.imdb.model.Director;

import java.util.List;

public interface IDirectorRepository{
  void addDirector(Director director);
  Director search(String name);
  List<Director> getAllDirectors();
  void updateDirector(Director director);
  void removeDirector(String name);
  void updateFile();
  List<Director> loadData();
}
