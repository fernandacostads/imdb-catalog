package com.imdb.repository.impl;

import com.imdb.dto.ActorDTO;
import com.imdb.model.Actor;
import com.imdb.model.Movie;
import com.imdb.repository.IActorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ActorRepositoryImpl implements IActorRepository {
    private static ActorRepositoryImpl instance;
    private static List<Actor> actorsList;
    private int idGenerator;

    private ActorRepositoryImpl() {
        actorsList = new ArrayList<>(10);
        idGenerator = actorsList.isEmpty() ? 1 : actorsList.get(actorsList.size() - 1).getId() + 1;
    }

    public static synchronized ActorRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new ActorRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void create(ActorDTO entry) {

    }

    @Override
    public ActorDTO update(ActorDTO entry, ActorDTO entry2) {
        return null;
    }

    @Override
    public void delete(ActorDTO entry) {

    }

    @Override
    public List<ActorDTO> getAll() {
        return null;
    }

    @Override
    public ActorDTO readById(ActorDTO id) {
        return null;
    }

    @Override
    public List<ActorDTO> readByName(ActorDTO entry) {
        return null;
    }

    private Actor findActorById(int id) {
        return actorsList.stream()
                .filter(actor -> actor.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private List<Actor> findActorByName(String name) {
        return actorsList.stream()
                .filter(actor -> actor.getName().toLowerCase().contains(name.toLowerCase()))
                       .collect(Collectors.toList());
    }

    private List<Actor> findActorByNationality(String nationality) {
        return actorsList.stream()
                       .filter(actor -> actor.getNationality().toLowerCase().contains(nationality.toLowerCase()))
                       .collect(Collectors.toList());
    }
}

