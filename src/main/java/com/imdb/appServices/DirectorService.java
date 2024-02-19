package com.imdb.appServices;

import com.imdb.model.Director;
import com.imdb.repository.impl.DirectorRepository;
import java.util.List;
import java.util.Optional;

public class DirectorService {

  private final DirectorRepository directorRepository;
  //ficaria em gateway
  public DirectorService() {
    directorRepository = DirectorRepository.getInstance();
  }

  //adiciona um diretor
  public void addDirector(Director director) {
    directorRepository.addDirector(director);
  }

  //remove um diretor
  public void removeDirector(Director director) {
    directorRepository.removeDirector(director);
  }

  //atualiza um diretor
  public Director updateDirector(Director director) {
    return directorRepository.updateDirector(director);
  }

  //pesquisa um diretor por nome
  public Optional<Director> searchDirector(String name) {
    return directorRepository.searchDirector(name);
  }

  //pega a lista de diretor
  public List<Director> getAllDirectors() {
    return directorRepository.getAllDirectors();
  }
  /* public Director findDirectorByName(String name) {
    return directorRepository.findDirectorByName(name);
  }

  public Director findDirectorByNationality(String nationality) {
    return directorRepository.findDirectorByNationality(nationality);
  }*/

}
