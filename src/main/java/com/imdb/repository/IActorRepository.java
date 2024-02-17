package com.imdb.repository;

import com.imdb.model.Actor;

import java.util.List;

public interface IActorRepository{
  void addActor(Actor actor);
  Actor search(String name);
  List<Actor> getAllActors();
  void updateActor(Actor actor);
  void removeActor(String name);
  void updateFile();
  List<Actor> loadData();

}
