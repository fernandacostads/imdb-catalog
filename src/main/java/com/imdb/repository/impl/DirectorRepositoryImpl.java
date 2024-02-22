package com.imdb.repository.impl;

import com.imdb.dto.DirectorDTO;
import com.imdb.model.Director;
import com.imdb.repository.IDirectorRepository;

import java.util.ArrayList;
import java.util.List;

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
}
