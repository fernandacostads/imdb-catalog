package com.imdb.repository.impl;

import com.imdb.dto.DirectorDTO;
import com.imdb.model.Actor;
import com.imdb.model.Director;
import com.imdb.repository.IDirectorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DirectorRepositoryImpl implements IDirectorRepository {
  private static DirectorRepositoryImpl instance;
  private static List<Director> directorsList;
  private int idGenerator;

  private DirectorRepositoryImpl() {
    directorsList = new ArrayList<>(10);
    idGenerator = directorsList.isEmpty() ? 1 : directorsList.get(directorsList.size() - 1).getId() + 1;
  }

  public static synchronized DirectorRepositoryImpl getInstance() {
    if (instance == null) {
      instance = new DirectorRepositoryImpl();
    }
    return instance;
  }

  @Override
  public void create(DirectorDTO entry) {

  }

  @Override
  public DirectorDTO update(DirectorDTO entry, DirectorDTO entry2) {
    return null;
  }

  @Override
  public void delete(DirectorDTO entry) {

  }

  @Override
  public List<DirectorDTO> getAll() {
    return null;
  }

  @Override
  public DirectorDTO readById(DirectorDTO id) {
    return null;
  }

  @Override
  public List<DirectorDTO> readByName(DirectorDTO entry) {
    return null;
  }
  private Director findActorById(int id) {
    return directorsList.stream()
            .filter(director -> director.getId() == id)
            .findFirst()
            .orElse(null);
  }

  private List<Director> findActorByName(String name) {
    return directorsList.stream()
            .filter(director -> director.getName().toLowerCase().contains(name.toLowerCase()))
            .collect(Collectors.toList());
  }

  private List<Director> findActorByNationality(String nationality) {
    return directorsList.stream()
            .filter(director -> director.getNationality().toLowerCase().contains(nationality.toLowerCase()))
            .collect(Collectors.toList());
  }
}
