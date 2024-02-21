package com.imdb.repository.impl;

import com.imdb.dto.DirectorDTO;
import com.imdb.model.Director;
import com.imdb.repository.IDirectorRepository;
import com.imdb.util.FileHandler;
import com.imdb.util.ModelConvertUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DirectorRepositoryImpl implements IDirectorRepository {

  private static final String FILE_PATH =
          "src/main/java/com/imdb/util/resources/directors.txt";
  private static DirectorRepositoryImpl instance;
  private static List<Director> directorsList;
  private final ModelConvertUtil converter = new ModelConvertUtil();

  private int idGenerator;

  private DirectorRepositoryImpl() {
    directorsList = new ArrayList<>(10);
    directorsList = FileHandler.loadDirectorsFromFile(FILE_PATH);
    idGenerator = directorsList.isEmpty() ? 1 : directorsList.getLast().getId() + 1;
  }

  public static synchronized DirectorRepositoryImpl getInstance() {
    if (instance == null) {
      instance = new DirectorRepositoryImpl();
    }
    return instance;
  }


  @Override
  public void addDirector(DirectorDTO directorDTO) {
    Director director = converter.convertDTOToObjt(directorDTO);
    director.setId(idGenerator++);
    directorsList.add(director);
    FileHandler.updateFileD(directorsList, FILE_PATH);
  }

  @Override
  public void directorPresent(String name) {
    throw new IllegalArgumentException("O Diretor " + name + " já existe");

  }

  @Override
  public void directorNotFound(String name) {
    throw new IllegalArgumentException("O Diretor " + name + " não existe");
  }

  @Override
  public void removeDirector(Director director) {
    directorsList.remove(director);
    FileHandler.updateFileD(directorsList, FILE_PATH);
  }

  @Override
  public void updateDirector(Director director, String newName, String nationality) {
    director.setName(newName);
    director.setNationality(nationality);
    FileHandler.updateFileD(directorsList, FILE_PATH);
  }


  @Override
  public Optional<Director> searchDirector(String name) {
    return directorsList
            .stream()
            .filter(aux -> aux.getName().equalsIgnoreCase(name))
            .findFirst();
  }

  @Override
  public String getAllDirectors() {
    if (directorsList.isEmpty()) {
      throw new IllegalArgumentException("A lista de atores está vazia");
    }
    StringBuilder allActorsBuilder = new StringBuilder();
    for (Director director : directorsList) {
      allActorsBuilder.append(director);
      allActorsBuilder.append("\n"); // Adiciona uma quebra de linha entre cada representação de ator
    }
    return allActorsBuilder.toString();
  }

}
