package com.imdb.repository.impl;

import com.imdb.DTO.ActorDTO;
import com.imdb.model.Actor;
import com.imdb.repository.IActorRepository;
import com.imdb.util.FileHandler;
import com.imdb.util.exceptions.ActorException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class ActorRepositoryImpl implements IActorRepository {
    private static final String FILE_PATH =
            "src/main/java/com/imdb/util/resources/actors.txt";
    private static ActorRepositoryImpl instance;
    private static List<Actor> actorsList;
    private int idGenerator;

    private ActorRepositoryImpl() {
        actorsList = new ArrayList<>(10);
        actorsList = FileHandler.loadActorsFromFile(FILE_PATH);
        idGenerator = actorsList.isEmpty() ? 1 : actorsList.getLast().getId() + 1;
    }

    public static synchronized ActorRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new ActorRepositoryImpl();
        }
        return instance;
    }

    @Override
    public ActorDTO create(ActorDTO entry) {
        Actor newActor = new Actor(
                idGenerator++,
                entry.name(),
                entry.nationality()
        );
        actorsList.add(newActor);
        FileHandler.updateFileA(actorsList, FILE_PATH);
        return ActorDTO.fromActor(newActor);
    }

    @Override
    public ActorDTO update(ActorDTO entry, ActorDTO entry2) throws ActorException.ActorNotFoundException {
        Actor actor = findActorById(entry.id());
        actor.setName(entry2.name());
        actor.setNationality(entry2.nationality());
        FileHandler.updateFileA(actorsList, FILE_PATH);
        return ActorDTO.fromActor(actor);
    }

    @Override
    public void delete(ActorDTO entry) throws ActorException.ActorNotFoundException {
        Actor actor = findActorById(entry.id());
        actorsList.remove(actor);
        FileHandler.updateFileA(actorsList, FILE_PATH);
    }

    @Override
    public List<ActorDTO> getAll() {
        try {
            return actorsList.stream()
                    .filter(actor -> !actorsList.isEmpty())
                    .map(ActorDTO::fromActor)
                    .collect(Collectors.toList());
        } catch (ActorException.ActorListIsEmpty e){
            return Collections.emptyList();
        }
    }

    @Override
    public ActorDTO readById(ActorDTO actorDTO) throws ActorException.ActorNotFoundException {
        Actor actor = findActorById(actorDTO.id());
        return ActorDTO.fromActor(actor);
    }

    @Override
    public List<ActorDTO> readByName(ActorDTO entry) {
        List<ActorDTO> list = actorsList.stream()
                .filter(actor -> actor.getName().equalsIgnoreCase(entry.name()))
                .map(ActorDTO::fromActor)
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            throw new ActorException.ActorListIsEmpty();
        }
        return list;
    }

    private Actor findActorById(int id) {
        Actor actor = actorsList.stream()
                .filter(actor1 -> actor1.getId() == id)
                .findFirst()
                .orElse(null);
        if (actor == null) {
            throw new ActorException.ActorNotFoundException();
        }
        return actor;
    }

    private List<Actor> findActorByName(String name) {
        List<Actor> list = actorsList.stream()
                .filter(actor -> actor.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            throw new ActorException.ActorListIsEmpty();
        }
        return list;
    }

    private List<Actor> findActorByNationality(String nationality) {
        List<Actor> list = actorsList.stream()
                .filter(actor -> actor.getNationality().toLowerCase().contains(nationality.toLowerCase()))
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            throw new ActorException.ActorListIsEmpty();
        }
        return list;
    }
}

