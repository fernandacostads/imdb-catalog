package com.imdb.src2.main.java.com.imdb.adapters;

import com.imdb.model.Director;
import com.imdb.src2.main.java.com.imdb.core.cases.director.DirectorUseCase;
import com.imdb.infra.repository.DirectorRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DirectorRepositoryGateway implements DirectorUseCase {
    private final DirectorRepository directorRepository;
    private static DirectorRepositoryGateway instance;
    private DirectorRepositoryGateway(){
        directorRepository = DirectorRepository.getInstance();
    }

    public static DirectorRepositoryGateway getInstance() {
        if (Objects.isNull(instance)) {
            instance = new DirectorRepositoryGateway();
        }
        return instance;
    }

    @Override
    //adiciona um diretor
    public void addDirector(Director director) {
        directorRepository.addDirector(director);
    }
    @Override
    //remove um diretor
    public void removeDirector(Director director) {
        directorRepository.removeDirector(director);
    }
    @Override
    //atualiza um diretor
    public Director updateDirector(Director director) {
        return directorRepository.updateDirector(director);
    }
    @Override
    //pesquisa um diretor por nome
    public Optional<Director> searchDirector(String name) {
        return directorRepository.searchDirector(name);
    }
    @Override
    //pega a lista de diretor
    public List<Director> getAllDirectors() {
        return directorRepository.getAllDirectors();
    }
}
