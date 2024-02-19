package com.imdb.src2.main.java.com.imdb.core.cases.director;

import com.imdb.model.Director;

import java.util.List;
import java.util.Optional;

public interface DirectorUseCase {
    void addDirector(Director director);

    //remove um diretor
    void removeDirector(Director director);

    //atualiza um diretor
    Director updateDirector(Director director);

    //pesquisa um diretor por nome
    Optional<Director> searchDirector(String name);

    //pega a lista de diretor
    List<Director> getAllDirectors();
}
